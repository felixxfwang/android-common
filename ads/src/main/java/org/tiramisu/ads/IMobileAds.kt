package org.tiramisu.ads

import android.content.Context

/**
 *
 *
 * @author felixxfwang
 */
interface IMobileAds {

    fun initialize(context: Context)

    fun fetchAds(callback: IMobileAdsCallback)
}