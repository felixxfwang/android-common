package org.tiramisu.repository.http

import org.tiramisu.http.HttpParam
import org.tiramisu.repository.coroutine.BaseCoroutineDataSource
import org.tiramisu.repository.coroutine.CoroutineDataClient

abstract class BaseHttpCoroutineDataSource <P, D, REQ: HttpParam, RSP: Any>(
) : BaseCoroutineDataSource<P, D, REQ, RSP>(), IHttpBasicInfo<RSP> {

    override fun getDataClient(): CoroutineDataClient<REQ, RSP> {
        return HttpCoroutineDataClient(baseUrl, path, rspClass)
    }
}