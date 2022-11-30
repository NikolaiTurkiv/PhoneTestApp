package com.test.testapp.navigation

import androidx.navigation.NavController
import com.test.feature_splash.SplashFragmentDirections
import com.test.feature_splash.SplashNavigator
import com.test.testapp.R

class SplashNavigatorImpl: SplashNavigator {

    override fun navigateToHome() {
        navController?.navigate(R.id.action_splash_to_home_fragment)
    }

    override var navController: NavController? = null

}