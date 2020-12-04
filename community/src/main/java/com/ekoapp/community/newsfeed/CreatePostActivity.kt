package com.ekoapp.community.newsfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekoapp.community.R
import com.ekoapp.ekosdk.uikit.community.newsfeed.fragment.EkoPostCreateFragment

class CreatePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getCommunityHomeFragment()
            fragmentTransaction.replace(com.ekoapp.ekosdk.uikit.R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }

    }

    private fun getCommunityHomeFragment(): Fragment {
        return EkoPostCreateFragment.Builder()
            .build(this)
    }

}