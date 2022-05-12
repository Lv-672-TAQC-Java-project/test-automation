package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FlawedProductsTest extends TestRunner {

    @Test
    public void verifyThatWrittenOffProductsHaveAdditionalDescription() {
        var header = homePage.getHeader();
        var flawedProductsPage =
                homePage
                        .getCategorySideBar()
                        .openFlawedProductsPage();

        var firstFlawedProduct = flawedProductsPage.getProduct(1);
        var secondFlawedProduct = flawedProductsPage.getProduct(2);

        boolean firstProductDefect = firstFlawedProduct.isDefectDescriptionVisible();
        boolean secondProductDefect = secondFlawedProduct.isDefectDescriptionVisible();

        assertThat(firstProductDefect)
                .as("Red description message should be visible after hovering mouse over product")
                .isTrue();

        assertThat(secondProductDefect)
                .as("Red description message should be visible after hovering mouse over product")
                .isTrue();

        firstFlawedProduct.addToCart();
        secondFlawedProduct.addToCart();

        var cart = header.openCart();

        String firstProductName = cart
                .getProduct(1)
                .getName();
        String secondProductName = cart
                .getProduct(2)
                .getName();

        String expectedWord = "Уцінка";

        assertThat(firstProductName)
                .as("Flawed product name should have word 'Уцінка' in its name")
                .endsWith(expectedWord);

        assertThat(secondProductName)
                .as("Flawed product name should have word 'Уцінка' in its name")
                .endsWith(expectedWord);
    }
}
