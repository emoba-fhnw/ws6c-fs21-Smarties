package fhnw.ws6c.theapp.model

import android.media.Image
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import fhnw.ws6c.theapp.data.Category
import fhnw.ws6c.theapp.data.Drink
import fhnw.ws6c.theapp.data.Ingredient
import fhnw.ws6c.theapp.data.RecipeStep
import fhnw.ws6c.theapp.data.services.RemoteRequestService
import fhnw.ws6c.theapp.data.services.RemoteImageService
import fhnw.ws6c.theapp.ui.theme.MyColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.Normalizer
import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fhnw.ws6c.theapp.RecListener
import java.util.*
import androidx.activity.ComponentActivity
import fhnw.ws6c.theapp.ui.theme.MySvgs

class CocktailModel(val remoteRequestService: RemoteRequestService, val remoteImageService : RemoteImageService, val context : ComponentActivity) {
    var isLoading       by mutableStateOf(false)

    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO) //for loding data asynchrounously

    var currentScreen by mutableStateOf(Screen.CATEGORY_SCREEN)
    var darkTheme   by mutableStateOf(true)

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


    fun getColor(myColors: MyColors) : Brush {
        return if(darkTheme){
            myColors.dark_color
        }else{
            myColors.light_color
        }
    }

    fun getSvg(mySvgs : MySvgs) : Int{
        return if(darkTheme){
            mySvgs.dark_svg
        }else{
            mySvgs.light_svg
        }
    }



    //*************************************************************************************************************
    //Speech

    var isRecording = mutableStateOf(false)

    var audio_text = mutableStateOf("")

    var number = 0
    var printed_text = mutableStateOf("")


    fun recording(){
        grantMicrophoneAccess()
        grantAvailabilityOfSpeechRecognizer()
        startListening()
        isRecording.value = true
    }

    fun grantMicrophoneAccess() {

        var isGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

        var text = "Wenn du die Sprachsteuerung verwenden möchtest brauchen wir die Berechtigung dein Microphon verwenden zu dürfen"

        if(!isGranted){
            Toast.makeText(context,text, Toast.LENGTH_SHORT).show()

            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                0
            )
        }
    }

    fun grantAvailabilityOfSpeechRecognizer(){
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            val appPackageName = "com.google.android.googlequicksearchbox"
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
        println(SpeechRecognizer.isRecognitionAvailable(context))
    }

    fun startListening(){
        var sp : SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        sp.setRecognitionListener(RecListener(this))

        val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")

        sp.startListening(i)
    }

    fun onTextResult(){
        if(audio_text.value.toLowerCase().contains("back")){
            if (currentRecipeStepIndex > 0) {
                currentRecipeStepIndex--
            }
        }
        else if(audio_text.value.toLowerCase().contains("next")){

            if(currentScreen == Screen.TUTORIAL_SCREEN){
                currentScreen = Screen.RECIPE_STEPS_SCREEN
            }
            else if(currentScreen == Screen.RECIPE_STEPS_SCREEN){
                if (currentRecipeStepIndex < recipeSteps.size - 1) {
                    currentRecipeStepIndex++
                } else {
                    currentScreen = Screen.DRINK_COMPLETED_SCREEN
                    currentRecipeStepIndex = 0
                }
            }

        }

        if(!audio_text.value.toLowerCase().contains("stop") && (currentScreen == Screen.RECIPE_STEPS_SCREEN || currentScreen == Screen.TUTORIAL_SCREEN)){
            recording()
        }
    }


}