package com.test.network.data.response

data class CartInfoResponse(
    val basket: List<Basket>,
    val delivery: String,
    val id: Int,
    val total: Int
) {
    data class Basket(
        val id: Int,
        val images: String,
        val price: Int,
        val title: String
    )
}
