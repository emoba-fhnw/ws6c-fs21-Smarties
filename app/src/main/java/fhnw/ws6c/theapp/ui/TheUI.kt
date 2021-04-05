package fhnw.ws6c.theapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import fhnw.ws6c.MainActivity
import fhnw.ws6c.theapp.model.TheModel


@Composable
fun AppUI(model : MainActivity){
    with(model){
        Box(contentAlignment = Alignment.Center,
            modifier         = Modifier.fillMaxSize()
        ){
            Text(text  = title,
                 style = TextStyle(fontSize = 42.sp))
        }
        IconButton(onClick = { askSpeechInput() }) {
            Icon(imageVector = Icons.Filled.Mic, contentDescription = "")
        }
        //Text(text = onActRes())
    }
}
