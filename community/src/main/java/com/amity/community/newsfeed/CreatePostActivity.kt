package com.amity.community.newsfeed

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.amity.community.R
import com.amity.socialcloud.uikit.common.base.AmityBaseToolbarFragmentContainerActivity
import com.amity.socialcloud.uikit.community.newsfeed.fragment.AmityPostCreatorFragment

class CreatePostActivity : AmityBaseToolbarFragmentContainerActivity() {

    override fun initToolbar() {
        getToolBar()?.setLeftDrawable(
            ContextCompat.getDrawable(this, R.drawable.amity_ic_cross)
        )
        getToolBar()?.setLeftString(getString(
            R.string.amity_my_timeline))
    }

    override fun getContentFragment(): Fragment {
        return AmityPostCreatorFragment.newInstance().onMyFeed()
            .build(this)
    }

}