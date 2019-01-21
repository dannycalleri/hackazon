package org.toastedware.hackazon.tax

import org.toastedware.hackazon.model.Product
import org.toastedware.hackazon.model.ProductCategory
import java.math.BigDecimal

typealias SalesTaxer = (Product) -> BigDecimal

fun common(): SalesTaxer {
    return { product ->
        var tax = BigDecimal("0.0")
        if(product.category == ProductCategory.OTHER) {
            tax = product.price.multiply(BigDecimal("10")).divide(BigDecimal("100"))
        }
        tax
    }
}

fun imported(): SalesTaxer {
    return { product ->
        var tax = BigDecimal("0.0")
        if(product.imported) {
            tax = product.price.multiply(BigDecimal("5")).divide(BigDecimal(100))
        }
        tax
    }
}
