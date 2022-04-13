package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsComparisonTest extends TestRunner {
    @Test
    public void verifyShowOnlyDifferencesFunctionality() {
        String searchTerm = "notebook";
        String productCategory = "Ноутбуки";
        var header = homePage.getHeader();

        SearchResultPage searchResultPage = header
                .search(searchTerm)
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons();

        ComparisonPage comparisonPage = header
                .openComparisonModal()
                .openComparisonPage(productCategory);

        assertThat(comparisonPage.getAllProductsCharacteristicsList())
                .as("Only different products characteristics should be displayed")
                .isNotEqualTo(comparisonPage
                        .showOnlyDifferences()
                        .getAllProductsCharacteristicsList());
    }
}
