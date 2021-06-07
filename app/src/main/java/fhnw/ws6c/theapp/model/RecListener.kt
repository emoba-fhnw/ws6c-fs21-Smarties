package fhnw.ws6c.theapp

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.widget.Toast
import fhnw.ws6c.theapp.model.CocktailModel

class RecListener(var model : CocktailModel) : RecognitionListener{
    override fun onReadyForSpeech(params: Bundle?) {
        println("onReadyForSpeech")
    }

    override fun onBeginningOfSpeech() {
        println("onBeginningOfSpeech")
    }

    override fun onRmsChanged(rmsdB: Float) {
        println("onRmsChanged")
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        println("onBufferReceived")
    }

    override fun onEndOfSpeech() {
        println("onEndOfSpeech")
    }

    override fun onError(error: Int) {
        model.isRecording.value = false
        if(error==7){ //ERROR_NO_MATCH
            if(model.enableSpeechRec){
                Toast.makeText(model.context,"Can you repeat that please?", Toast.LENGTH_SHORT).show()
            }
            model.recording()
        }
        if(error==8){ //ERROR_RECOGNIZER_BUSY
            model.recording()
        }
    }

    override fun onResults(results: Bundle?) {
        model.isRecording.value = false
        model.audio_text.value = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)!!.get(0)
        model.onTextResult()
    }

    override fun onPartialResults(partialResults: Bundle?) {
        println("onPartialResults")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        println("onEvent")
    }
}