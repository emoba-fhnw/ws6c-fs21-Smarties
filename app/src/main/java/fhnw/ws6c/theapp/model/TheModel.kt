package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.ws6c.theapp.TheApp
import fhnw.ws6c.theapp.data.Category
import fhnw.ws6c.theapp.data.Drink
import fhnw.ws6c.theapp.data.services.RemoteRequestService
import fhnw.ws6c.theapp.data.services.RemoteImageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONObject

class TheModel(val remoteRequestService: RemoteRequestService, val remoteImageService : RemoteImageService) {
    var isLoading       by mutableStateOf(false)

    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO) //for loding data asynchrounously

    var currentScreen by mutableStateOf(Screen.CATEGORY_SCREEN)
    var darkTheme   by mutableStateOf(false)
        private set

    var currentDrink : Drink by mutableStateOf(Drink())
    val currentCategory : Category by mutableStateOf(Category())

    //**********************************************************************************************
    //functions

    fun loadDrinksOfChoosenCategoryAsync(){
        isLoading = true;
        modelScope.launch{
            currentCategory.listOfDrinks = remoteRequestService.convertJsonToListOfCategoryDrinks(remoteRequestService.requestJson(DataModifier.FilterDrinksByCategory))
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
            val jsonString = remoteRequestService.requestJson(DataModifier.LookUpDrinkDetailsById, currentDrink.id.toString())
            println(jsonString)
            val drink = JSONObject(jsonString).getJSONArray("drinks").getJSONObject(0)
            currentDrink = Drink(drink)
        }
        isLoading = false
    }
}