package org.toastedware.hackazon

import java.math.BigDecimal

data class Cart(
        val products: List<Product>,
        val salesTaxes: BigDecimal,
        val total: BigDecimal
)