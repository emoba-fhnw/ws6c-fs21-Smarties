package fhnw.ws6c

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import fhnw.ws6c.theapp.TheApp
import fhnw.ws6c.theapp.model.TheModel
import java.util.*


class MainActivity : ComponentActivity() {
    private lateinit var app: EmobaApp  //alle Beispiele implementieren das Interface EmobaApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = TheApp(this)

        app.initialize(activity = this)

        setContent {
            app.CreateUI()
        }
    }

    /**
     * Eine der Activity-LiveCycle-Methoden. Im Laufe des Semesters werden weitere benötigt
     * werden. Auch die leiten den Aufruf lediglich an die EmobaApp weiter.
     */
    override fun onStop() {
        super.onStop()
        app.onStop(activity = this)
    }




//    private val RQ_SPEECH_REC = 102
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
//            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//            model.text = result?.get(0).toString()
//        }
//    }
//
//    fun askSpeechInput(){
//        if(!SpeechRecognizer.isRecognitionAvailable(this)){
//            Toast.makeText(this,"Speech regognition is not available", Toast.LENGTH_SHORT).show()
//        }else{
//            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")
//            startActivityForResult(i, RQ_SPEECH_REC)
//        }
//    }
}

