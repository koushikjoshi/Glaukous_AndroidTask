package com.koushikjoshi.glaukous_androidtask

data class BatchNumData(
    val batchNumber: Int,
    val id: Int,
    val numberOfSKUs: Int,
    val numberOfTotes: Int,
    val pickingType: String,
    val waveNumber: Int,
    val zone: String
)