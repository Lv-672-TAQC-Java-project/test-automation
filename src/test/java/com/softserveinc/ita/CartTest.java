package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyRemovalFunctionalityInTheCart() {
        var header = homePage.getHeader();

        Product firstProduct = header
                .search("Фотоапарати")
                .getProduct(1);

        String firstProductName = firstProduct.getName();

        firstProduct.addToCart();

        InCartProduct productInCart = header
                .openCart()
                .getProduct(firstProductName);

        String productNameInCart = productInCart.getName();

        assertThat(firstProductName)
                .as("The product name in the cart should be equal to the name of added product")
                .isEqualTo(productNameInCart);

        Cart cart = productInCart.remove();

        assertThat(cart.isEmpty())
                .as("Cart should be empty")
                .isTrue();
    }
}
