package com.softserveinc.ita;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class CartTest extends TestRunner {

    @Test
    public void verifyRemoveFunctionInCart() {

        Cart addedProductToCart = homePage.getHeader()
                .search("Фотоапарати")
                .getProduct(1)
                .addToCart()
                .getHeader()
                .openCart();

        assertThat(addedProductToCart.isCartContainsAddedCountOfProduct(1))
                .as("Cart should contain one product")
                .isTrue();

        Cart cartAfterRemoving = addedProductToCart
                .openCartProductActions()
                .removeOption();

        assertThat(cartAfterRemoving.isCartEmpty())
                .as("In the cart should be displayed the message 'Кошик порожній'")
                .isTrue();
    }
}
