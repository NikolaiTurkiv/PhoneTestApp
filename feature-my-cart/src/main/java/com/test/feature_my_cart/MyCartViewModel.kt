package com.test.feature_my_cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.repository_phones.domain.CartInfoResult
import com.test.repository_phones.domain.PhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MyCartViewModel @Inject constructor(
    private val phoneUseCase: PhoneUseCase
) : ViewModel() {

    private val _cartInfoLD = MutableLiveData<CartInfoResult>()
    val cartInfoLD: LiveData<CartInfoResult> get() = _cartInfoLD

    fun cartInfo() {
        phoneUseCase.cartInfo.observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _cartInfoLD.postValue(it)
            }, {
                Log.d("INFOCART", it.message.toString())
            })
    }
}
