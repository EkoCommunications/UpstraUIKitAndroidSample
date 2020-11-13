package com.ekoapp.community.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ekoapp.community.R


class MyCustomExploreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_custom_explore, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MyCustomExploreFragment()
    }
}