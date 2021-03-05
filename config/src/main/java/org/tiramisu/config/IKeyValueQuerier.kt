package org.tiramisu.config

interface IKeyValueQuerier {

    fun getString(key: String): String

    fun getBoolean(key: String): Boolean

    fun getInt(key: String): Int

    fun getValue(key: String): IConfigValue

}