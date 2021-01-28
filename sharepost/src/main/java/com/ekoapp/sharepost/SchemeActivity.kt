package com.ekoapp.sharepost

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import kotlinx.android.synthetic.main.activity_feature_list.*

class SchemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_list)

        btnOpenDefaultCommunity.setOnClickListener {
            val intent = Intent(this, EkoCommunityHomePageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun handleDeepLink(callback: (Uri) -> Unit) {
        val uri = intent.data
        if (uri != null) callback.invoke(uri)
    }
}
