package com.example.hw2sub

import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class ClaimService(val ctx : MainActivity) {
    /*abstract class GetAllServiceRespHandler : AsyncHttpResponseHandler() {
    }*/

    lateinit var mainAct: MainActivity

    inner class addServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            mainAct = MainActivity()
            if (responseBody != null) {

                val respStr = String(responseBody)
                (ctx).addSuccess()
                Log.d("Claim Service", "The add Service response : ${respStr}")
            }

        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {

            (ctx).addFail()
            TODO("Not yet implemented, FAIL")
        }
    }

               
    fun addClaim(readyClaim: Claim){
        val client = AsyncHttpClient()
        val requestUrl = "http://192.168.1.20:8080/ClaimService/add"

        // Convert Claim to JSON
        val cJsonString = Gson().toJson(readyClaim)

        println("JSON SENDING ${cJsonString}")
        // Send the post request
        val entity = StringEntity(cJsonString)

        // Send POST Request
        client.post(ctx, requestUrl, entity,"application/json", addServiceRespHandler())

    }
}