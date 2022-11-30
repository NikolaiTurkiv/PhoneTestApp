package com.test.network.data.response

import com.google.gson.annotations.SerializedName

data class FullInfoResponse(
    @SerializedName("best_seller")
    val bestSeller: List<BestSeller>,
    @SerializedName("home_store")
    val homeStore: List<HomeStore>
    ){

    data class BestSeller(
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_favorites")
        val isFavorites: Boolean = false,
        @SerializedName("title")
        val title: String,
        @SerializedName("price_without_discount")
        val priceWithoutDiscount: String,
        @SerializedName("discount_price")
        val discountPrice: String,
        @SerializedName("picture")
        val picture: String,
    )

    data class HomeStore(
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_new")
        val isNew: Boolean = false,
        @SerializedName("title")
        val title: String,
        @SerializedName("subtitle")
        val subtitle: String,
        @SerializedName("picture")
        val picture: String,
        @SerializedName("is_buy")
        val isBuy: Boolean = false
    )
}
