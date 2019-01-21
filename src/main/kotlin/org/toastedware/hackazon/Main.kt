package org.toastedware.hackazon

import org.toastedware.hackazon.model.Product
import org.toastedware.hackazon.model.ProductCategory
import java.math.BigDecimal

fun main(args: Array<String>) {
    val products = listOf(
            Product(
                    name = "book",
                    category = ProductCategory.BOOK,
                    price = BigDecimal("12.49")
            ),
            Product(
                    name = "music CD",
                    category = ProductCategory.OTHER,
                    price = BigDecimal("14.99")
            ),
            Product(
                    name = "chocolate bar",
                    category = ProductCategory.FOOD,
                    price = BigDecimal("0.85")
            )
    )

    println(products)
}

