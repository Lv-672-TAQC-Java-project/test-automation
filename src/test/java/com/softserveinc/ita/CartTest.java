package com.softserveinc.ita;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyThatProductItemAddedToTheCart() {
        String searchTerm = "DeWALT";
        SearchResultPage searchResultPage = homePage
                .getHeader()
                .search(searchTerm);

        String searchedProductItemName = searchResultPage.getSearchedProductItemName();
        assertThat(searchedProductItemName)
                .as("Search term label should be displayed")
                .contains(searchTerm);

        String firstProductItemName = searchResultPage
                .addFirstProductItemToCart()
                .getFirstProductItemName();

        Cart cart = homePage
                .getHeader()
                .openCart();

        boolean isCartModalDisplayed = cart.isCartModalDisplayed();
        assertThat(isCartModalDisplayed)
                .as("Cart modal should be displayed")
                .isTrue();

        String productItemNameFromCart = cart.getProductItemNameFromCart();
        assertThat(firstProductItemName)
                .as("Added item name should be equal to item name from the cart")
                .isEqualTo(productItemNameFromCart);
    }
}