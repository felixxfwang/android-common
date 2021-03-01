package org.tiramisu.repository.http

interface IHttpBasicInfo<RSP> {

    val baseUrl: String
    val path: String
    val rspClass: Class<RSP>
}