package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.product.ComparisonPageProduct;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.softserveinc.ita.pageobjects.models.CategoryName.PLUMBING_AND_REPAIR;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparisonTest extends TestRunner {

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

    @Description("Verify that products added to the comparison")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-12")
    @Test(description = "LVTAQC672-12")
    public void verifyThatProductsAddedToTheComparison() {
        String searchTerm = "Lenovo";

        Header header = homePage.getHeader();

        SearchResultPage searchResultPage = header
                .search(searchTerm)
                .getFilterCategorySideBar()
                .filterBySubCategory("Планшети")
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons();

        String categoryName = "Планшет";
        ComparisonPage comparisonPage = header
                .openComparisonModal()
                .openComparisonPage(categoryName);

        List<ComparisonPageProduct> productList = comparisonPage.getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 2 products should be in comparison")
                .hasSize(2);

        productList
                .forEach(product -> assertThat(product.getProductName())
                        .as("All products should contain " + searchTerm)
                        .containsIgnoringCase(searchTerm)
                        .as("All products should contain " + categoryName)
                        .containsIgnoringCase(categoryName));

        comparisonPage
                .addMoreModels()
                .getProductNotAddedToComparison(1)
                .addToListOfComparisons();
        productList = header
                .openComparisonModal()
                .openComparisonPage(categoryName)
                .getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 3 products should be in comparison")
                .hasSize(3);

        productList
                .forEach(product -> assertThat(product.getProductName())
                        .as("All products should contain " + categoryName)
                        .containsIgnoringCase(categoryName));
    }
}
