package com.ekoapp.sharepost.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekoapp.ekosdk.uikit.community.explore.fragments.EkoExploreFragment
import com.ekoapp.ekosdk.uikit.community.home.fragments.EkoCommunityHomePageFragment
import com.ekoapp.ekosdk.uikit.community.home.listener.IExploreFragmentFragmentDelegate
import com.ekoapp.sharepost.R

class CommunityHomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getCommunityHomeFragment()
            fragmentTransaction.replace(com.ekoapp.ekosdk.uikit.R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }
    }

    private fun getCommunityHomeFragment(): Fragment {
        return EkoCommunityHomePageFragment.Builder().build(this)
    }

}