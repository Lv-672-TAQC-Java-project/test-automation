package com.softserveinc.ita;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsComparisonTest extends TestRunner{
    @Test
    public void showOnlyDifferencesComparisonTest() {
        String searchTerm = "notebook";
        ComparisonPage comparisonPage = homePage
                .getHeader()
                .search(searchTerm)
                .addProductToComparisonModal(1)
                .addProductToComparisonModal(2)
                .getHeader()
                .openComparisonModal()
                .openComparisonPage();

        assertThat(comparisonPage.getAllCharacteristicsText())
                .as("Only different products characteristics should be displayed")
                .isNotEqualTo(comparisonPage.showOnlyDifferences().getAllCharacteristicsText());


    }
}
