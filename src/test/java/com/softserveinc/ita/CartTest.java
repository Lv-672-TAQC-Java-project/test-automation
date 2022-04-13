package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyRemovalFunctionalityInTheCart() {
        var header = homePage.getHeader();

        Product firstProductFromSearch = header
                .search("Фотоапарати")
                .getProduct(1);

        String firstProductTitle = firstProductFromSearch.getName();

        firstProductFromSearch.addToCart();

        InCartProduct firstProductInTheCart = header
                .openCart()
                .getProduct(1);

        String firstProductTitleInTheCart = firstProductInTheCart.getName();

        assertThat(firstProductTitle)
                .as("The cart should contain added product")
                .isEqualTo(firstProductTitleInTheCart);

        Cart cart = firstProductInTheCart.remove();

        assertThat(cart.isCartEmpty())
                .as("Cart should be empty")
                .isTrue();
    }
}
