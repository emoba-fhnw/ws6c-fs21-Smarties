package fhnw.ws6c.theapp.data
import org.json.JSONObject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

class Category {

    val listOfDrinks_URL : String

    //will be reloaded
    var listOfDrinks : List<Drink> by mutableStateOf(emptyList())


    constructor(json : JSONObject){
        listOfDrinks_URL = if(json.has("drinks")){
            json.getString("drinks")
        }else{
            ""
        }
    }

    constructor() : this(JSONObject())
}