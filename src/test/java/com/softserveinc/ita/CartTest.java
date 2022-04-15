package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartTest extends TestRunner {

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

        Product firstProductItem = searchResultPage.getProduct(1);
        firstProductItem.addToCart();

        Cart cart = header.openCart();
        InCartProduct firstCartProduct = cart.getProduct(1);

        assertThat(firstCartProduct.getName())
                .as("Product name in cart should be same as name of added product to it")
                .contains(firstProductItem.getName());

        int totalPrice = cart.getTotalPrice();
        int totalPriceUpdated = firstCartProduct
                .selectFirstAdditionalProductService()
                .getTotalPrice();

        assertThat(firstCartProduct.getPriceOfFirstAdditionalProductService())
                .as("Total price should be increased by the cost of the first selected additional service")
                .isEqualTo(totalPriceUpdated-totalPrice);
    }
}
