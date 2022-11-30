package com.test.feature_home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.repository_phones.domain.FullInfoResult
import com.test.repository_phones.domain.PhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val phoneUseCase: PhoneUseCase,
    private val homeNavigator: HomeNavigator
) : ViewModel(){

    private val _bestSellersLd = MutableLiveData<List<FullInfoResult.BestSellerResult>>()
    val bestSellersLD: LiveData<List<FullInfoResult.BestSellerResult>> get() = _bestSellersLd

    private val _filtersVisibility = MutableLiveData<Boolean>()
    val filtersVisibility: LiveData<Boolean> get() = _filtersVisibility

    private val _cartPhoneCount = MutableLiveData<Int>()
    val cartPhoneCount: LiveData<Int> get() = _cartPhoneCount

    fun setFavorites(bestSellerResult: FullInfoResult.BestSellerResult){
        phoneUseCase.setFavorite(
            FullInfoResult.BestSellerResult(
            id = bestSellerResult.id,
            isFavorites = !bestSellerResult.isFavorites,
            title = bestSellerResult.title,
            priceWithoutDiscount = bestSellerResult.priceWithoutDiscount,
            discountPrice = bestSellerResult.discountPrice,
            picture = bestSellerResult.picture
        ))
       getBestSellers()
    }

    fun getCartPhones(){
        phoneUseCase.cartInfo.observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _cartPhoneCount.postValue(it.basket.size)
            }, {
                Log.d("INFOCART", it.message.toString())
            })
    }

    fun getBestSellers(){
        phoneUseCase.bestSellers
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _bestSellersLd.postValue(it)
            },{
                Log.d("Error",it.message.toString())
            })
    }

    fun filtersVisibility(isVisible: Boolean){
        _filtersVisibility.value = isVisible
    }

    fun navigateToPhoneDetails(){
        homeNavigator.openPhoneDetail()
    }

    fun navigateToCart(){
        homeNavigator.goToCart()
    }


}