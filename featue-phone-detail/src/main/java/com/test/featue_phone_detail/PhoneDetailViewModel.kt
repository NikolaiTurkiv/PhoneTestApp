package com.test.featue_phone_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.repository_phones.domain.InfoAboutPhoneResult
import com.test.repository_phones.domain.PhoneUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PhoneDetailViewModel @Inject constructor(
    private val phoneUseCase: PhoneUseCase,
    private val phoneDetailNavigator: PhoneDetailNavigator
) : ViewModel() {

    private val _infoAbouPhoneLd = MutableLiveData<InfoAboutPhoneResult>()
    val infoAboutPhoneLD: LiveData<InfoAboutPhoneResult> get() = _infoAbouPhoneLd

    private val _colorsListLd = MutableLiveData<List<ColorItem>>()
    val colorsListLd: LiveData<List<ColorItem>> get() = _colorsListLd

    private val _phoneCapacityListLd = MutableLiveData<List<CapacityItem>>()
    val phoneCapacityListLd: LiveData<List<CapacityItem>> get() = _phoneCapacityListLd

    private val _isFavoriteLd = MutableLiveData<Boolean>()
    val isFavoriteLd: LiveData<Boolean> get() = _isFavoriteLd


    private var phoneColors: List<ColorItem>? = null
    private var phonesCapacitySizes: List<CapacityItem>? = null

    private var isFavorite = false

    fun infoAboutPhone() {
        phoneUseCase.infoAboutPhone.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isFavorite = it.isFavorites
                 _infoAbouPhoneLd.postValue(it)
                _isFavoriteLd.postValue(it.isFavorites)
            }, {
                Log.d("Error", it.message.toString())
            })
    }

    fun phoneColors(colors: List<String>) {

        val colorItems = colors.mapIndexed{ index, color ->
            if(index == 0){
                ColorItem(color,true)
            }else{
                ColorItem(color,false)
            }
        }
        phoneColors = colorItems

        _colorsListLd.value = colorItems

    }

    fun phoneColorClick(item: ColorItem){

        phoneColors?.forEach {
            it.isClicked = it.color == item.color
        }

        phoneColors.let{
            _colorsListLd.value = it
        }
    }

    fun phoneCapacity(phoneMemories: List<String>){
        val memoryItems = phoneMemories.mapIndexed { index, memorySize ->
            if(index == 0){
                CapacityItem(memorySize,true)
            }else{
                CapacityItem(memorySize,false)
            }
        }

        phonesCapacitySizes = memoryItems
        _phoneCapacityListLd.value = memoryItems
    }

    fun phoneCapacityClick(item: CapacityItem){

        phonesCapacitySizes?.forEach {
            it.isClicked = it.capacity == item.capacity
        }

        phonesCapacitySizes?.let{
            _phoneCapacityListLd.value = it
        }

    }

    fun setFavorite(isFavorite: Boolean){
        this.isFavorite = isFavorite
        _isFavoriteLd.value = isFavorite
    }

    fun getFavorite(): Boolean{
        return isFavorite
    }

    fun navigateToCart(){
        phoneDetailNavigator.goToCart()
    }



}