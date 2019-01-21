package org.toastedware.hackazon.model

import java.math.BigDecimal

enum class ProductCategory {
    FOOD,
    BOOK,
    MEDICAL,
    OTHER
}

data class Product(
        val name: String,
        val category: ProductCategory,
        val price: BigDecimal,
        val imported: Boolean = false
)