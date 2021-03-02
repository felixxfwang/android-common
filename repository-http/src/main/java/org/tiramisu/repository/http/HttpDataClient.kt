package org.tiramisu.repository.http

import org.tiramisu.http.*
import org.tiramisu.http.Result
import org.tiramisu.repository.*

class HttpDataClient<PARAM: HttpParam, RESULT: Any>(
    baseUrl: String,
    private val path: String,
    private val rspClass: Class<RESULT>,
    private val deserializer: HttpResponseDeserializable<RESULT>? = null
) : DataClient<PARAM, RESULT> {

    private val http = TiramisuHttp().baseUrl(baseUrl)

    override fun sendDataRequest(param: PARAM): DataResult<RESULT> {
        return when (val result = http.client.sendHttpRequest(http.wrapUrl(path), HttpMethod.GET, rspClass, param, deserializer = deserializer)) {
            is Result.Success -> DataResult.success(result.get())
            is Result.Failure -> DataResult.error(DataException(result.error.code, result.error.message, result.error.cause))
        }
    }

    override fun sendDataRequest(
        param: PARAM,
        callback: DataCallback<PARAM, RESULT>
    ): Disposable {
        return HttpDisposable(
            http.client.sendHttpRequest(http.wrapUrl(path), HttpMethod.GET, rspClass, param, deserializer = deserializer, callback = HttpDataCallback(callback))
        )
    }

    class HttpDataCallback<P: HttpParam, R>(private val callback: DataCallback<P, R>): HttpCallback<P, R> {

        override fun onSuccess(param: P, data: R) {
            callback.onSuccess(param, data)
        }

        override fun onError(param: P, error: HttpException) {
            callback.onError(param, DataException(error.code, error.message))
        }

    }
}