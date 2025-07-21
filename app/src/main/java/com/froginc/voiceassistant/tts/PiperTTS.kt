package com.froginc.voiceassistant.tts

object PiperTTS {

    // Load the native library
    init {
        System.loadLibrary("piper_tts")
    }

    // Native call to start speaking
    external fun nativeSpeak(text: String)

    fun speak(text: String) {
        // Call the native function to play speech using Piper
        nativeSpeak(text)
    }
}