package com.ekoapp.community.newsfeed

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

class SchemeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDeepLink {
            val intent = Intent(this, NewsFeedHomeActivity::class.java)
            intent.putExtra("uri", it)
            startActivity(intent)
        }
        finish()
    }

    private fun handleDeepLink(callback: (Uri) -> Unit) {
        val uri = intent.data
        if (uri != null) callback.invoke(uri)
    }
}
