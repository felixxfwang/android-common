package org.tiramisu.repository.http

import org.tiramisu.http.HttpParam
import org.tiramisu.repository.coroutine.BaseCoroutineDataSource
import org.tiramisu.repository.coroutine.CoroutineDataClient

abstract class SimpleHttpCoroutineDataSource <P: HttpParam, D, RSP: Any>
    : BaseCoroutineDataSource<P, D, P, RSP>(), IHttpBasicInfo<RSP> {

    override fun getDataClient(): CoroutineDataClient<P, RSP> {
        return HttpCoroutineDataClient(baseUrl, path, rspClass)
    }

    override fun getRequest(param: P): P = param
}