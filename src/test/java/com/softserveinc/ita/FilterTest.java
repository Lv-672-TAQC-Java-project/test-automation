package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.ProductAvailability;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.pageobjects.models.CategoryName.HOUSEHOLD_APPLIANCES;
import static com.softserveinc.ita.pageobjects.models.CategoryName.LAPTOPS_AND_COMPUTERS;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.*;
import static com.softserveinc.ita.pageobjects.models.ProductAvailability.OUT_OF_STOCK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilterTest extends TestRunner {
    @Description("Verify that filtered products contains searching term “Asus” in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-11")
    @Test(description = "LVTAQC672-11")
    public void verifyThatFilteredProductsContainsExpectedTerm() {
        var filteredProductsList = homePage
                .getHeader()
                .search("мобільний телефон")
                .getFilter()
                .filterBySection(MANUFACTURER, "Apple")
                .getProducts();

        var expectedTerm = "iPhone";
        filteredProductsList.forEach(product -> assertThat(product.getName())
                .as("Product name should contains " + expectedTerm)
                .containsIgnoringCase(expectedTerm));
    }

    @Description("Verify filtering products by custom price functionality")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-7")
    @Test(description = "LVTAQC672-7")
    public void verifyCustomPriceFilteringFunctionality() {
        String searchTerm = "Asus";
        int priceRangeMinimum = 2000;
        int priceRangeMaximum = 24000;

        SearchResultPage searchResultPage = homePage
                .getHeader()
                .search(searchTerm)
                .getFilter()
                .filterByPrice(priceRangeMinimum, priceRangeMaximum);

        var products = searchResultPage.getProducts();
        var prices = searchResultPage.getProductsPrices(products);

        prices.forEach(productPrice -> Assertions.assertThat(productPrice)
                .as("products prices should not exceed or be lower than custom price range")
                .isGreaterThanOrEqualTo(priceRangeMinimum)
                .isLessThanOrEqualTo(priceRangeMaximum));
    }
  
    @Description("Verify that filtered products contain 'Закінчився' status")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-13")
    @Test(description = "LVTAQC672-13")
    public void verifyThatFilteredProductsContainExpectedStatus() {

        var filteredProducts = homePage
                .getHeader()
                .openCatalog()
                .openSubCategoryPage(HOUSEHOLD_APPLIANCES, "Холодильники")
                .getFilter()
                .filterBySection(PRODUCT_AVAILABILITY, "Закінчився")
                .getProducts();

        ProductAvailability expectedAvailability = OUT_OF_STOCK;
        filteredProducts
                .forEach(product -> assertThat(product.getAvailability())
                        .as("Product name should contain " + expectedAvailability)
                        .isEqualTo(expectedAvailability));
    }

    @Test
    public void verifyThatFilteredProductsContainExpectedCharacteristics() {
        String subCategoryName = "Acer";
        var subCategoryPage = homePage
                .getHeader()
                .openCatalog()
                .openSubCategoryPage(LAPTOPS_AND_COMPUTERS, subCategoryName);

        assertThat(subCategoryPage.getTitle())
                .as("The title on the subCategoryPage should contain " + subCategoryName)
                .containsIgnoringCase(subCategoryName);

        var productCharacteristics = subCategoryPage
                .getFilter()
                .filterBySection(PROCESSOR,"Intel Core i5")
                .getFilter()
                .filterBySection(SCREEN_DIAGONAL,"15\"-15.6\"")
                .getFilter()
                .filterBySection(RANDOM_ACCESS_MEMORY_AMOUNT,"16 - 24 ГБ")
                .getProduct(1)
                .openDetailsPage()
                .openCharacteristicTab();
    }
}
