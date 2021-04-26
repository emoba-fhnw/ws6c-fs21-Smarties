package fhnw.ws6c.theapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import org.json.JSONObject


class Drink {
    val name    : String
    val img_url : String
    val preview_img_url : String
    val id      : Int

    //will be reloaded
    var img         : ImageBitmap by mutableStateOf(defaultImage())
    var preview_img : ImageBitmap by mutableStateOf(defaultImage())


    val glass           : String
    val instructions    : String
    val ingredients     : Set<String> = emptySet()
    val meassurements   : Set<String> = emptySet()


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
        preview_img_url = if(json.has("strDrinkThumb")){
            json.getString("strDrinkThumb") + "/preview"
        }else{
            ""
        }
        id = if(json.has("idDrink")){
            json.getInt("idDrink")
        }else{
            -1
        }

        glass = if(json.has("strGlass")){
            json.getString("strGlass")
        }else{
            ""
        }
        instructions = if(json.has("strInstructions")){
            json.getString("strInstructions")
        }else{
            ""
        }
//        var i = 1
//        while(i <= 15){
//            if(json.has("strIngredient$i")) {
//                if(json.getString("strIngredient$i").isNullOrEmpty()){
//                    ingredients.toMutableSet().add(json.getString("strIngredient$i"))
//                }
//                i ++
//            }
//        }
//        i = 0
//        while(i <= 15){
//            if(json.has("strMeasure$i")) {
//                if(json.getString("strMeasure$i").isNullOrEmpty()){
//                    meassurements.toMutableSet().add(json.getString("strMeasure$i"))
//                }
//                i ++
//            }
//        } //todo find a better way
    }

    constructor() : this(JSONObject())
}