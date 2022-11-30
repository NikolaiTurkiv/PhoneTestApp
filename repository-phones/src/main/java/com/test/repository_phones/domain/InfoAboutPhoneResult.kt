package com.test.repository_phones.domain

import com.test.network.data.response.InfoAboutPhoneResponse

data class InfoAboutPhoneResult(
    val cpu: String?,
    val camera: String,
    val capacity: List<String>,
    val color: List<String>,
    val id: String,
    val images: List<String>,
    val isFavorites: Boolean,
    val price: Int,
    val rating: Double,
    val sd: String,
    val ssd: String,
    val title: String
)

fun InfoAboutPhoneResponse.toInfoAboutPhoneResult(): InfoAboutPhoneResult {
    return InfoAboutPhoneResult(
        cpu = CPU,
        camera = camera,
        capacity = capacity,
        color = color,
        id = id,
        images = images,
        isFavorites = isFavorites,
        price = price,
        rating = rating,
        sd = sd,
        ssd = ssd,
        title = title
    )
}