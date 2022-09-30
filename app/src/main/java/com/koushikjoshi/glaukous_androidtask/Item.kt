package com.koushikjoshi.glaukous_androidtask

data class Item (
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
) : Comparable<Item> {
    override fun compareTo(other: Item): Int {
        return if (status !=0 && status < other.status) -1 else if (status!=0 && status == other.status) 0 else 1
    }
}