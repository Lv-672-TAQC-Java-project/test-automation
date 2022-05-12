package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.models.CategoryName;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends TestRunner {

    @Description("Verify that products include the search term in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-3")
    @Test(description = "LVTAQC672-3")
    public void verifyThatProductsIncludeTheSearchTermInTheirNames() {
        String searchTerm = "Asus";
        var products = homePage
                .getHeader()
                .search(searchTerm)
                .getProducts();

        assertThat(products)
                .allSatisfy(product -> assertThat(product.getName())
                        .as(product.getName() + " should contain " + searchTerm)
                        .containsIgnoringCase(searchTerm));
    }

    @Test
    public void verifyThatWrittenOffProductsHaveAdditionalDescription() {
        var header = homePage.getHeader();
        var categoryPage =
                homePage
                        .getCategorySideBar()
                        .openCategoryPage(CategoryName.WRITTEN_OFF_PRODUCTS);

        var firstFlawedProduct = categoryPage.getProduct(1);
        var secondFlawedProduct = categoryPage.getProduct(2);

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
