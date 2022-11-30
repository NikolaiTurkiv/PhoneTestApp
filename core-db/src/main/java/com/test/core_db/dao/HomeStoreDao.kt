package com.test.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
 import com.test.core_db.entities.HomeStoreEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface HomeStoreDao {

    @Query("SELECT * FROM home_store")
    fun getHomeStore(): Single<List<HomeStoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeStore(bestSeller: List<HomeStoreEntity>)

    @Query("DELETE FROM home_store")
    fun removeAllHomeStores()

}