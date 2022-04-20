package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.ComparisonPageProduct;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.CategoryName.PLUMBING_AND_REPAIR;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest extends TestRunner {

    @Test
    public void verifyDeleteFunction() {
        String[] productNames = {
                "Ванна акрилова CERSANIT VIRGO 170 +",
                "Ванна акрилова BESCO Modern 130х70",
                "Ванна акрилова SERENA SE-4001 150x70"};

        ComparisonPage products = homePage
                .getCategorySideBar()
                .openCategoryPage(PLUMBING_AND_REPAIR)
                .openSubCategoryPage("Ванни")
                .addProductsToComparison(productNames)
                .openComparisonModal()
                .openComparisonPage("Ванни");

        String expectedString = "Ванна";

        for (ComparisonPageProduct product : products.getAllComparisonPageProducts()) {
            assertThat(product.getProductName())
                    .as("name in comparison should contain text " + expectedString)
                    .containsIgnoringCase(expectedString);
        }
        assertThat(products.getAllComparisonPageProducts())
                .as("should be three added products")
                .hasSize(3);
        products.getProduct(1).remove();
        assertThat(products.getAllComparisonPageProducts())
                .as("should be two added products")
                .hasSize(2);
    }
}
