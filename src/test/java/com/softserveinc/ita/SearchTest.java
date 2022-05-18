package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.softserveinc.ita.pageobjects.models.CategoryName.SPORTS_AND_HOBBIES;

public class SearchTest extends TestRunner {

    @Description("Verify that products include the search term in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-3")
    @Test(description = "LVTAQC672-3")
    public void verifyThatProductsIncludeTheSearchTermInTheirNames() {
        String searchTerm = "Asus";
        var products = homePage
                .getHeader()
                .search(searchTerm)
                .getProducts();

        assertThat(products)
                .allSatisfy(product -> assertThat(product.getName())
                        .as(product.getName() + " should contain " + searchTerm)
                        .containsIgnoringCase(searchTerm));
    }

    @Description("Verified that it's possible to search product by code")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-43")
    @Test(description = "LVTAQC672-43")
    public void verifyThatItIsPossibleToSearchProductByCode() {
        var subCategoryPage = homePage
                .getCategorySideBar()
                .openCategoryPage(SPORTS_AND_HOBBIES)
                .openSubCategoryPage("Електротранспорт");

        assertThat(subCategoryPage.isOpened())
                .as("Subcategory page should be displayed")
                .isTrue();

        var productDetailsPage = subCategoryPage
                .getProduct(1)
                .openDetailsPage();

        assertThat(productDetailsPage.isOpened())
                .as("Product details page should be displayed")
                .isTrue();

        var productName = productDetailsPage.getProductName();
        var productCode = productDetailsPage.getProductCode();
        productDetailsPage.goBack();

        assertThat(subCategoryPage.isOpened())
                .as("Subcategory page should be displayed")
                .isTrue();

        subCategoryPage
                .getHeader()
                .search(productCode);

        assertThat(productDetailsPage.isOpened())
                .as("Product details page should be displayed")
                .isTrue();

        var searchedProductName = productDetailsPage.getProductName();

        var softAssert = new SoftAssertions();
        softAssert.assertThat(productName)
                .as("Product name should match the searched product")
                .isEqualTo(searchedProductName);

        var searchedProductCode = productDetailsPage.getProductCode();
        softAssert.assertThat(productCode)
                .as("Product code should match the searched product")
                .isEqualTo(searchedProductCode);

        softAssert.assertAll();
    }
}
