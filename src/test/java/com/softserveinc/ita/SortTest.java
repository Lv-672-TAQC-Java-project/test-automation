package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.models.SortOrder;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SortTest extends TestRunner {
    @Description("Add test script to cover 'From cheap to expensive' sort functionality in Rozetka")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-15")
    @Test(description = "LVTAQC672-15")
    public void verifyThatProductsAreSortedFromCheapToExpensive() {
        var header = homePage.getHeader();
        String searchTerm = "Декоративна штукатурка RZTK";
        var searchResultPage = header.search(searchTerm);

        Assertions.assertThat(searchResultPage.getSearchTermLabel())
                .as("Search result page should contain label with" + searchTerm)
                .contains(searchTerm);

        var products = searchResultPage.getProducts();
        var productPrices = searchResultPage.getProductsPrices(products);
        Collections.sort(productPrices);

        var sortedProductsFromCheap = searchResultPage
                .sort(SortOrder.FROM_CHEAP)
                .getProducts();
        var sortedProductsFromCheapPrices = searchResultPage.getProductsPrices(sortedProductsFromCheap);

        assertThat(sortedProductsFromCheapPrices)
                .as("Prices of sorted products (from cheap to expensive) should increase")
                .isEqualTo(productPrices);
    }
}
