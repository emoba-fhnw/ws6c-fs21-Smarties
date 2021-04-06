package fhnw.ws6c.theapp.model

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fhnw.ws6c.theapp.RecListener
import java.util.*


class TheModel(var context : ComponentActivity) {
    var title = "Hello ws6C"
    var isRecording = mutableStateOf(false)

    var audio_text = mutableStateOf("")

    var number = 0
    var printed_text = mutableStateOf("")


    fun recording(){
        grantMicrophoneAccess()
        grantAvailabilityOfSpeechRecognizer()
        startListening()
        isRecording.value = true
    }

    fun grantMicrophoneAccess() {

        var isGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED

        var text = "Wenn du die Sprachsteuerung verwenden möchtest brauchen wir die Berechtigung dein Microphon verwenden zu dürfen"

        if(!isGranted){
            Toast.makeText(context,text, Toast.LENGTH_SHORT).show()

            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                0
            )
        }
    }

    fun grantAvailabilityOfSpeechRecognizer(){
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            val appPackageName = "com.google.android.googlequicksearchbox"
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
        println(SpeechRecognizer.isRecognitionAvailable(context))
    }

    fun startListening(){
        var sp : SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        sp.setRecognitionListener(RecListener(this))

        val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")

        sp.startListening(i)
    }

    fun onTextResult(){
        if(audio_text.value.toLowerCase().contains("next")){
            goToNextStep()
        }

        if(!audio_text.value.toLowerCase().contains("stop")){
            recording()
        }
    }

    fun goToNextStep(){
        number ++
        printed_text.value = number.toString()
    }

}