package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.ProductAvailability;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.HOUSEHOLD_APPLIANCES;
import static com.softserveinc.ita.pageobjects.models.CategoryName.TOOLS_AND_AUTOMOTIVE_PRODUCTS;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.MANUFACTURER;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.PRODUCT_AVAILABILITY;
import static com.softserveinc.ita.pageobjects.models.ProductAvailability.OUT_OF_STOCK;
import static java.lang.String.format;
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

    @Description("Verified that product characteristic contains searched value")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-29")
    @Test(description = "LVTAQC672-29")
    public void verifyThatProductCharacteristicContainsSearchedValue() {
        var subCategoryPage = homePage
                .getCategorySideBar()
                .openCategoryPage(TOOLS_AND_AUTOMOTIVE_PRODUCTS)
                .openSubCategoryPage("Автошини та диски");

        var tiresSubcategoryFilterSection = subCategoryPage.getTiresSubcategoryFilterSection();
        assertThat(tiresSubcategoryFilterSection.isOpened())
                .as("Tires subcategory filter section should be displayed")
                .isTrue();

        var expectedTireCharacteristic = "245/40 R19";
        tiresSubcategoryFilterSection
                .setYear("2015")
                .setBrand("Audi")
                .setModel("A6")
                .setCarConfiguration("2.0 TDi")
                .selectTireSize(expectedTireCharacteristic)
                .filter();

        var firstProduct = subCategoryPage.getProduct(1);
        var firstProductName = firstProduct.getName();

        var productDetailsPage = firstProduct.openDetailsPage();
        var productName = productDetailsPage.getProductName();

        assertThat(productName)
                .as("Product name should be the same for subcategory page and product details page")
                .isEqualTo(firstProductName);

        var actualTireCharacteristics = productDetailsPage
                .openCharacteristicTab()
                .getProductCharacteristics();

        var tireWidth = actualTireCharacteristics
                .get("Ширина шини")
                .split(" ")[0];
        var tireProfile = actualTireCharacteristics.get("Профіль");
        var tireDiameter = actualTireCharacteristics.get("Діаметр");
        var actualTireCharacteristic = format("%s/%s %s", tireWidth, tireProfile, tireDiameter);

        assertThat(expectedTireCharacteristic)
                .as("Tire characteristic should be " + expectedTireCharacteristic)
                .isEqualTo(actualTireCharacteristic);
    }
}
