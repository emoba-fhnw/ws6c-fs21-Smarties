package fhnw.ws6c.theapp.model

import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf


class TheModel(var context : ComponentActivity) {
    var title = "Hello ws6C"

    var text = mutableStateOf("")

}