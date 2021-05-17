package fhnw.ws6c.theapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap

class Ingredient {
    var name : String

    //will be reloaded
    var img_small : ImageBitmap by mutableStateOf(defaultImage())
    var img_medium : ImageBitmap by mutableStateOf(defaultImage())
    var img_big : ImageBitmap by mutableStateOf(defaultImage())

    constructor(name : String){
        this.name = name
    }
}