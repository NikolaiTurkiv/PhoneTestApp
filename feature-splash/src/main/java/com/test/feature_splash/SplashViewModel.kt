package com.test.feature_splash

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.repository_phones.domain.PhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: SplashNavigator,
    private val phoneUseCase: PhoneUseCase,
): ViewModel() {

    private fun navigate(){
        navigator.navigateToHome()
    }

      fun loadData(){
          /*
          * задержку выставил,что показать сплэш подольше
          * */
        phoneUseCase.fullInfo
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .delaySubscription(1,TimeUnit.SECONDS)
            .subscribe({
                navigate()
            },{
                Log.d("Error",it.message.toString())
            })
    }

}