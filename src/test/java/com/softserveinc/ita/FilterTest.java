package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilterTest extends TestRunner {
    @Description("Verify that filtered products contains searching term 'iPhone' in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-11")
    @Test(description = "LVTAQC672-11")
    public void verifyThatFilteredProductsContainsExpectedTerm() {
        SearchResultPage searchResultPage = homePage
                .getHeader()
                .search("мобільний телефон")
                .getFilter()
                .filterByCategory("Виробник", "Apple");

        String expectedTerm = "iPhone";

        List<Product> filteredProductsList = searchResultPage.getProducts();
        filteredProductsList
                .forEach(product -> assertThat(product.getName())
                .as("Product name should contains " + expectedTerm)
                .containsIgnoringCase(expectedTerm));
    }
}
