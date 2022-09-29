package com.koushikjoshi.glaukous_androidtask

data class Item(
    val boxBarcode: String,
    val id: Any,
    val itemBarcode: String,
    val itemCode: String,
    val itemDescription: String,
    val locationID: String,
    val quantityPicked: Int,
    val quantityToBePicked: Int,
    val sequenceID: Int,
    val shortageQuantity: Int,
    val status: Int
)