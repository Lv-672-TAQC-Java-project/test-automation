package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest extends TestRunner {

    @Test
    public void verifyDeleteFunctionInComparisonList() {
        String name = "Ванни";
        String[] titleProducts = {
                "Ванна акрилова CERSANIT VIRGO 170 +",
                "Ванна акрилова BESCO Modern 130х70",
                "Ванна акрилова SERENA SE-4001 150x70"};

        ComparisonPage comparisonPage = homePage
                .getCategory()
                .openCategory("Сантехніка та ремонт")
                .openSubCategory(name)
                .addProductsToComparison(titleProducts)
                .openComparisonModal()
                .openComparisonPage(name);

        String expectedString = "Ванна";

        assertThat(comparisonPage.isHeadingProductContainText(expectedString))
                .as("heading in comparison should contain text " + expectedString)
                .isTrue();
        assertThat(comparisonPage.getAmountAddedProducts())
                .as("should be three added products")
                .isEqualTo(3);
        comparisonPage.deleteAddedProducts(titleProducts[1]);
        assertThat(comparisonPage.getAmountAddedProducts())
                .as("should be two added products")
                .isEqualTo(2);
    }
}
