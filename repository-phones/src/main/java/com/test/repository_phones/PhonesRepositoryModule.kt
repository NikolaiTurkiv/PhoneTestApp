package com.test.repository_phones

import com.test.core_db.dao.BestSellerDao
import com.test.core_db.dao.HomeStoreDao
import com.test.network.data.NetworkApi
import com.test.repository_phones.data.PhoneRepositoryImpl
import com.test.repository_phones.domain.PhoneRepository
import com.test.repository_phones.domain.PhoneUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhonesRepositoryModule {

    @Provides
    @Singleton
    fun providePhoneRepository(api: NetworkApi, bestSellerDao: BestSellerDao, homeStoreDao: HomeStoreDao
    ): PhoneRepository = PhoneRepositoryImpl(api,bestSellerDao, homeStoreDao)

    @Provides
    @Singleton
    fun providePhoneUseCase(phoneRepository: PhoneRepository) = PhoneUseCase(phoneRepository)

}
