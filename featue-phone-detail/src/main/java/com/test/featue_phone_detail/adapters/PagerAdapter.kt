package com.test.featue_phone_detail.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.featue_phone_detail.EmptyFragment
import com.test.featue_phone_detail.ProductDetailsShop
import com.test.repository_phones.domain.InfoAboutPhoneResult

class PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    var infoAboutPhone : InfoAboutPhoneResult? = null

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProductDetailsShop(infoAboutPhone)
            else -> EmptyFragment()
        }

    }

}