package com.example.and_p_test.network

import com.example.and_p_test.model.PushDTO
import com.example.and_p_test.util.Val
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PushApi {

    @Headers("Authorization: key=${Val.PUSH_SERVER_KEY}", "Content-Type: ${Val.PUSH_CONTENT_TYPE}")
    @POST("/fcm/send")
    suspend fun sendMessage(@Body push: PushDTO): Response<ResponseBody>

}