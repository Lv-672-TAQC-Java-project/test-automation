package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Header;
import com.softserveinc.ita.pageobjects.Product;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilterTest extends TestRunner {
    @Description("Verify that filtered products contains searching term “Asus” in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-11")
    @Test(description = "LVTAQC672-11")
    public void verifyThatFilteredProductsContainsExpectedTerm() {
        List<Product> filteredProductsList = homePage
                .getHeader()
                .search("мобільний телефон")
                .getFilter()
                .filterByCategory("Виробник", "Apple")
                .getProducts();

        String expectedTerm = "iPhone";
        filteredProductsList.forEach(product -> assertThat(product.getName())
                .as("Product name should contains " + expectedTerm)
                .containsIgnoringCase(expectedTerm));
    }

    @Description("Add test script to cover 'From cheap to expensive' filter functionality in Rozetka")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-15")
    @Test(description = "LVTAQC672-15")
    public void verifyThatFilteredProductsAreSortedFromCheapToExpensive(){
        Header header = homePage.getHeader();
        String searchTerm = "Asus";
        SearchResultPage searchResultPage = header.search(searchTerm);

        Assertions.assertThat(searchResultPage.getSearchTermLabel())
                .as("Search result page should contain label with" + searchTerm)
                .contains(searchTerm);

        List<Product> filteredProductsList = searchResultPage
                .sortProductsFromCheapToExpensive()
                .getProducts();

        List<Integer> pricesOfSortedProductsList = searchResultPage
                .getPricesOfProducts(filteredProductsList);

        boolean actualResult = searchResultPage.isPricesOfProductsIncreasing(pricesOfSortedProductsList);
        assertThat(actualResult)
                .as("Price of sorted products (from cheap to expensive) should increasing")
                .isTrue();
    }
}
