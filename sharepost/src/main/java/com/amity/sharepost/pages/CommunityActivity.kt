package com.amity.sharepost.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.amity.sharepost.R
import com.amity.socialcloud.uikit.community.detailpage.AmityCommunityPageFragment
import com.amity.socialcloud.uikit.community.utils.EXTRA_PARAM_COMMUNITY_ID

class CommunityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val communityId = intent.getStringExtra(EXTRA_PARAM_COMMUNITY_ID) ?: ""
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getCommunityFragment(communityId)
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }

    }


    private fun getCommunityFragment(communityId: String): Fragment {
        return AmityCommunityPageFragment.newInstance(communityId)
            .build(this)
    }

}