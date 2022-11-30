package com.test.feature_hotsales

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
class HotSalesViewModel @Inject constructor(
    private val phoneUseCase: PhoneUseCase
): ViewModel() {

    private val _hotSalesLd = MutableLiveData<List<FullInfoResult.HomeStoreResult>>()
    val hotSalesLD: LiveData<List<FullInfoResult.HomeStoreResult>> get() = _hotSalesLd

    private val _filtersVisibility = MutableLiveData<Boolean>()
    val filtersVisibility:LiveData<Boolean> get() = _filtersVisibility

    fun getHotSales(){
        phoneUseCase.hotSales
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                   _hotSalesLd.postValue(it)
            },{
                Log.d("Error",it.message.toString())
            })
    }

    fun filtersVisibility(isVisible: Boolean){
        _filtersVisibility.value = isVisible
    }

}