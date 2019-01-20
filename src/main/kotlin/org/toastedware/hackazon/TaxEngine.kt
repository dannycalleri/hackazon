package org.toastedware.hackazon

import java.math.RoundingMode
import java.math.BigDecimal

object TaxEngine {
    private val TAX_RATE = 10
    private val IMPORT_TAX_RATE = 5

    fun process(products: List<Product>): Cart {
        val processedProducts = products.map {
            Product(
                    name = it.name,
                    price = it.price + calculateSaleTax(it) + calculateImportTax(it),
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

    private fun calculateSaleTax(product: Product): BigDecimal {
        var tax = BigDecimal("0.0")
        if(product.category === ProductCategory.OTHER) {
            tax = round(product.price.multiply(BigDecimal(TAX_RATE.toString())).divide(BigDecimal(100)))
        }

        return tax
    }

    private fun calculateImportTax(product: Product): BigDecimal {
        var tax = BigDecimal("0.0")
        if(product.imported) {
            tax = round(product.price.multiply(BigDecimal(IMPORT_TAX_RATE.toString())).divide(BigDecimal(100)))
        }

        return tax
    }

    private fun round(value: BigDecimal): BigDecimal {
        val rounding = BigDecimal("0.05")
        return (value.divide(rounding, 0, RoundingMode.CEILING)).multiply(rounding)
    }
}