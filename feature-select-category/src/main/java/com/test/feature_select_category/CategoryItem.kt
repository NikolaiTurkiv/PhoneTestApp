package com.test.feature_select_category

data class CategoryItem(
    val title:String,
    var isClick: Boolean,
    val iconNotClick: Int,
    val iconClick: Int
)