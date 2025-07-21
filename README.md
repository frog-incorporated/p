# Voice LLM Assistant Android

This is an Android app that serves as a voice-based assistant using an on-device LLM via a local Ollama server. It integrates Whisper for speech-to-text (STT) and Piper for text-to-speech (TTS) directly into the app using JNI, so no Termux setup is needed. The app also features wake word detection, a Jetpack Compose GUI, settings for configuration, and sample Retrofit API calls to your Pi Pico W.

## Features

- Uses Whisper for native STT
- Uses Piper TTS via JNI for natural speech output
- Wake word detection integration (configurable via settings)
- Streaming responses from a local Ollama LLM server
- Settings screen to configure: Ollama model name, system prompt, and wake word
- Tool call API integration for sending commands to a Pi Pico W

## Repository Structure

```
voice-llm-assistant-android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/froginc/voiceassistant/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── settings/SettingsActivity.kt
│   │   │   │   ├── stt/WhisperSTT.kt
│   │   │   │   ├── tts/PiperTTS.kt
│   │   │   │   └── network/
│   │   │   │       ├── OllamaService.kt
│   │   │   │       └── ToolCallService.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   ├── build.gradle
├── CMakeLists.txt
├── build.gradle
└── README.md
```

## How to Pull and Run in Android Studio

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/frog-incorporated/voice-llm-assistant-android.git
   cd voice-llm-assistant-android
   ```

2. **Open in Android Studio:**
   - Launch Android Studio.
   - Choose "Open an existing project" and select the cloned folder.
   - Wait for Gradle to sync and build the project.

3. **Build and Run:**
   - Connect your Android device or start an emulator.
   - Click the Run button to install and launch the app.

4. **Native Libraries:**
   - Ensure the native libraries (`libpiper_tts.so` and `libwhisper_stt.so`) are built for your target architecture.
   - Place them in `app/src/main/jniLibs/<abi>` as needed.
   - The provided CMakeLists.txt will help build these libraries.

5. **Configuration via Settings:**
   - Use the Settings screen in the app to set the Ollama model name, system prompt, and wake word.

6. **Extend Functionality:**
   - Replace the stub implementations in the JNI libraries with actual integration for Piper TTS and Whisper STT.
   - Update Retrofit base URLs in `OllamaService.kt` and `ToolCallService.kt` as per your server configuration.

Happy coding!