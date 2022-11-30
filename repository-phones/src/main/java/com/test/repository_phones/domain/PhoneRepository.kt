package com.test.repository_phones.domain

import io.reactivex.rxjava3.core.Single

interface PhoneRepository {
    fun fullInfo(): Single<FullInfoResult>
    fun setFavorite(bestseller: FullInfoResult.BestSellerResult)
    fun hotSales(): Single<List<FullInfoResult.HomeStoreResult>>
    fun bestSellers(): Single<List<FullInfoResult.BestSellerResult>>
    fun infoAboutPhone():Single<InfoAboutPhoneResult>
    fun cartInfo(): Single<CartInfoResult>
}