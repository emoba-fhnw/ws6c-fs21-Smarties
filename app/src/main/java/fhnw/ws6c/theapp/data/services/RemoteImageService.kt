package fhnw.ws6c.theapp.data.services

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.ws6c.theapp.data.defaultImage
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RemoteImageService {

    fun requestImageBitmap(url: String): ImageBitmap {
        if (url.isNotEmpty() && !url.equals("null")) {
            val connection = URL(url).openConnection() as HttpsURLConnection //connect
            connection.connect()

            val inputStram = connection.inputStream //open stream
            val allBites = inputStram.readBytes()
            inputStram.close() //close stream

            val bitmap = BitmapFactory.decodeByteArray(allBites, 0, allBites.size)
            return bitmap.asImageBitmap()
        } else {
            return defaultImage()
        }
    }
}