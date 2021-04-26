package fhnw.ws6c.theapp.data.services

import fhnw.ws6c.theapp.data.Drink
import fhnw.ws6c.theapp.model.DataModifier
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

class RemoteRequestService {

    private val baseURL = "https://www.thecocktaildb.com/api/json/v1/1/"

    fun requestJson(dataModifier: DataModifier) : String {
        try {
            var url = URL("${baseURL}" + dataModifier.urlString)

            //connect
            val connection = url.openConnection() as HttpsURLConnection
            connection.connect()

            print(url)

            //read data
            val inputStream = connection.inputStream // open stream
            val reader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
            val jsonString = reader.readText()
            reader.close()
            inputStream.close() // close stream

            //return json
            return jsonString

        } catch (e : Exception){
            e.printStackTrace()
        }
        return ""
    }

    fun convertJsonToListOfCategoryDrinks(jsonString : String ): List<Drink> {
        val listOfDrinks = mutableListOf<Drink>()
        val json = JSONObject(jsonString)
        val nameOfArray = "drinks"

        if(json.has(nameOfArray)){
            val jsonArray = json.getJSONArray(nameOfArray) //convert json to array

            for(i in 0 until jsonArray.length()){
                val drink = Drink(jsonArray.getJSONObject(i)) //convert every array element to a drink
                listOfDrinks.add(drink)
            }
            return listOfDrinks
        }
        return emptyList()
    }
}