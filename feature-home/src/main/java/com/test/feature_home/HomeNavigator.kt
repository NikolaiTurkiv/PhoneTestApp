package com.test.feature_home

import com.test.android_utils.navigation.NavControllerHolder

interface HomeNavigator: NavControllerHolder {
    fun openPhoneDetail()
    fun goToCart()
}