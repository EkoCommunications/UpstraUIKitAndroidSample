package com.ekoapp.chatkitfragment

import android.app.Application
import com.ekoapp.ekosdk.EkoClient

class ChatKitFragmentApp: Application() {

    /**
     * Replace with actual api key [String]
     */
    val TEST_KEY = "b3babb5e32dbf2314935d81e0301438fd85b84b7b2316a2e"

    override fun onCreate() {
        super.onCreate()
        /**
         * Setup call to initialize the SDK
         */
        EkoClient.setup(TEST_KEY)
    }
}