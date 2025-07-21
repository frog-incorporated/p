package com.froginc.voiceassistant.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Define the API request and response.
data class LLMRequest(val prompt: String)
data class LLMResponse(val text: String)

interface OllamaService {

    @POST("api/v1/stream")
    suspend fun requestLLM(@Body request: LLMRequest): LLMResponse

    companion object {
        fun create(): OllamaService {
            return Retrofit.Builder()
                .baseUrl("http://127.0.0.1:11434/") // Adjust to your local Ollama server URL
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OllamaService::class.java)
        }
    }
}