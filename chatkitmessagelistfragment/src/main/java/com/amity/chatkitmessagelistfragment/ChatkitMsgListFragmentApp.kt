package com.amity.chatkitmessagelistfragment

import android.app.Application
import com.amity.socialcloud.sdk.AmityCoreClient

class ChatkitMsgListFragmentApp: Application() {

    /**
     * Replace with actual api key [String]
     */
    val TEST_KEY = "b3babb5e32dbf2314935d81e0301438fd85b84b7b2316a2e"

    override fun onCreate() {
        super.onCreate()
        /**
         * Setup call to initialize the SDK
         */
        AmityCoreClient.setup(TEST_KEY)
    }
}