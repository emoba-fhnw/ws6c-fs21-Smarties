package fhnw.ws6c.theapp.data

import org.json.JSONObject
import java.net.URL

class RemoteCategoryService {

    val baseURL = "https://www.thecocktaildb.com"
    val baseSearchURL = "api/json/v1/1/filter.php?c="
    val baseSearchWord = "Cocktail"

    fun searchAPIRequest(searchParam: String) : String {
        var requestURL = URL("$baseURL/$baseSearchURL$baseSearchWord")
        return requestURL.readText()
    }

    fun loadCategory(jsonObject: JSONObject) : List<Category> {
        var data = jsonObject.optJSONArray("data")
        var categoryList = ArrayList<Category>()

        if(data == null) {
            return categoryList
        }

        for(x in 0 until data.length()) {
            var CategoryModel = Category(data.getJSONObject(x))
            categoryList.add(CategoryModel)
        }
        return categoryList
    }

}