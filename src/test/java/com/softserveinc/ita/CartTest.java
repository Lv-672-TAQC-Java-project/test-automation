package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyThatProductItemAddedToTheCart() {
        String searchTerm = "DeWALT";
        Header header = homePage.getHeader();
        SearchResultPage searchResultPage = header.search(searchTerm);

        assertThat(searchResultPage.getSearchTermLabel())
                .as("Search term label should be displayed")
                .contains(searchTerm);

        Product firstProductItem = searchResultPage.getProduct(1);
        String firstProductItemName = firstProductItem.getName();

        firstProductItem.addToCart();

        Cart cart = header.openCart();

        assertThat(cart.isOpened())
                .as("Cart modal should be displayed")
                .isTrue();

        String productItemNameInCart = cart
                .getProduct(1)
                .getName();

        assertThat(productItemNameInCart)
                .as("Added item name should be equal to item name from the cart")
                .isEqualTo(firstProductItemName);
    }
}