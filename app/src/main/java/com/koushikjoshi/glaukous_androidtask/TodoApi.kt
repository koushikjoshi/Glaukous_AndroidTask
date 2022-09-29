package com.koushikjoshi.glaukous_androidtask

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {
    var batchNum: Int

    @GET("api/Picking/GetPickingItems")
    suspend fun getTodos(@Query("batchNum") batch: String): Response<List<TodoFirst>>
}