package com.amity.sharepost.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.amity.sharepost.R
import com.amity.socialcloud.uikit.community.home.fragments.AmityCommunityHomePageFragment

class CommunityHomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getCommunityHomeFragment()
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }
    }

    private fun getCommunityHomeFragment(): Fragment {
        return AmityCommunityHomePageFragment.newInstance().build(this)
    }

}