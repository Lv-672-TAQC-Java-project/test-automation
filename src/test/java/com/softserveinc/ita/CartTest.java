package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
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

    @Description("Add test script to cover 'Additional services' functionality in Rozetka’s cart " +
            "and that the total products price works correctly")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-14")
    @Test(description = "LVTAQC672-14")
    public void verifyThatTotalPriceChangedAfterAddingAdditionalService() {
        Header header = homePage.getHeader();
        String searchTerm = "Asus";
        SearchResultPage searchResultPage = header.search(searchTerm);

        assertThat(searchResultPage.getSearchTermLabel())
                .as("Search result page should contain label with" + searchTerm)
                .contains(searchTerm);

        Product firstProduct = searchResultPage.getProduct(1);
        firstProduct.addToCart();

        Cart cart = header.openCart();
        InCartProduct firstCartProduct = cart.getProduct(1);

        assertThat(firstCartProduct.getName())
                .as("Product name in cart should be same as name of added product to it")
                .contains(firstProduct.getName());

        int totalPrice = cart.getTotalPrice();
        InCartProductAdditionalService firstAdditionalProductService = firstCartProduct.getAdditionalProductService(1);
        firstAdditionalProductService.select();
        int totalPriceUpdated = cart.getTotalPrice();
        int priceOfFirstAdditionalProductService = firstAdditionalProductService.getPrice();

        assertThat(priceOfFirstAdditionalProductService)
                .as("Total price should be increased by the cost of the first selected additional service")
                .isEqualTo(totalPriceUpdated-totalPrice);
    }
}