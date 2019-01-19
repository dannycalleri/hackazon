package org.toastedware.hackazon

data class Cart(
        val products: List<Product>,
        val salesTaxes: Double,
        val total: Double
)