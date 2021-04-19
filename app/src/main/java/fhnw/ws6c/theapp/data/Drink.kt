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

    }
}