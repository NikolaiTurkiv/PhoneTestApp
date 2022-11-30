package com.test.testapp.navigation.di

import com.test.android_utils.navigation.NavControllerHolder
import com.test.featue_phone_detail.PhoneDetailNavigator
import com.test.feature_home.HomeNavigator
import com.test.feature_splash.SplashNavigator
import com.test.testapp.navigation.HomeNavigatorImpl
import com.test.testapp.navigation.PhoneDetailNavigatorImpl
import com.test.testapp.navigation.SplashNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideSplashNavigator(): SplashNavigator = SplashNavigatorImpl()

    @Provides
    @Singleton
    fun provideHomeNavigator(): HomeNavigator = HomeNavigatorImpl()

    @Provides
    @Singleton
    fun providePhoneDetailedNavigator(): PhoneDetailNavigator = PhoneDetailNavigatorImpl()

    @Provides
    @Singleton
    fun provideNavControllerHolders(
        splashNavigator: SplashNavigator,
        homeNavigator: HomeNavigator,
        phoneDetailedNavigator: PhoneDetailNavigator
    ):Array<NavControllerHolder> =
        arrayOf(
            splashNavigator,
            homeNavigator,
            phoneDetailedNavigator
        )
}
