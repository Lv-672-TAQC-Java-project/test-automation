package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.Header;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsComparisonTest extends TestRunner {
    @Test
    public void verifyShowOnlyDifferencesFunctionality() {
        String searchTerm = "notebook";
        String productCategory = "Ноутбуки";

        Header header = homePage.getHeader();

        SearchResultPage searchResultPage =
        header
                .search(searchTerm)
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons();

        String firstProductName = searchResultPage
                .getProduct(1)
                .getName();
        String secondProductName = searchResultPage
                .getProduct(2)
                .getName();

        ComparisonPage comparisonPage =
        header
                .openComparisonModal()
                .openComparisonPage(productCategory);

        int firstProductFullCharacteristicSize = comparisonPage
                .getProductCharacteristics(firstProductName)
                .size();
        int secondProductFullCharacteristicSize = comparisonPage
                .getProductCharacteristics(secondProductName)
                .size();

        assertThat(comparisonPage.showOnlyDifferences()
                .getProductCharacteristics(firstProductName))
                .isNotEqualTo(comparisonPage
                        .getProductCharacteristics(secondProductName));

        assertThat(firstProductFullCharacteristicSize)
                .isGreaterThan(comparisonPage
                        .getProductCharacteristics(firstProductName).size());

        assertThat(secondProductFullCharacteristicSize)
                .isGreaterThan(comparisonPage
                        .getProductCharacteristics(secondProductName).size());
    }
}
