package org.tiramisu.feeds.repository

import org.tiramisu.log.TLog
import org.tiramisu.repository.BaseDataSource
import org.tiramisu.repository.DataCallback
import org.tiramisu.repository.DataException
import org.tiramisu.repository.LoadCallback
import java.util.concurrent.atomic.AtomicBoolean

abstract class FeedsDataSource<P: FeedReqParameter, D, REQ, RSP, KEY>()
    : BaseDataSource<P, D, REQ, RSP>(), PagingDataSource<P, D> {

    companion object {
        private const val TAG = "FeedsDataRepository"
    }

    private val isLoadingInitial = AtomicBoolean(false)
    private val isLoadingAfter = AtomicBoolean(false)
    protected var isLastPage = AtomicBoolean(false)
    protected var nextKey: KEY? = null

    override fun loadData(param: P, callback: LoadInitialCallback<P, D>?) = loadInitial(param, callback)

    override fun loadInitial(param: P, callback: LoadInitialCallback<P, D>?) {
        callback?.onLoadDataStart(param)
        sendDataRequest(param, true, callback)
    }

    override fun loadAfter(param: P, callback: LoadMoreCallback<P, D>?) {
        callback?.onLoadMoreStart(param)
        sendDataRequest(param, false, callback)
    }

    override fun cancel() {
        super.cancel()
        isLoadingInitial.set(false)
        isLoadingAfter.set(false)
    }

    override fun isLoading(): Boolean = isLoadingInitial() || isLoadingAfter()

    override fun isLoadingInitial(): Boolean = isLoadingInitial.get()

    override fun isLoadingAfter(): Boolean = isLoadingAfter.get()

    override fun isLastPage(): Boolean = isLastPage.get()

    protected open fun sendDataRequest(param: P, isLoadInitial: Boolean, callback: LoadCallback<P, D>?) {
        setLoadingState(isLoadInitial, true)
        val intercepted = onRequestPreProcess(param, isLoadInitial, callback)
        if (!intercepted) {
            val req = getRequest(param, isLoadInitial)
            TLog.i(TAG, "request: $req")
            request = client.sendDataRequest(req, object : DataCallback<REQ, RSP> {
                override fun onSuccess(req: REQ, data: RSP) {
                    onLoadSuccess(param, data, isLoadInitial, req, callback)
                }

                override fun onError(req: REQ, error: DataException) {
                    onLoadFailed(error, param, isLoadInitial, callback)
                }

            })
        }
    }

    protected fun onLoadFailed(
        error: DataException,
        param: P,
        isLoadInitial: Boolean,
        callback: LoadCallback<P, D>?
    ) {
        TLog.e(TAG, "onError: errCode=${error.code}, errMsg: ${error.message}")
        onLoadFailed(param, isLoadInitial, callback, error.code, error.message)
        onLoadComplete(param, isLoadInitial, callback)
        setLoadingState(isLoadInitial, false)
    }

    protected fun onLoadSuccess(
        param: P,
        data: RSP,
        isLoadInitial: Boolean,
        req: REQ,
        callback: LoadCallback<P, D>?
    ) {
        // 解析回包数据
        val rsp = getDataListFromRsp(param, data, isLoadInitial)
        // 数据后处理
        val isLast = isLastPage(data)
        isLastPage.set(isLast)
        nextKey = getNextKeyFromRsp(req, data)
        onResponsePostProcess(param, data, rsp, isLoadInitial, isLast)

        TLog.i(TAG, "onSuccess: $data")

        // 回调给业务
        onLoadSuccess(param, isLoadInitial, callback, rsp, isLast)
        onLoadComplete(param, isLoadInitial, callback)
        setLoadingState(isLoadInitial, false)
    }

    protected fun setLoadingState(isLoadInitial: Boolean, isLoading: Boolean) {
        if (isLoadInitial) {
            isLoadingInitial.set(isLoading)
        } else {
            isLoadingAfter.set(isLoading)
        }
    }

    protected fun onLoadSuccess(param: P, isLoadInitial: Boolean, callback: LoadCallback<P, D>?, data: D, isLastPage: Boolean) {
        if (isLoadInitial) {
            (callback as? LoadInitialCallback)?.onLoadDataSuccess(param, data)
        } else {
            (callback as? LoadMoreCallback)?.onLoadMoreSuccess(param, data, isLastPage)
        }
    }

    protected fun onLoadFailed(param: P, isLoadInitial: Boolean, callback: LoadCallback<P, D>?, code: Int, errorMessage: String?) {
        if (isLoadInitial) {
            (callback as? LoadInitialCallback)?.onLoadDataFailed(param, code, errorMessage)
        } else {
            (callback as? LoadMoreCallback)?.onLoadMoreFailed(param, code, errorMessage)
        }
    }

    protected fun onLoadComplete(param: P, isLoadInitial: Boolean, callback: LoadCallback<P, D>?) {
        if (isLoadInitial) {
            (callback as? LoadInitialCallback)?.onLoadDataComplete(param)
        } else {
            (callback as? LoadMoreCallback)?.onLoadMoreComplete(param)
        }
    }

    protected abstract fun getRequest(param: P, isLoadInitial: Boolean): REQ

    protected abstract fun getDataListFromRsp(param: P, rsp: RSP, isLoadInitial: Boolean): D

    protected abstract fun getNextKeyFromRsp(req: REQ, rsp: RSP): KEY

    protected abstract fun isLastPage(rsp: RSP): Boolean

    protected open fun onRequestPreProcess(param: P, isLoadInitial: Boolean, callback: LoadCallback<P, D>?): Boolean = false

    protected open fun onResponsePostProcess(param: P, rsp: RSP, data: D, isLoadInitial: Boolean, isLastPage: Boolean) {}
}
