package com.ekoapp.community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import kotlinx.android.synthetic.main.activity_feature_list.*

class FeatureListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_list)

        btnOpenDefaultCommunity.setOnClickListener{
            val intent = Intent(this, EkoCommunityHomePageActivity::class.java)
            startActivity(intent)
        }
    }
}