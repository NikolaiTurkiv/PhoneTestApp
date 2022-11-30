package com.test.repository_phones.domain

import com.test.network.data.response.CartInfoResponse

data class CartInfoResult(
    val basket: MutableList<CartInfoResult.Basket>,
    val delivery: String,
    val id: Int,
    val total: Int
) {
    data class Basket(
        val id: Int,
        val images: String,
        val price: Int,
        val title: String,
    )
}

fun CartInfoResponse.Basket.toCartInfoResultBasket(): CartInfoResult.Basket {
    return CartInfoResult.Basket(id = id, images = images, price = price, title = title)
}

fun CartInfoResponse.toCartInfoResult(): CartInfoResult{
    return CartInfoResult(
        basket = this.basket.map { it.toCartInfoResultBasket() }.toMutableList(),
        delivery = this.delivery,
        id = this.id,
        total = this.total
    )
}