package com.ekoapp.chatkitmessagelistfragment

import android.app.Application
import com.ekoapp.ekosdk.EkoClient

class ChatkitMsgListFragmentApp: Application() {

    /**
     * Replace with actual api key [String]
     */
    val TEST_KEY = "b3baed5a3dd8f5661933851d505b1688d1088bebbd346a7f"

    override fun onCreate() {
        super.onCreate()
        /**
         * Setup call to initialize the SDK
         */
        EkoClient.setup(TEST_KEY)
    }
}