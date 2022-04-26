package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.ita.pageobjects.models.CategoryName.PLUMBING_AND_REPAIR;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteFromComparisonPageTest extends TestRunner {

    @Description("Add test script to cover delete function in Rozetka's comparison list")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-9")
    @Test(description = "LVTAQC672-9")
    public void verifyDeleteProductFromComparisonPage() {
        List<String> productNames = new ArrayList<>();
        productNames.add("Ванна акрилова CERSANIT VIRGO 170 +");
        productNames.add("Ванна акрилова BESCO Modern 130х70");
        productNames.add("Ванна акрилова SERENA SE-4001 150x70");

        String category = "Ванни";

        ComparisonPage comparisonProducts = homePage
                .getCategorySideBar()
                .openCategoryPage(PLUMBING_AND_REPAIR)
                .openSubCategoryPage(category)
                .addProductsToComparison(productNames)
                .openComparisonModal()
                .openComparisonPage(category);

        String expectedString = "Ванна";

        comparisonProducts.getAllComparisonPageProducts()
                .forEach(product -> assertThat(product.getProductName())
                        .as("name in comparison should contain text " + expectedString)
                        .containsIgnoringCase(expectedString));

        assertThat(comparisonProducts.getAllComparisonPageProducts())
                .as("should be three added products")
                .hasSize(3);
        comparisonProducts
                .getProduct(1)
                .remove();
        assertThat(comparisonProducts.getAllComparisonPageProducts())
                .as("should be two added products")
                .hasSize(2);
    }
}
