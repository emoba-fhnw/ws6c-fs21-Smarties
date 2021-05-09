package fhnw.ws6c.theapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.ws6c.theapp.data.Category
import fhnw.ws6c.theapp.data.Drink
import fhnw.ws6c.theapp.data.Ingredient
import fhnw.ws6c.theapp.data.RecipeStep
import fhnw.ws6c.theapp.data.services.RemoteRequestService
import fhnw.ws6c.theapp.data.services.RemoteImageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.Normalizer

class CocktailModel(val remoteRequestService: RemoteRequestService, val remoteImageService : RemoteImageService) {
    var isLoading       by mutableStateOf(false)

    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO) //for loding data asynchrounously

    var currentScreen by mutableStateOf(Screen.CATEGORY_SCREEN)
    var darkTheme   by mutableStateOf(true)
        private set

    var currentDrink : Drink by mutableStateOf(Drink())
    val currentCategory : Category by mutableStateOf(Category())

    var recipeSteps = mutableListOf<RecipeStep>()
    var currentRecipeStepIndex by mutableStateOf(0)
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

    fun loadIngredientImgAsync(ingredient : Ingredient){
        isLoading = true
        modelScope.launch {
            var ingredient_url_name = ingredient.name.substring(0,ingredient.name.length).replace(" ", "%20")
            ingredient.img_small = remoteImageService.requestImageBitmap("https://www.thecocktaildb.com/images/ingredients/" + ingredient_url_name + "-Small.png")
            ingredient.img_medium = remoteImageService.requestImageBitmap("https://www.thecocktaildb.com/images/ingredients/" + ingredient_url_name + "-Medium.png")
            ingredient.img_big = remoteImageService.requestImageBitmap("https://www.thecocktaildb.com/images/ingredients/" + ingredient_url_name + ".png")
        }
        isLoading = false
    }

    fun loadAllDrinkDetailsAsync(){
        isLoading = true
        modelScope.launch {
            val jsonString = remoteRequestService.requestJson(DataModifier.LookUpDrinkDetailsById, currentDrink.id.toString())
            val drink = JSONObject(jsonString).getJSONArray("drinks").getJSONObject(0)
            currentDrink = Drink(drink)
        }
        isLoading = false
    }

    fun fillRecipeSteps(){
        println("LoadingRecipeSteps")
        recipeSteps = emptyList<RecipeStep>().toMutableList()

        val sentenceList = currentDrink.instructions.substring(0,currentDrink.instructions.length-1).split(".").toMutableList() //convert string to list

        for(i in 0..sentenceList.size-1){
            sentenceList[i] = sentenceList[i] + ". "
        }


        val allIngredientsPerSentence = mutableListOf<List<Ingredient>>()
        var ingredientsOfCurrentSentence = mutableListOf<Ingredient>()

        val allMeassurementsPerSentence = mutableListOf<List<String>>()
        var meassurementsOfCurrentSentence = mutableListOf<String>()


        val allNotUsedIngredients = mutableListOf<Ingredient>()

        for(i in currentDrink.ingredients){
            allNotUsedIngredients.add(i)
        }

        for(s in sentenceList){
            for(i in 0..currentDrink.ingredients.size-1){
                val regex = ("\\b" + currentDrink.ingredients[i].name.unaccent().toLowerCase() + "\\b").toRegex()
                if(s.unaccent().toLowerCase().contains(regex)){
                    ingredientsOfCurrentSentence.add(currentDrink.ingredients[i])
                    meassurementsOfCurrentSentence.add(currentDrink.meassurements[i])
                    allNotUsedIngredients.remove(currentDrink.ingredients[i])
                }
            }
            allIngredientsPerSentence.add(ingredientsOfCurrentSentence)
            allMeassurementsPerSentence.add(meassurementsOfCurrentSentence)
            ingredientsOfCurrentSentence = mutableListOf<Ingredient>()
            meassurementsOfCurrentSentence = mutableListOf<String>()
        }


        //create RecipeSteps
        var string = ""
        var ingredientsIndex = 0

        if(allNotUsedIngredients.size == currentDrink.ingredients.size){
            recipeSteps.add(RecipeStep(currentDrink.meassurements, currentDrink.ingredients, currentDrink.instructions))
        }
        else{
            for(i in 0..allIngredientsPerSentence.size-1){
                if(!allIngredientsPerSentence[i].isEmpty() && !string.equals("")){
                    recipeSteps.add(RecipeStep(allMeassurementsPerSentence[ingredientsIndex].toMutableList(), allIngredientsPerSentence[ingredientsIndex].toMutableList(), string))
                    ingredientsIndex = i
                    string = ""
                }
                string += sentenceList[i]
            }
            recipeSteps.add(RecipeStep(allMeassurementsPerSentence[ingredientsIndex].toMutableList(), allIngredientsPerSentence[ingredientsIndex].toMutableList(), string))
        }
    }

    /**
     * @see https://stackoverflow.com/questions/51731574/removing-accents-and-diacritics-in-kotlin
     */
    private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    fun CharSequence.unaccent(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }

}