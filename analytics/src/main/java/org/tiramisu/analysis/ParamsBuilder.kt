package org.tiramisu.analysis

import android.os.Bundle

class ParamsBuilder {
    val bundle: Bundle = Bundle()

    fun param(key: kotlin.String, value: android.os.Bundle) {
        bundle.putBundle(key, value)
    }

//    fun param(key: kotlin.String, value: kotlin.Array<android.os.Bundle>) {
//        bundle.putAr
//    }

    fun param(key: kotlin.String, value: kotlin.Double) {
        bundle.putDouble(key, value)
    }

    fun param(key: kotlin.String, value: kotlin.Long) {
        bundle.putLong(key, value)
    }

    fun param(key: kotlin.String, value: kotlin.String) {
        bundle.putString(key, value)
    }
}