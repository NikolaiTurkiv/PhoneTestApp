package com.test.testapp

import androidx.lifecycle.ViewModel
import com.test.repository_phones.domain.PhoneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val phoneUseCase: PhoneUseCase
): ViewModel() {

}