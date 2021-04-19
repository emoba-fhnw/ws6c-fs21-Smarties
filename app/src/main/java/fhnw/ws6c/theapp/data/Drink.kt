package fhnw.ws6c.theapp.data

import androidx.compose.runtime.mutableStateOf
import org.json.JSONObject


class Drink {
    val name    : String
    val img_url : String
    val id      : Int


    constructor(json : JSONObject){
        name = if(json.has("strDrink")){
            json.getString("strDrink")
        }else{
            ""
        }
        img_url = if(json.has("strDrinkThumb")){
            json.getString("strDrinkThumb")
        }else{
            ""
        }
        id = if(json.has("idDrink")){
            json.getInt("idDrink")
        }else{
            -1
        }

    }
}