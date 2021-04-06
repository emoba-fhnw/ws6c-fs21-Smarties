package fhnw.ws6c.theapp

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import fhnw.ws6c.theapp.model.TheModel

class RecListener(var model : TheModel) : RecognitionListener{
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
        println("onError: " + error)
    }

    override fun onResults(results: Bundle?) {
        println("onResults")
        model.text.value = results!!.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)!!.get(0)
    }

    override fun onPartialResults(partialResults: Bundle?) {
        println("onPartialResults")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        println("onEvent")
    }
}