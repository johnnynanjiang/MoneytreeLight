package io.github.johnnynanjiang.android.moneytreelight.domain

import java.math.BigDecimal

data class Account(
    val id: String,
    val name: String,
    val institution: String,
    val currency: String,
    val current_balance: BigDecimal,
    val current_balance_in_base: BigDecimal
)