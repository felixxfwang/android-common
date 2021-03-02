package org.tiramisu.repository.http

import org.tiramisu.http.HttpParam

abstract class SimpleHttpCoroutineDataSource <P: HttpParam, D, RSP: Any>
    : BaseHttpCoroutineDataSource<P, D, P, RSP>() {

    override fun getRequest(param: P): P = param
}