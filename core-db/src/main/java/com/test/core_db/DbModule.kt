package com.test.core_db

import android.content.Context
import androidx.room.Room
import com.test.core_db.dao.BestSellerDao
import com.test.core_db.dao.HomeStoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    private const val DB_NAME = "test_app_database"

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context:Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java, DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideBestSellerDao(appDatabase: AppDatabase): BestSellerDao = appDatabase.bestSellerDao()

    @Provides
    @Singleton
    fun provideHomeStoreDao(appDatabase: AppDatabase): HomeStoreDao = appDatabase.homeStoreDao()

}
