package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteInComparisonListTest extends TestRunner {

    @Test
    public void verifyDeleteFunctionInComparisonList() {
        String productString = "Ванни";
        ComparisonPage comparisonPage = homePage
                .openMenu("Сантехніка та ремонт")
                .openSubMenu(productString)
                .addAmountProductsToComparison(3)
                .openComparisonModal()
                .openComparisonPage(productString);

        String expectedString = "Ванна";
        assertThat(comparisonPage.isHeadingProductContainText(expectedString))
                .as("heading in comparison should contain text " + expectedString).isTrue();
        assertThat(comparisonPage.getAmountAddedProducts())
                .as("should be three added products").isEqualTo(3);
        comparisonPage.deleteAmountAddedProducts(1);
        assertThat(comparisonPage.getAmountAddedProducts())
                .as("should be two added products").isEqualTo(2);
    }
}