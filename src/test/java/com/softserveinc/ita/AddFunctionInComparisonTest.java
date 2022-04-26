package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddFunctionInComparisonTest extends TestRunner {
    @Description("Verify that products added to the comparison")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-12")
    @Test(description = "LVTAQC672-12")
    public void verifyThatProductsAddedToTheComparison() {
        String searchFirstTerm = "Lenovo";
        String searchSecondTerm = "Планшет";

        Header header = homePage.getHeader();

        SearchResultPage searchResultPage = header
                .search(searchFirstTerm)
                .getFilter()
                .filterBySubCategoryLink("Планшети")
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons();

        ComparisonPage comparisonPage = header
                .openComparisonModal()
                .openComparisonPage(searchSecondTerm);

        List<ComparisonPageProduct> productList = comparisonPage.getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 2 products should be in comparison")
                .hasSize(2);

        productList.forEach(product -> assertThat(product.getProductName())
                .as("All products should contain " + searchFirstTerm)
                .containsIgnoringCase(searchFirstTerm)
                .as("All products should contain " + searchSecondTerm)
                .containsIgnoringCase(searchSecondTerm));

        comparisonPage
                .addMoreModels()
                .getProduct(3)
                .addToListOfComparisons();
        productList = header
                .openComparisonModal()
                .openComparisonPage(searchSecondTerm)
                .getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 3 products should be in comparison")
                .hasSize(3);

        productList.forEach(product -> assertThat(product.getProductName())
                .as("All products should contain " + searchSecondTerm)
                .containsIgnoringCase(searchSecondTerm));
    }
}
