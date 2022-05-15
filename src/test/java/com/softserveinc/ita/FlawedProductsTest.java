package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlawedProductsTest extends TestRunner {

    @Description("Add test script to verify that flawed products have additional field with defect description.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-38")
    @Test(description = "LVTAQC672-38")
    public void verifyThatWrittenOffProductsHaveAdditionalDescription() {
        var header = homePage.getHeader();
        var flawedProductsPage =
                homePage
                        .getCategorySideBar()
                        .openFlawedProductsPage();

        String flawedProductsCategory = "телевізори та монітори";
        flawedProductsPage.openFlawedProductsCategoryPage(flawedProductsCategory);

        var flawedProduct = flawedProductsPage.getProduct(1);

        boolean isProductDefectVisible = flawedProduct.isDefectDescriptionVisible();

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
        String firstAlertMessage = cartProduct.getAlertMessage();

        String expectedAlert = "Недостатньо товару для покупки";

        assertThat(firstAlertMessage)
                .as("It is not possible to buy more than one of the same flawed product.")
                .isEqualTo(expectedAlert);

    }
}
