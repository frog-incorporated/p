package com.froginc.voiceassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.froginc.voiceassistant.network.OllamaService
import com.froginc.voiceassistant.stt.WhisperSTT
import com.froginc.voiceassistant.tts.PiperTTS
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    // States to hold conversation and listening state.
    private val conversation = mutableStateListOf<String>()
    private var isListening by mutableStateOf(false)
    private var currentText by mutableStateOf("")

    // Instances for STT and TTS (initially loaded as stubs)
    private lateinit var whisperSTT: WhisperSTT
    private lateinit var piperTTS: PiperTTS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize native TTS and STT components.
        whisperSTT = WhisperSTT()
        piperTTS = PiperTTS()

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(Modifier.fillMaxSize()) {
                        // Conversation display
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(conversation) { message ->
                                Text(text = message, style = MaterialTheme.typography.body1)
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }
                        // Input controls
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            Button(onClick = { startListening() }) {
                                Text("Start Listening")
                            }
                            Button(onClick = { endConversation() }) {
                                Text("End Conversation")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun startListening() {
        // Simulate wake-word detection here; in real use, integrate a wake word engine.
        isListening = true
        conversation.add("Wake word detected. Starting STT...")
        // Start recording and process with Whisper STT (asynchronously).
        WhisperSTT.recognize { transcribed ->
            // Append transcript then send to LLM with OllamaService.
            conversation.add("You: $transcribed")
            sendToLLM(transcribed)
            isListening = false
        }
    }

    private fun sendToLLM(prompt: String) {
        conversation.add("LLM processing…")
        // Use Retrofit-based OllamaService to send prompt to local server.
        val service = OllamaService.create()
        // Here we simulate a streaming response; in production, you’d implement streaming updates.
        val responseText = "This is a sample streamed response from the LLM."
        conversation.add("LLM: $responseText")
        // As response is received, read aloud with Piper TTS.
        piperTTS.speak(responseText)
    }

    private fun endConversation() {
        // Let the LLM decide when to end a conversation. For now, reset conversation.
        conversation.add("Conversation ended.")
    }
}