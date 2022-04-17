package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.ComparisonPageProduct;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest extends TestRunner {

    @Test
    public void verifyDeleteFunction() {
        String nameSubCategory = "Ванни";
        String[] titleProducts = {
                "Ванна акрилова CERSANIT VIRGO 170 +",
                "Ванна акрилова BESCO Modern 130х70",
                "Ванна акрилова SERENA SE-4001 150x70"};

        ComparisonPage products = homePage
                .getSidebarCategory()
                .openCategory("Сантехніка та ремонт")
                .openSubCategory(nameSubCategory)
                .addProductsToComparison(titleProducts)
                .openComparisonModal()
                .openComparisonPage(nameSubCategory);

        String expectedString = "Ванна";

        for (ComparisonPageProduct product : products.getAllComparisonPageProducts()) {
            assertThat(product.getProductName())
                    .as("heading in comparison should contain text " + expectedString)
                    .containsIgnoringCase(expectedString);
        }
        assertThat(products.getAllComparisonPageProducts().size())
                .as("should be three added products")
                .isEqualTo(3);
        products.getProduct(titleProducts[0]).remove();
        assertThat(products.getAllComparisonPageProducts().size())
                .as("should be three added products")
                .isEqualTo(2);
    }
}
