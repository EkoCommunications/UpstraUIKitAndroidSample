package com.amity.community.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.amity.community.R
import com.amity.socialcloud.uikit.community.explore.fragments.AmityCommunityExplorerFragment
import com.amity.socialcloud.uikit.community.home.fragments.AmityCommunityHomePageFragment
import com.amity.socialcloud.uikit.community.home.listener.AmityExploreFragmentFragmentDelegate

class MyCustomCommunityHomePageActivity : AppCompatActivity(),
    AmityExploreFragmentFragmentDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_custom_community_home_page)
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getCommunityHomeFragment()
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }

    }

    private fun getCommunityHomeFragment(): Fragment {
        return AmityCommunityHomePageFragment.newInstance()
            .exploreFragmentDelegate(this)
            .build(this)
    }

    override fun getExploreFragment(): AmityCommunityExplorerFragment {
        return AmityCommunityExplorerFragment.newInstance()
            .build(this)
    }

}