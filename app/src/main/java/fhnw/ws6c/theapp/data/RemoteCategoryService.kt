package fhnw.ws6c.theapp.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

class RemoteCategoryService {

    private val baseURL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?"

    fun requestCategoryList() : String {

        try {
            var url = URL("${baseURL}" + "c=Cocktail")

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


}