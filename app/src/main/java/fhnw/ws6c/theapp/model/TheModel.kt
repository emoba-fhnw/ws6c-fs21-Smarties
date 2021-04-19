package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.ws6c.theapp.data.Drink
import fhnw.ws6c.theapp.data.services.RemoteCategoryService
import fhnw.ws6c.theapp.data.services.RemoteImageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TheModel(val remoteCategoryService: RemoteCategoryService, val remoteImageService : RemoteImageService) {
    val title = "Hello ws6C"
    var isLoading       by mutableStateOf(false)

    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO) //for loding data asynchrounously

    val category_model = CategoryModel()


    fun loadDrinksOfChoosenCategoryAsync(){
        isLoading = true;
        category_model.jsonString = ""
        modelScope.launch{
            category_model.jsonString = remoteCategoryService.requestCategoryJson()
            category_model.drinksOfChoosenCategory = remoteCategoryService.convertJsonToListOfCategoryDrinks(remoteCategoryService.requestCategoryJson())
        }
        isLoading = false
    }

    fun loadDrinkImgAsync(drink : Drink){
        isLoading = true
        modelScope.launch {
            drink.img = remoteImageService.requestImageBitmap(drink.img_url)
            drink.preview_img = remoteImageService.requestImageBitmap(drink.preview_img_url)
        }
        isLoading = false
    }
}