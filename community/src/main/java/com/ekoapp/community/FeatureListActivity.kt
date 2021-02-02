package com.ekoapp.community

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ekoapp.community.homepage.MyCustomCommunityHomePageActivity
import com.ekoapp.community.newsfeed.CreatePostActivity
import com.ekoapp.community.newsfeed.NewsFeedHomeActivity
import com.ekoapp.ekosdk.uikit.community.home.activity.EkoCommunityHomePageActivity
import com.ekoapp.ekosdk.uikit.community.ui.view.EkoCommunityCreateActivity
import kotlinx.android.synthetic.main.activity_feature_list.*

class FeatureListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_list)

        btnOpenDefaultCommunity.setOnClickListener {
            val intent = Intent(this, EkoCommunityHomePageActivity::class.java)
            startActivity(intent)
        }

        btnOpenCustomCommunity.setOnClickListener {
            val intent = Intent(this, MyCustomCommunityHomePageActivity::class.java)
            startActivity(intent)
        }

        btnCreateCommunity.setOnClickListener {
            val intent = Intent(this, EkoCommunityCreateActivity::class.java)
            startActivity(intent)
        }

        btnCommunityHome.setOnClickListener {
            val intent = Intent(this, NewsFeedHomeActivity::class.java)
            startActivity(intent)
        }

        btnCreatePost.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
    }
}