package com.example.and_p_test.util

import android.util.Log
import com.example.and_p_test.MyApplication.Companion.firestore
import com.example.and_p_test.model.PushDTO
import com.example.and_p_test.network.NetworkModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import com.example.and_p_test.util.Val
import java.lang.Exception

object Push {

    const val TAG = "Push"

    fun sendPush(destinationUid: String, title: String, message: String) {
        firestore?.collection("pushtokens")
            ?.document(destinationUid)
            ?.get()
            ?.addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    val token = task.result.get("pushToken").toString()
                    val pushDTO = PushDTO(
                        token,
                        PushDTO.Notification(
                            title,
                            message
                        )
                    )

                    CoroutineScope(IO).launch {
                        try {
                            val retrofit = NetworkModule.getRetrofitClient(Val.PUSH_URL)
                            val response = NetworkModule.getPushApi(retrofit).sendMessage(pushDTO)

                            if(response.isSuccessful) {
                                Log.e(TAG, "Response success")
                            } else {
                                Log.e(TAG, "Response error")
                            }
                        } catch(e: Exception) {
                            e.printStackTrace()
                            Log.e(TAG, e.toString())
                        }
                    }
                }
            }
    }

}