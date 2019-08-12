package io.github.johnnynanjiang.android.moneytreelight.data

import java.math.BigDecimal

data class Transaction(
    val id: String,
    val account_id: String,
    val amount: BigDecimal,
    val category_id: String,
    val date: String,
    val description: String
)