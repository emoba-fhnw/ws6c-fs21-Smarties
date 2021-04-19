package fhnw.ws6c.theapp.data
import org.json.JSONObject

class Category(json: JSONObject) {
    val drinks = json.getJSONObject("drinks")

    val name = drinks.getString("strDrink")
    val img = drinks.getString("strDrinkThumb")
    val id = drinks.getInt("idDrink")

    constructor(jsonString: String) : this(JSONObject(jsonString))

    fun asJson(): String {
        return "{" +
                "\"drinks\": $drinks," +
                "\"strDrink\": \"$name\"," +
                "\"strDrinkThumb\": \"$img\"," +
                 "\"idDrink\": \"$id\"," +
                "}" +
                "}"
    }
}