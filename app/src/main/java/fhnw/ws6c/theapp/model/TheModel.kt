package fhnw.ws6c.theapp.model

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import java.lang.Exception
import java.security.AccessController.checkPermission
import java.util.*

class TheModel {


//    private fun checkAudioPermission() {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  // M = 23
//            if(ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != PackageManager.PERMISSION_GRANTED) {
//                // this will open settings which asks for permission
//                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.programmingtech.offlinespeechtotext"))
//                startActivity(intent)
//                Toast.makeText(this, "Allow Microphone Permission", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    //---------------------------



//    private val REQUEST_CODE_SPEECH_INPUT = 100
//
//    fun speak(){
//        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        mIntent.putExtra(
//            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak something")
//
//        try{
//            startActivityForResult(Activity(), mIntent, REQUEST_CODE_SPEECH_INPUT,null)
//        }catch(e: Exception){
//            Toast.makeText(Activity(), e.message, Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when(requestCode){
//            REQUEST_CODE_SPEECH_INPUT ->{
//                if(requestCode == AppCompatActivity.RESULT_OK && null!= data){
//                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
////                    textTv.text = result[0]
//                }
//            }
//        }
//    }
//
//    fun write(requestCode: Int, resultCode: Int, data: Intent?){
//        when(requestCode){
//            REQUEST_CODE_SPEECH_INPUT ->{
//                if(requestCode == AppCompatActivity.RESULT_OK && null!= data){
//                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
////                    textTv.text = result[0]
//                }
//            }
//        }
//    }

}