package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.ws6c.theapp.data.Drink
import fhnw.ws6c.theapp.data.services.RemoteRequestService
import fhnw.ws6c.theapp.data.services.RemoteImageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TheModel(val remoteRequestService: RemoteRequestService, val remoteImageService : RemoteImageService) {
    var isLoading       by mutableStateOf(false)

    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO) //for loding data asynchrounously

    val category_model = CategoryModel()
    val drink_model = DrinkModel()

    var currentScreen by mutableStateOf(Screen.CATEGORY_SCREEN)
    var darkTheme   by mutableStateOf(false)
        private set

    var currentDrink : Drink by mutableStateOf(Drink())

    //**********************************************************************************************
    //functions

    fun loadDrinksOfChoosenCategoryAsync(){
        isLoading = true;
        category_model.jsonString = ""
        modelScope.launch{
            category_model.jsonString = remoteRequestService.requestJson(DataModifier.FilterDrinksByCategory)
            category_model.drinksOfChoosenCategory = remoteRequestService.convertJsonToListOfCategoryDrinks(remoteRequestService.requestJson(DataModifier.FilterDrinksByCategory))
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

    fun loadAllDrinkDetailsAsync(){
        isLoading = true
        modelScope.launch {
            drink_model.jsonString = remoteRequestService.requestJson(DataModifier.LookUpDrinkDetailsById)
        }
        isLoading = false
    }
}