package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import fhnw.ws6c.theapp.data.Category
import fhnw.ws6c.theapp.data.Drink

class CategoryModel {
    var drinksOfChoosenCategory : List<Drink>   by mutableStateOf(emptyList())
    var jsonString : String                     by mutableStateOf(String()) //todo delete when it's working

}