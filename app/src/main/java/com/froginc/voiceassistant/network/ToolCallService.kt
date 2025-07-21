package com.froginc.voiceassistant.network

import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Body

// Define API structure for tool calls to Pi Pico W.
data class ToolCallRequest(val command: String)
data class ToolCallResponse(val status: String)

interface ToolCallService {

    @POST("api/tool")
    suspend fun callTool(@Body request: ToolCallRequest): ToolCallResponse

    companion object {
        fun create(): ToolCallService {
            return Retrofit.Builder()
                .baseUrl("http://192.168.0.x/") // Set to your Pi Pico W endpoint
                .build()
                .create(ToolCallService::class.java)
        }
    }
}