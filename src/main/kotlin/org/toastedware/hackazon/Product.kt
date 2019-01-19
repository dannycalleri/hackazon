package org.toastedware.hackazon

enum class ProductCategory {
    FOOD,
    BOOK,
    MEDICAL,
    OTHER
}

data class Product(
        val name: String,
        val category: ProductCategory,
        val price: Double,
        val imported: Boolean = false
)