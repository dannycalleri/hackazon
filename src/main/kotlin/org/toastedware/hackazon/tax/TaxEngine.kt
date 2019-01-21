package org.toastedware.hackazon.tax

import org.toastedware.hackazon.model.Cart
import org.toastedware.hackazon.model.Product
import java.math.RoundingMode
import java.math.BigDecimal

object TaxEngine {
    private val taxingStrategies = listOf(
            imported(),
            common()
    )

    fun process(products: List<Product>): Cart {
        if(products.isEmpty()) {
            throw UnsupportedOperationException("Can't process an empty list of products")
        }

        val processedProducts = products.map {
            Product(
                    name = it.name,
                    price = it.price + taxingStrategies
                            .map { strategy -> round(strategy(it)) }
                            .reduce { acc, bigDecimal -> acc + bigDecimal },
                    category = it.category,
                    imported = it.imported
            )
        }

        val salesTaxes = products
                .zip(processedProducts)
                .map { it.second.price - it.first.price }
                .reduce { acc, bigDecimal ->  acc + bigDecimal }
        val total = processedProducts.map { it.price }.reduce { acc, bigDecimal -> acc + bigDecimal }

        return Cart(
                products = processedProducts,
                salesTaxes = salesTaxes,
                total = total
        )
    }

    private fun round(value: BigDecimal): BigDecimal {
        val rounding = BigDecimal("0.05")
        return (value.divide(rounding, 0, RoundingMode.CEILING)).multiply(rounding)
    }
}