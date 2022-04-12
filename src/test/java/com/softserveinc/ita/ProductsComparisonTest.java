package com.softserveinc.ita;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsComparisonTest extends TestRunner{
    @Test
    public void verifyShowOnlyDifferencesFunctionality() {
        String searchTerm = "notebook";
        String productCategory = "Ноутбуки";

        ComparisonPage comparisonPage = homePage
                .getHeader()
                .search(searchTerm)
                .addProductToComparisonModal(1)
                .addProductToComparisonModal(2)
                .getHeader()
                .openComparisonModal()
                .openComparisonPage(productCategory);


        assertThat(comparisonPage.getAllProductsCharacteristicsList())
                .as("Only different products characteristics should be displayed")
                .isNotEqualTo(comparisonPage.showOnlyDifferences().getAllProductsCharacteristicsList());
    }
}
