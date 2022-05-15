package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlawedProductsTest extends TestRunner {

    @Description("Add test script to verify that user can't add more than one same flawed product to the cart.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-38")
    @Test(description = "LVTAQC672-38")
    public void verifyThatUserCanAddOnlyOneSameFlawedProductToTheCart() {
        var header = homePage.getHeader();
        String flawedProductsCategory = "телевізори та монітори";

        var subCategoryPage =
                homePage
                        .getCategorySideBar()
                        .openFlawedProductsPage()
                        .openFlawedProductsCategoryPage(flawedProductsCategory);

        assertThat(subCategoryPage.getCategoryLabel())
                .as("Search result page category label should contain " + flawedProductsCategory)
                .contains(flawedProductsCategory);

        var flawedProduct = subCategoryPage.getProduct(1);
        var isProductDefectVisible = flawedProduct.isDefectDescriptionVisible();

        assertThat(isProductDefectVisible)
                .as("Red description message should be visible after hovering mouse over flawed product")
                .isTrue();

        flawedProduct.addToCart();
        var cart = header.openCart();
        var cartProduct = cart.getProduct(1);

        String cartProductName = cartProduct.getName();
        String expectedWord = "Уцінка";

        assertThat(cartProductName)
                .as("Flawed product name should have word 'Уцінка' in its name")
                .endsWith(expectedWord);

        cartProduct.addOneMoreProduct();
        String alertMessage = cartProduct.getAlertMessage();
        String expectedAlert = "Недостатньо товару для покупки";

        assertThat(alertMessage)
                .as("It is not possible to buy more than one of the same flawed product.")
                .isEqualTo(expectedAlert);

        int productQuantity = cartProduct.getProductsQuantity();

        assertThat(productQuantity)
                .as("Quantity of same flawed product added to the cart can't exceed 1")
                .isEqualTo(1);


    }
}
