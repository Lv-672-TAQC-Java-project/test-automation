package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.product.InCartProduct;
import com.softserveinc.ita.pageobjects.product.InOrderProduct;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static com.softserveinc.ita.pageobjects.models.CategoryName.BEAUTY_AND_HEALTH;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.PRODUCT_AVAILABILITY;
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

        var softAssert = new SoftAssertions();
        softAssert.assertThat(orderPlacementPage.isFieldRequired(surnameFieldTittle))
                .as(format(requiredFieldErrorMessage, surnameFieldTittle))
                .isTrue();

        var nameFieldTittle = "name";
        softAssert.assertThat(orderPlacementPage.isFieldRequired(nameFieldTittle))
                .as(format(requiredFieldErrorMessage, nameFieldTittle))
                .isTrue();

        var phoneFieldTittle = "phone";
        softAssert.assertThat(orderPlacementPage.isFieldRequired(phoneFieldTittle))
                .as(format(requiredFieldErrorMessage, phoneFieldTittle))
                .isTrue();

        softAssert.assertAll();
    }

    //may fall due to a bug
    @Description("Verify that in the order all product names and prices match with the names and prices from the cart")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-44")
    @Test(description = "LVTAQC672-44")
    public void verifyThatInTheOrderAllProductNamesAndPricesMatchWithTheNamesAndPricesFromTheCart() {
        homePage.emptyCart();
        var header = homePage.getHeader();
        header
                .openCatalog()
                .openSubCategoryPage(BEAUTY_AND_HEALTH, "Триммери")
                .getFilter()
                .filterBySection(PRODUCT_AVAILABILITY, "Є в наявності")
                .getProduct(1)
                .addToCart()
                .getProduct(2)
                .addToCart()
                .getProduct(3)
                .addToCart();

        var cart = header.openCart();
        var productsInCart = cart.getInCartProducts();

        var productsInCartNames = productsInCart
                .stream()
                .map(InCartProduct::getName)
                .collect(Collectors.toList());

        var productsInCartPrices = productsInCart
                .stream()
                .map(InCartProduct::getPrice)
                .collect(Collectors.toList());

        var productsInOrder = cart
                .submitOrder()
                .getProducts();

        var productsInOrderNames = productsInOrder
                .stream()
                .map(InOrderProduct::getName)
                .collect(Collectors.toList());

        var productsInOrderPrices =  productsInOrder
                .stream()
                .map(InOrderProduct::getPrice)
                .collect(Collectors.toList());

        assertThat(productsInOrderNames)
                .as("In the order all product names should match with the names from the cart")
                .containsAll(productsInCartNames);
        assertThat(productsInOrderPrices)
                .as("In the order all product prices should match with the prices from the cart")
                .containsAll(productsInCartPrices);
    }
}