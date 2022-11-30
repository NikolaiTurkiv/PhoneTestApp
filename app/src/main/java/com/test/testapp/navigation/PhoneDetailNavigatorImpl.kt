package com.test.testapp.navigation

import androidx.navigation.NavController
import com.test.featue_phone_detail.PhoneDetailNavigator
import com.test.testapp.R

class PhoneDetailNavigatorImpl : PhoneDetailNavigator{

    override fun goToCart() {
        navController?.navigate(R.id.action_phone_fragment_to_cart_fragment)
    }

    override var navController: NavController? = null
}