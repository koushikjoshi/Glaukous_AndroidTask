package com.koushikjoshi.glaukous_androidtask

data class Data(
    val batchNumber: Int,
    val date: String,
    val id: Int,
    val items: List<Item>,
    val numberOfSKUs: Int,
    val numberOfTotes: Int,
    val pickingType: String,
    val totalQuantityPicked: Int,
    val totalQuantityToBePicked: Int,
    val zone: String
)