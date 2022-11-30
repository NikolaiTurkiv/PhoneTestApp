package com.test.network.data

import com.test.network.data.response.CartInfoResponse
import com.test.network.data.response.FullInfoResponse
import com.test.network.data.response.InfoAboutPhoneResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NetworkApi {
    companion object{
        private const val FULL_INFO = "654bd15e-b121-49ba-a588-960956b15175"
        private const val PHONE_INFO = "6c14c560-15c6-4248-b9d2-b4508df7d4f5"
        private const val CART_INFO = "53539a72-3c5f-4f30-bbb1-6ca10d42c149"
    }

    @GET(FULL_INFO)
    fun fullInfo(): Single<FullInfoResponse>

    @GET(PHONE_INFO)
    fun phoneInfo(): Single<InfoAboutPhoneResponse>

    @GET(CART_INFO)
    fun cartInfo(): Single<CartInfoResponse>
}