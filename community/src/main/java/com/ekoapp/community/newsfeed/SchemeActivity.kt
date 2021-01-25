package com.ekoapp.community.newsfeed

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class SchemeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, NewsFeedHomeActivity::class.java))

        val uri = intent.data
        if (uri != null) {

        }

        finish()
    }
}
