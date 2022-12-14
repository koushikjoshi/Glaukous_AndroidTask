package com.koushikjoshi.glaukous_androidtask

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TodoApi {
    var batchNum: Int

//    Making a get request to API containing data
    @GET("api/Picking/GetPickingItems/{id}")
    suspend fun getTodos(@Path("id") batch: String): Response<TodoFirstX>

//    Making a get request to API containing batch number
    @GET("api/Picking/StartBatch")
    suspend fun getBatchNum(): Response<BatchNumData>
}