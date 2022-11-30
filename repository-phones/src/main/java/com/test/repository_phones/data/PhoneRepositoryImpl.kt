package com.test.repository_phones.data

import android.util.Log
import com.test.core_db.dao.BestSellerDao
import com.test.core_db.dao.HomeStoreDao
import com.test.network.data.NetworkApi
import com.test.repository_phones.domain.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class PhoneRepositoryImpl(
    private val api: NetworkApi,
    private val bestSellerDao: BestSellerDao,
    private val homeStoreDao: HomeStoreDao
) : PhoneRepository {
    override fun fullInfo(): Single<FullInfoResult> {
        return api.fullInfo().map { response ->

            bestSellerDao.insertBestSellers(response.bestSeller.map { it.toBestSellerEntity() })
            homeStoreDao.insertHomeStore(response.homeStore.map { it.toHomeStoreEntity() })

            response.toFullInfoResult()
        }
    }

    override fun setFavorite(bestseller: FullInfoResult.BestSellerResult) {
        Single.just(bestseller)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                bestSellerDao.insertBestSeller(bestseller.toBestSellerEntity())
            }, {

            })


    }

    override fun hotSales(): Single<List<FullInfoResult.HomeStoreResult>> {
        return homeStoreDao.getHomeStore().map { entities ->
            entities.map { it.toHomeStoreResult() }
        }
    }

    override fun bestSellers(): Single<List<FullInfoResult.BestSellerResult>> {
        return bestSellerDao.getBestSellers().map { entities ->
            entities.map { it.toBestsellerResult() }
        }
    }

    override fun infoAboutPhone(): Single<InfoAboutPhoneResult> {
        return api.phoneInfo().map { response ->
            response.toInfoAboutPhoneResult()
        }
    }

    override fun cartInfo(): Single<CartInfoResult> {
        return api.cartInfo().map {
            it.toCartInfoResult()
        }
    }
}
