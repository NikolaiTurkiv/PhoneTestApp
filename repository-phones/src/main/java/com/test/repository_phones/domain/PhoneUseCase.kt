package com.test.repository_phones.domain

import com.test.network.data.response.FullInfoResponse

class PhoneUseCase(private val phoneRepository: PhoneRepository) {
    val fullInfo = phoneRepository.fullInfo()
    val hotSales = phoneRepository.hotSales()
    val bestSellers = phoneRepository.bestSellers()
    val infoAboutPhone = phoneRepository.infoAboutPhone()
    val cartInfo = phoneRepository.cartInfo()

    fun setFavorite(bestSeller: FullInfoResult.BestSellerResult){
        phoneRepository.setFavorite(bestSeller)
    }

}
