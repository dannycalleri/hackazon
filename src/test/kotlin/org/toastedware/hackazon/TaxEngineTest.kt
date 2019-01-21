package org.toastedware.hackazon

import org.junit.Test
import org.toastedware.hackazon.model.Cart
import org.toastedware.hackazon.model.Product
import org.toastedware.hackazon.model.ProductCategory
import org.toastedware.hackazon.tax.TaxEngine
import java.math.BigDecimal
import kotlin.test.assertEquals

class TaxEngineTest {
    @Test
    fun testMixedNotImportedProducts() {
        val mixedNotImportedProducts = listOf(
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
        val processedCart = TaxEngine.process(mixedNotImportedProducts)
        val expectedCart = Cart(
                products = listOf(
                        Product(
                                name = "book",
                                category = ProductCategory.BOOK,
                                price = BigDecimal("12.49")
                        ),
                        Product(
                                name = "music CD",
                                category = ProductCategory.OTHER,
                                price = BigDecimal("16.49")
                        ),
                        Product(
                                name = "chocolate bar",
                                category = ProductCategory.FOOD,
                                price = BigDecimal("0.85")
                        )
                ),
                salesTaxes = BigDecimal("1.50"),
                total = BigDecimal("29.83")
        )

        assertEquals(
                expectedCart,
                processedCart,
                "Testing tax engine for mixed not imported products"
        )
    }

    @Test
    fun testMixedImportedProducts() {
        val mixedImportedProducts = listOf(
                Product(
                        name = "box of chocolates",
                        category = ProductCategory.FOOD,
                        price = BigDecimal("10.00"),
                        imported = true
                ),
                Product(
                        name = "bottle of perfume",
                        category = ProductCategory.OTHER,
                        price = BigDecimal("47.5"),
                        imported = true
                )
        )
        val processedCart = TaxEngine.process(mixedImportedProducts)
        val expectedCart = Cart(
                products = listOf(
                        Product(
                                name = "box of chocolates",
                                category = ProductCategory.FOOD,
                                price = BigDecimal("10.50"),
                                imported = true
                        ),
                        Product(
                                name = "bottle of perfume",
                                category = ProductCategory.OTHER,
                                price = BigDecimal("54.65"),
                                imported = true
                        )
                ),
                salesTaxes = BigDecimal("7.65"),
                total = BigDecimal("65.15")
        )

        assertEquals(
                expectedCart,
                processedCart,
                "Testing tax engine for mixed imported products"
        )
    }

    @Test
    fun testMixedProducts() {
        val mixedProducts = listOf(
                Product(
                        name = "bottle of perfume",
                        category = ProductCategory.OTHER,
                        price = BigDecimal("27.99"),
                        imported = true
                ),
                Product(
                        name = "bottle of perfume",
                        category = ProductCategory.OTHER,
                        price = BigDecimal("18.99")
                ),
                Product(
                        name = "packet of headache pills",
                        category = ProductCategory.MEDICAL,
                        price = BigDecimal("9.75")
                ),
                Product(
                        name = "box of chocolates",
                        category = ProductCategory.FOOD,
                        price = BigDecimal("11.25"),
                        imported = true
                )
        )
        val processedCart = TaxEngine.process(mixedProducts)
        val expectedCart = Cart(
                products = listOf(
                        Product(
                                name = "bottle of perfume",
                                category = ProductCategory.OTHER,
                                price = BigDecimal("32.19"),
                                imported = true
                        ),
                        Product(
                                name = "bottle of perfume",
                                category = ProductCategory.OTHER,
                                price = BigDecimal("20.89")
                        ),
                        Product(
                                name = "packet of headache pills",
                                category = ProductCategory.MEDICAL,
                                price = BigDecimal("9.75")
                        ),
                        Product(
                                name = "box of chocolates",
                                category = ProductCategory.FOOD,
                                price = BigDecimal("11.85"),
                                imported = true
                        )
                ),
                salesTaxes = BigDecimal("6.70"),
                total = BigDecimal("74.68")
        )

        assertEquals(
                expectedCart,
                processedCart,
                "Testing tax engine for mixed products"
        )
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testTaxEngineWithEmptyList() {
        TaxEngine.process(listOf())
    }
}
