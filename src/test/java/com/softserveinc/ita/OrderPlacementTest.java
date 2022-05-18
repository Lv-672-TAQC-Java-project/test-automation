package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPlacementTest extends TestRunner {

    @Description("Verify that it's impossible to place an order without populating required data")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-2")
    @Test(description = "LVTAQC672-2")
    public void verifyThatImpossibleToPlaceAnOrderWithoutRequiredData() {
        homePage.emptyCart();
        var searchTerm = "DeWALT";
        var header = homePage.getHeader();
        var searchResultPage = header.search(searchTerm);

        assertThat(searchResultPage.getSearchTermLabel())
                .as("Search term label should be displayed")
                .contains(searchTerm);

        var firstProduct = searchResultPage.getProduct(1);
        var firstProductName = firstProduct.getName();

        var productDetailsPage = firstProduct.openDetailsPage();
        var productName = productDetailsPage.getProductName();

        assertThat(productName)
                .as("Product name should be the same for search result page and product details page")
                .isEqualTo(firstProductName);

        var cart = productDetailsPage.addToCart();

        //Sometimes cart is not opened automatically after adding product to cart from details page
        if (!cart.isOpened()) {
            header.openCart();
        }

        assertThat(cart.isOpened())
                .as("Cart modal should be displayed")
                .isTrue();

        var orderPlacementPage = cart.submitOrder();

        assertThat(orderPlacementPage.isOpened())
                .as("Order placement page should be displayed")
                .isTrue();

        orderPlacementPage.submitOrder();

        assertThat(orderPlacementPage.isOpened())
                .as("Order placement page should still be displayed")
                .isTrue();

        var requiredFieldErrorMessage = "%s field should be required";
        var surnameFieldTittle = "surname";
        assertThat(orderPlacementPage.isFieldRequired(surnameFieldTittle))
                .as(format(requiredFieldErrorMessage, surnameFieldTittle))
                .isTrue();

        var nameFieldTittle = "name";
        assertThat(orderPlacementPage.isFieldRequired(nameFieldTittle))
                .as(format(requiredFieldErrorMessage, nameFieldTittle))
                .isTrue();

        var phoneFieldTittle = "phone";
        assertThat(orderPlacementPage.isFieldRequired(phoneFieldTittle))
                .as(format(requiredFieldErrorMessage, phoneFieldTittle))
                .isTrue();
    }
}