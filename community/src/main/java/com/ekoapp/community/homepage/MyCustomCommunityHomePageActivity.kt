package com.ekoapp.community.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekoapp.community.R
import com.ekoapp.ekosdk.uikit.community.home.fragments.EkoCommunityHomePageFragment
import com.ekoapp.ekosdk.uikit.community.home.listener.IExploreFragmentFragmentDelegate

class MyCustomCommunityHomePageActivity : AppCompatActivity(), IExploreFragmentFragmentDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_custom_community_home_page)
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getCommunityHomeFragment()
            fragmentTransaction.replace(com.ekoapp.ekosdk.uikit.R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }

    }

    private fun getCommunityHomeFragment(): Fragment {
        return EkoCommunityHomePageFragment.Builder()
            .exploreFragmentDelegate(this)
            .build(this)
    }

    override fun getExploreFragment(): Fragment {
        return MyCustomExploreFragment.newInstance()
    }
}