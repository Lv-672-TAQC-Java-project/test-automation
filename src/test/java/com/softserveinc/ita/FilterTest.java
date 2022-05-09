package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.SubCategoryPage;
import com.softserveinc.ita.pageobjects.models.AdulthoodConfirmation;
import com.softserveinc.ita.pageobjects.models.ProductAvailability;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.ALCOHOLIC_BEVERAGES_AND_PRODUCTS;
import static com.softserveinc.ita.pageobjects.models.CategoryName.HOUSEHOLD_APPLIANCES;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.*;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.MATURATION_PERIOD;
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

    @Description("Add test script to cover the 'Витримка' section of the filter functionality")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-30")
    @Test(description = "LVTAQC672-30")
    public void verifyThatProductsAreFilteredByMaturationPeriod() {
        String category = "Віскі";

        var subCategoryPage = homePage
                .getCategorySideBar()
                .openCategoryPage(ALCOHOLIC_BEVERAGES_AND_PRODUCTS)
                .confirmAdulthood(AdulthoodConfirmation.CONFIRM)
                .openSubCategoryPage(category)
                .confirmAdulthood(AdulthoodConfirmation.CONFIRM);

        subCategoryPage.getFilter()
                .filterBySection(MATURATION_PERIOD, "до 30 років")
                .confirmAdulthood(AdulthoodConfirmation.CONFIRM);

        var filteredProductsList = subCategoryPage.getProducts();

        filteredProductsList.forEach(product -> assertThat(product.getMaturationPeriod((SubCategoryPage) subCategoryPage))
                .as("Products should be filtered by maturation period from 21 to 30 years")
                .isBetween(21, 30));
    }
}
