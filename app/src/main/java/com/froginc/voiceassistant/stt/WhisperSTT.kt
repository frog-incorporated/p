package com.froginc.voiceassistant.stt

object WhisperSTT {

    // Load the native library
    init {
        System.loadLibrary("whisper_stt")
    }

    // Native call to process audio and return text transcript.
    external fun nativeRecognize(): String

    // For demonstration, we simulate asynchronous recognition.
    fun recognize(callback: (String) -> Unit) {
        // In a real app, you’d capture audio and perform recognition.
        // Here, we simulate by calling the native function in a background thread.
        Thread {
            val result = nativeRecognize()
            callback(result)
        }.start()
    }
}