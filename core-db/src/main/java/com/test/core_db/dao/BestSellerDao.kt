package com.test.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.core_db.entities.BestSellerEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface BestSellerDao {

    @Query("SELECT * FROM best_seller")
    fun getBestSellers(): Single<List<BestSellerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBestSellers(bestSeller: List<BestSellerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBestSeller(bestSeller:BestSellerEntity)

    @Query("DELETE FROM best_seller")
    fun removeAllBestSellers()

}
