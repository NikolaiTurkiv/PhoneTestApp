package com.test.core_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "best_seller")
data class BestSellerEntity(
    @PrimaryKey
    val id: Int,
    val isFavorites: Boolean = false,
    val title: String,
    val priceWithoutDiscount: String,
    val discountPrice: String,
    val picture: String,
)