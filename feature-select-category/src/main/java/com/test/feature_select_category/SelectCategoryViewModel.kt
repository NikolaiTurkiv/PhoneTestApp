package com.test.feature_select_category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectCategoryViewModel @Inject constructor(): ViewModel() {

      var categoryList = mutableListOf<CategoryItem>(
        CategoryItem("Phone",true, com.test.core_resources.R.drawable.phone,
            com.test.core_resources.R.drawable.phone_click),
        CategoryItem("Computer",false, com.test.core_resources.R.drawable.computer,
            com.test.core_resources.R.drawable.computer_click),
        CategoryItem("Health",false, com.test.core_resources.R.drawable.health,
            com.test.core_resources.R.drawable.health_click),
        CategoryItem("Books",false, com.test.core_resources.R.drawable.books,
            com.test.core_resources.R.drawable.books_click),
        CategoryItem("Something\nelse",false, com.test.core_resources.R.drawable.question_mark,
            com.test.core_resources.R.drawable.question_mark_click),
    )


    private val _categoryListLD = MutableLiveData<List<CategoryItem>>()
    val categoryListLD : LiveData<List<CategoryItem>> get() =  _categoryListLD

    fun updateList(item: CategoryItem){
        categoryList.forEach {
            it.isClick = it.title == item.title
        }
        _categoryListLD.value = categoryList
    }
}
