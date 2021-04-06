package fhnw.ws6c.theapp.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.layout.*
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
import fhnw.ws6c.theapp.RecListener
import fhnw.ws6c.theapp.model.TheModel
import java.util.*
import android.net.Uri


@Composable
fun AppUI(model : TheModel){
    with(model){
        Column() {
            Box(contentAlignment = Alignment.Center,
                modifier         = Modifier.fillMaxWidth()
            ){
                Text(text  = title,
                    style = TextStyle(fontSize = 42.sp))
            }
            IconButton(onClick = { recording(model) }) {
                Icon(imageVector = Icons.Filled.Mic, contentDescription = "")
            }
            Text(text = text.value, style = TextStyle(fontSize = 32.sp))
        }
    }
}

fun recording(model : TheModel){
    if (!SpeechRecognizer.isRecognitionAvailable(model.context)) {
        val appPackageName = "com.google.android.googlequicksearchbox"
        try {
            model.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
            model.context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }


    println(SpeechRecognizer.isRecognitionAvailable(model.context))
    var sp : SpeechRecognizer  = SpeechRecognizer.createSpeechRecognizer(model.context)

    sp.setRecognitionListener(RecListener(model))

    val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")

    sp.startListening(i)
}
