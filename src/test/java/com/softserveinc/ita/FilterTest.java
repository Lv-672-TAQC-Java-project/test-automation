package com.softserveinc.ita;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilterTest extends TestRunner {
    @Description("Verify that filtered products contains searching term 'iPhone' in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-11")
    @Test(description = "LVTAQC672-11")
    public void verifyThatFilteredProductsContainsExpectedTerm() {
        String filterSubCategoryName = "Apple";

        SearchResultPage searchResultPage = homePage
                .getHeader()
                .search("мобільний телефон")
                .getFilter()
                .filterByCategory("Виробник", filterSubCategoryName);

        SelenideElement filteredProductsSubCategoryTag = $x(String.format("//a[@class='catalog-selection__link' " +
                "and contains(text(), '%s')]", filterSubCategoryName));
        List<Product> filteredProductsList = new ArrayList<>();

        if (isDisplayed(filteredProductsSubCategoryTag, Duration.ofSeconds(5))) {
            filteredProductsList = searchResultPage.getProducts();
        }

        String expectedTerm = "iPhone";
        filteredProductsList.forEach(product -> assertThat(product.getName())
                .as("Product name should contains " + expectedTerm)
                .containsIgnoringCase(expectedTerm));
    }
}
