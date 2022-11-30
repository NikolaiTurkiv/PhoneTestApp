package com.test.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.core_db.dao.BestSellerDao
import com.test.core_db.dao.HomeStoreDao
import com.test.core_db.entities.BestSellerEntity
import com.test.core_db.entities.HomeStoreEntity

@Database(entities = [BestSellerEntity::class,HomeStoreEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bestSellerDao(): BestSellerDao
    abstract fun homeStoreDao(): HomeStoreDao
}