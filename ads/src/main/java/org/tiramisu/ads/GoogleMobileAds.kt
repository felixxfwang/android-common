package org.tiramisu.ads

import android.content.Context
import com.google.android.gms.ads.MobileAds

/**
 * @author felixxfwang
 */
class GoogleMobileAds: IMobileAds {

    override fun initialize(context: Context) {
        MobileAds.initialize(context)
    }

    override fun fetchAds(callback: IMobileAdsCallback) {
    }
}