package org.toastedware.hackazon

import org.junit.Test
import kotlin.test.assertEquals

class TaxEngineTest {
    private val mixedNotImportedProducts = listOf(
            Product(
                    name = "book",
                    category = ProductCategory.BOOK,
                    price = 12.49
            ),
            Product(
                    name = "music CD",
                    category = ProductCategory.OTHER,
                    price = 14.99
            ),
            Product(
                    name = "chocolate bar",
                    category = ProductCategory.FOOD,
                    price = 0.85
            )
    )

    private val mixedImportedProducts = listOf(
            Product(
                    name = "box of chocolates",
                    category = ProductCategory.FOOD,
                    price = 10.00,
                    imported = true
            ),
            Product(
                    name = "bottle of perfume",
                    category = ProductCategory.OTHER,
                    price = 47.5,
                    imported = true
            )
    )

    private val mixedProducts = listOf(
            Product(
                    name = "bottle of perfume",
                    category = ProductCategory.OTHER,
                    price = 27.99,
                    imported = true
            ),
            Product(
                    name = "bottle of perfume",
                    category = ProductCategory.OTHER,
                    price = 18.99
            ),
            Product(
                    name = "packet of headache pills",
                    category = ProductCategory.MEDICAL,
                    price = 9.75
            ),
            Product(
                    name = "box of chocolates",
                    category = ProductCategory.FOOD,
                    price = 11.25,
                    imported = true
            )
    )

    @Test
    fun testMixedNotImportedProducts() {
        val processedCart = TaxEngine.process(mixedNotImportedProducts)
        val expectedCart = Cart(
                products = listOf(
                        Product(
                                name = "book",
                                category = ProductCategory.BOOK,
                                price = 12.49
                        ),
                        Product(
                                name = "music CD",
                                category = ProductCategory.OTHER,
                                price = 14.99
                        ),
                        Product(
                                name = "chocolate bar",
                                category = ProductCategory.FOOD,
                                price = 0.85
                        )
                ),
                salesTaxes = 1.50,
                total = 29.83
        )

        assertEquals(
                expectedCart,
                processedCart,
                "Tax Engine for mixed, not imported products conforms the specs! \uD83E\uDD18"
        )
    }

    @Test
    fun testMixedImportedProducts() {
        val processedCart = TaxEngine.process(mixedImportedProducts)
        val expectedCart = Cart(
                products = listOf(
                        Product(
                                name = "box of chocolates",
                                category = ProductCategory.FOOD,
                                price = 10.50,
                                imported = true
                        ),
                        Product(
                                name = "bottle of perfume",
                                category = ProductCategory.OTHER,
                                price = 54.65,
                                imported = true
                        )
                ),
                salesTaxes = 7.65,
                total = 65.15
        )

        assertEquals(
                expectedCart,
                processedCart,
                "Tax Engine for mixed imported products conforms the specs! \uD83E\uDD18"
        )
    }

    @Test
    fun testMixedProducts() {
        val processedCart = TaxEngine.process(mixedProducts)
        val expectedCart = Cart(
                products = listOf(
                        Product(
                                name = "bottle of perfume",
                                category = ProductCategory.OTHER,
                                price = 32.19,
                                imported = true
                        ),
                        Product(
                                name = "bottle of perfume",
                                category = ProductCategory.OTHER,
                                price = 20.89
                        ),
                        Product(
                                name = "packet of headache pills",
                                category = ProductCategory.MEDICAL,
                                price = 9.75
                        ),
                        Product(
                                name = "box of chocolates",
                                category = ProductCategory.FOOD,
                                price = 11.85,
                                imported = true
                        )
                ),
                salesTaxes = 6.70,
                total = 74.68
        )

        assertEquals(
                expectedCart,
                processedCart,
                "Tax Engine for mixed products conforms the specs! \uD83E\uDD18"
        )
    }
}
