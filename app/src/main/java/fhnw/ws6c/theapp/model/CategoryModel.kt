package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import fhnw.ws6c.theapp.data.Category

class CategoryModel {
    val searchedCategoryResults : List<Category>    by mutableStateOf(emptyList())

    val choosenCategory : Category                  by mutableStateOf(Category())
}