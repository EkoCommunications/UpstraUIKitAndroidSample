package com.ekoapp.sharepost.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekoapp.ekosdk.uikit.community.detailpage.EkoCommunityPageFragment
import com.ekoapp.ekosdk.uikit.community.utils.EXTRA_PARAM_COMMUNITY_ID
import com.ekoapp.sharepost.R

class CommunityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val communityId = intent.getStringExtra(EXTRA_PARAM_COMMUNITY_ID) ?: ""
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = getCommunityFragment(communityId)
            fragmentTransaction.replace(com.ekoapp.ekosdk.uikit.R.id.fragmentContainer, fragment)
            fragmentTransaction.commit()
        }

    }



    private fun getCommunityFragment(communityId: String): Fragment {
        return EkoCommunityPageFragment.Builder()
            .setCommunityId(communityId)
            .build(this)
    }

}