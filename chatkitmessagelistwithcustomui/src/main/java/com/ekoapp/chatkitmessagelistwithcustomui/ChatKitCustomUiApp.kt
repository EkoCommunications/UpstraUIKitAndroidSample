package com.ekoapp.chatkitmessagelistwithcustomui

import android.app.Application
import com.ekoapp.ekosdk.EkoClient

class ChatKitCustomUiApp : Application() {

    /**
     * Replace with actual api key [String]
     */
    val TEST_KEY = "b3baef0b6cdfa4361a348a1f5a011688d10889e4bc306d7d"

    override fun onCreate() {
        super.onCreate()
        /**
         * Setup call to initialize the SDK
         */
        EkoClient.setup(TEST_KEY)
    }
}