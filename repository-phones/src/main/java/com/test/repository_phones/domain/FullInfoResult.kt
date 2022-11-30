package com.test.repository_phones.domain

import com.test.core_db.entities.BestSellerEntity
import com.test.core_db.entities.HomeStoreEntity
import com.test.network.data.response.FullInfoResponse

data class FullInfoResult(
    val bestSeller: List<BestSellerResult>,
    val homeStore: List<HomeStoreResult>
) {
    data class BestSellerResult(
        val id: Int,
        val isFavorites: Boolean = false,
        val title: String,
        val priceWithoutDiscount: String,
        val discountPrice: String,
        val picture: String,
    )

    data class HomeStoreResult(
        val id: Int,
        val isNew: Boolean = false,
        val title: String,
        val subtitle: String,
        val picture: String,
        val isBuy: Boolean = false
    )
}

fun FullInfoResponse.toFullInfoResult(): FullInfoResult {
    return FullInfoResult(
        bestSeller = this.bestSeller.map { it.toBestsellerResult() },
        homeStore = this.homeStore.map { it.toHomeStoreResult() }
    )
}

fun FullInfoResponse.HomeStore.toHomeStoreResult(): FullInfoResult.HomeStoreResult {
    return FullInfoResult.HomeStoreResult(
        id = this.id,
        isNew = this.isNew,
        title = this.title,
        subtitle = this.subtitle,
        picture = this.picture,
        isBuy = this.isBuy
    )
}

fun FullInfoResponse.BestSeller.toBestsellerResult(): FullInfoResult.BestSellerResult {
    return FullInfoResult.BestSellerResult(
        id = this.id,
        isFavorites = this.isFavorites,
        title = this.title,
        priceWithoutDiscount = this.priceWithoutDiscount,
        discountPrice = this.discountPrice,
        picture = this.picture
    )
}

fun FullInfoResponse.BestSeller.toBestSellerEntity(): BestSellerEntity {
    return BestSellerEntity(
        id = id,
        isFavorites = isFavorites,
        title = title,
        priceWithoutDiscount = priceWithoutDiscount,
        discountPrice = discountPrice,
        picture = picture
    )
}

fun FullInfoResponse.HomeStore.toHomeStoreEntity(): HomeStoreEntity {
    return HomeStoreEntity(
        id = id,
        isNew = isNew,
        title = title,
        subtitle = subtitle,
        picture = picture,
        isBuy = isBuy
    )
}

fun HomeStoreEntity.toHomeStoreResult(): FullInfoResult.HomeStoreResult {
    return FullInfoResult.HomeStoreResult(
        id = id,
        isNew = isNew,
        title = title,
        subtitle = subtitle,
        picture = picture,
        isBuy = isBuy
    )
}

fun BestSellerEntity.toBestsellerResult(): FullInfoResult.BestSellerResult {
    return FullInfoResult.BestSellerResult(
        id = id,
        isFavorites = isFavorites,
        title = title,
        priceWithoutDiscount = priceWithoutDiscount,
        discountPrice = discountPrice,
        picture = picture
    )
}

fun FullInfoResult.BestSellerResult.toBestSellerEntity(): BestSellerEntity {
    return BestSellerEntity(
        id = id,
        isFavorites = isFavorites,
        title = title,
        priceWithoutDiscount = priceWithoutDiscount,
        discountPrice = discountPrice,
        picture = picture
    )
}
