package org.tiramisu.repository.http

import org.tiramisu.http.HttpParam
import org.tiramisu.repository.coroutine.BaseCoroutineDataSource
import org.tiramisu.repository.coroutine.CoroutineDataClient

abstract class SimpleHttpCoroutineDataSource <REQ: HttpParam, RSP: Any>
    : BaseCoroutineDataSource<REQ, RSP, REQ, RSP>(), IHttpBasicInfo<RSP> {

    override fun getDataClient(): CoroutineDataClient<REQ, RSP> {
        return HttpCoroutineDataClient(baseUrl, path, rspClass)
    }

    override fun getRequest(param: REQ): REQ = param

    override fun getResponse(response: RSP): RSP = response
}