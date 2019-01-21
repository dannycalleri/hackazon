package org.toastedware.hackazon

import org.junit.Test
import org.toastedware.hackazon.model.Product
import org.toastedware.hackazon.model.ProductCategory
import org.toastedware.hackazon.tax.common
import org.toastedware.hackazon.tax.imported
import java.math.BigDecimal
import kotlin.test.assertEquals

class SalesTaxerTest {
    @Test
    fun testImportedTaxStrategy() {
        val product = Product(
                name = "dummy",
                category = ProductCategory.FOOD,
                price = BigDecimal("100"),
                imported = true
        )
        val importedStrategy = imported()
        val tax = importedStrategy(product)
        assertEquals(BigDecimal("5"), tax, "Testing with imported food product")
    }

    @Test
    fun testImportedTaxStrategyWithNotImportedProduct() {
        val product = Product(
                name = "dummy",
                category = ProductCategory.FOOD,
                price = BigDecimal("100")
        )
        val importedStrategy = imported()
        val tax = importedStrategy(product)
        assertEquals(BigDecimal("0.0"), tax, "Testing with imported food product")
    }

    @Test
    fun testCommonTaxStrategy() {
        val product = Product(
                name = "dummy",
                category = ProductCategory.OTHER,
                price = BigDecimal("100")
        )
        val importedStrategy = common()
        val tax = importedStrategy(product)
        assertEquals(BigDecimal("10"), tax, "Testing with an imported product")
    }

    @Test
    fun testCommonTaxStrategyWithFoodProduct() {
        val product = Product(
                name = "dummy",
                category = ProductCategory.FOOD,
                price = BigDecimal("100")
        )
        val importedStrategy = common()
        val tax = importedStrategy(product)
        assertEquals(BigDecimal("0.0"), tax, "Testing with an imported product")
    }
}