package com.test.testapp.navigation

import androidx.navigation.NavController
import com.test.feature_home.HomeNavigator
import com.test.testapp.R

class HomeNavigatorImpl: HomeNavigator {

    override fun openPhoneDetail() {
        navController?.navigate(R.id.action_home_phone_detail)
    }

    override fun goToCart() {
            navController?.navigate(R.id.action_home_fragment_to_cart_fragment)
    }

    override var navController: NavController? = null
}