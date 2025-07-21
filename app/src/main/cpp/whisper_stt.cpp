#include <jni.h>
#include <string>
#include <android/log.h>

#define LOG_TAG "WhisperSTT"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL
Java_com_froginc_voiceassistant_stt_WhisperSTT_nativeRecognize(JNIEnv *env, jobject /* this */) {
    // TODO: Integrate actual Whisper STT code. For now, return a dummy string.
    std::string result = "Simulated STT result.";
    return env->NewStringUTF(result.c_str());
}