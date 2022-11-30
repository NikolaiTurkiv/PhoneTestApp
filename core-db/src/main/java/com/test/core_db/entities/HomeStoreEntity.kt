package com.test.core_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_store")
data class HomeStoreEntity(
    @PrimaryKey
    val id: Int,
    val isNew: Boolean = false,
    val title: String,
    val subtitle: String,
    val picture: String,
    val isBuy: Boolean = false
)