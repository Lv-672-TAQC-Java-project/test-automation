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

        var firstFlawedProduct = flawedProductsPage.getProduct(1);
//        var secondFlawedProduct = flawedProductsPage.getProduct(2);

        boolean firstProductDefect = firstFlawedProduct.isDefectDescriptionVisible();
//        boolean secondProductDefect = secondFlawedProduct.isDefectDescriptionVisible();

        assertThat(firstProductDefect)
                .as("Red description message should be visible after hovering mouse over flawed product")
                .isTrue();

//        assertThat(secondProductDefect)
//                .as("Red description message should be visible after hovering mouse over flawed product")
//                .isTrue();

        firstFlawedProduct.addToCart();
//        secondFlawedProduct.addToCart();

        var cart = header.openCart();

        var firstCartProduct = cart.getProduct(1);
//        var secondCartProduct = cart.getProduct(2);

        String firstCartProductName = firstCartProduct.getName();
//        String secondCartProductName = secondCartProduct.getName();

        String expectedWord = "Уцінка";

        assertThat(firstCartProductName)
                .as("Flawed product name should have word 'Уцінка' in its name")
                .endsWith(expectedWord);

//        assertThat(secondCartProductName)
//                .as("Flawed product name should have word 'Уцінка' in its name")
//                .endsWith(expectedWord);

        firstCartProduct.addOneMoreProduct();
        String firstAlertMessage = firstCartProduct.getAlertMessage();

        String expectedAlert = "Недостатньо товару для покупки";

        assertThat(firstAlertMessage)
                .as("It is not possible to buy more than one same flawed product")
                .isEqualTo(expectedAlert);

//        secondCartProduct.addOneMoreProduct();
//        String secondAlertMessage = secondCartProduct.getAlertMessage();
//
//        assertThat(secondAlertMessage)
//                .as("It is not possible to buy more than one same flawed product")
//                .isEqualTo(expectedAlert);
    }
}
