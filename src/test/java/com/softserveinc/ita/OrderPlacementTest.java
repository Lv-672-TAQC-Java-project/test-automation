package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderPlacementTest extends TestRunner {

    @Test
    public void verifyThatImpossibleToPlaceAnOrderWithoutRequiredData() {
        String searchTerm = "DeWALT";
        Header header = homePage.getHeader();
        SearchResultPage searchResultPage = header.search(searchTerm);

        assertThat(searchResultPage.getSearchTermLabel())
                .as("Search term label should be displayed")
                .contains(searchTerm);

        Product firstProduct = searchResultPage.getProduct(1);
        String firstProductName = firstProduct.getName();

        ProductDetailsPage productDetailsPage = firstProduct.openProductDetailsPage();
        String productName = productDetailsPage.getProductName();

        assertThat(productName)
                .as("Product name should be the same for search result page and product details page")
                .isEqualTo(firstProductName);

        productDetailsPage.scrollToProductName();
        Cart cart = productDetailsPage.addToCart();

        assertThat(cart.isOpened())
                .as("Cart modal should be displayed")
                .isTrue();

        OrderPlacementPage orderPlacementPage = cart.submitOrder();

        assertThat(orderPlacementPage.isOpened())
                .as("Order placement page should be displayed")
                .isTrue();

        orderPlacementPage.submitOrder();

        assertThat(orderPlacementPage.isOpened())
                .as("Order placement page should still be displayed")
                .isTrue();

        String requiredFieldErrorMessage = "%s field should be required";
        String surnameFieldTittle = "surname";
        assertThat(orderPlacementPage.isFieldRequired(surnameFieldTittle))
                .as(format(requiredFieldErrorMessage, surnameFieldTittle))
                .isTrue();

        String nameFieldTittle = "name";
        assertThat(orderPlacementPage.isFieldRequired(nameFieldTittle))
                .as(format(requiredFieldErrorMessage, nameFieldTittle))
                .isTrue();

        String phoneFieldTittle = "phone";
        assertThat(orderPlacementPage.isFieldRequired(phoneFieldTittle))
                .as(format(requiredFieldErrorMessage, phoneFieldTittle))
                .isTrue();
    }
}