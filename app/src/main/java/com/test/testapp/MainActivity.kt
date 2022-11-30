package com.test.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.test.android_utils.navigation.NavControllerHolder
import com.test.repository_phones.domain.PhoneUseCase
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    @Inject
    lateinit var navControllerHolders: Array<NavControllerHolder>

    private val navController: NavController by lazy {
        navHostFragment.findNavController()
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavControllerToNavigators()

    }

    private fun setNavControllerToNavigators() {
        navControllerHolders.forEach { it.navController = navController }
    }
}