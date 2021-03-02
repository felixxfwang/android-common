package org.tiramisu.repository.http

import org.tiramisu.http.HttpParam

abstract class SimpleHttpCoroutineDataSource <P: HttpParam, D: Any>
    : BaseHttpCoroutineDataSource<P, D, P, D>() {

    override fun getRequest(param: P): P = param

    override fun getResponse(response: D): D = response
}