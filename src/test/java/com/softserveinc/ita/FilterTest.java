package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.*;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.*;
import static com.softserveinc.ita.pageobjects.models.ProductState.UNAVAILABLE;
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
        var searchTerm = "Asus";
        int priceRangeMinimum = 2000;
        int priceRangeMaximum = 24000;

        SearchResultPage searchResultPage = homePage
                .getHeader()
                .search(searchTerm)
                .getFilter()
                .filterByPrice(priceRangeMinimum, priceRangeMaximum);

        var softAssert = new SoftAssertions();

        softAssert.assertThat(searchResultPage.isCustomPriceTagDisplayed())
                .as("Tag with lowest and highest desirable price boundaries should be visible")
                .isTrue();

        var products = searchResultPage.getProducts();
        var prices = searchResultPage.getProductsPrices(products);

        prices.forEach(productPrice -> Assertions.assertThat(productPrice)
                .as("products prices should not exceed or be lower than custom price range")
                .isGreaterThanOrEqualTo(priceRangeMinimum)
                .isLessThanOrEqualTo(priceRangeMaximum));

        softAssert.assertAll();
    }

    @Description("Verify that filtered products have expected state")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-13")
    @Test(description = "LVTAQC672-13")
    public void verifyThatFilteredProductsHaveExpectedState() {
        var filteredProducts = homePage
                .getHeader()
                .openCatalog()
                .openSubCategoryPage(HOUSEHOLD_APPLIANCES, "Холодильники")
                .getFilter()
                .filterBySection(PRODUCT_AVAILABILITY, "Закінчився")
                .getProducts();

        var expectedState = UNAVAILABLE;
        filteredProducts
                .forEach(product -> assertThat(product.getState())
                        .as("Product name should be " + expectedState)
                        .isEqualTo(expectedState));
    }

    @Description("Verified that product characteristic contains searched value")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-29")
    @Test(description = "LVTAQC672-29")
    public void verifyThatProductCharacteristicContainsValueSearchedUsingAdditionalFilterSection() {
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

    @Description("Add a test script to cover a set of filters: 'Процесор', 'Бренд', 'Обсяг оперативної пам'яті' in the 'Ноутбуки' subcategory")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-32")
    @Test(description = "LVTAQC672-32")
    public void verifyThatFilteredProductContainsExpectedCharacteristics() {
        var subCategoryPage = homePage
                .getCategorySideBar()
                .openCategoryPage(LAPTOPS_AND_COMPUTERS)
                .openSubCategoryPage("Ноутбуки");

        var manufacturer = "HP";
        var processor = "Intel Core i5";
        var memoryValue = "16 - 24 ГБ";

        var filter = subCategoryPage.getFilter();
        filter.filterBySection(MANUFACTURER, manufacturer);
        filter.filterBySection(PROCESSOR, processor);
        var filteredProduct = filter
                .filterBySection(RAM_SIZE, memoryValue)
                .getProduct(1);

        assertThat(filteredProduct.getName())
                .as("Product name should contain " + manufacturer)
                .containsIgnoringCase(manufacturer);

        var productCharacteristics = filteredProduct
                .openDetailsPage()
                .openCharacteristicTab()
                .getProductCharacteristics();

        assertThat(productCharacteristics.get("Процесор"))
                .as("The processor name should contain a value from the filter " + processor)
                .containsIgnoringCase(processor);

        int memorySizeInCharacteristics = Integer.parseInt(productCharacteristics.get("Обсяг оперативної пам'яті")
                .replaceAll("[^\\d]", ""));

        int firstMemorySizeFromFilter = parseStringIntoNumbers(memoryValue, 0);
        int secondMemorySizeFromFilter = parseStringIntoNumbers(memoryValue, 1);

        assertThat(memorySizeInCharacteristics)
                .as("The size of RAM on the characteristics tab should be within the range of values of the selected filter")
                .isBetween(firstMemorySizeFromFilter, secondMemorySizeFromFilter);
    }

    private int parseStringIntoNumbers(String text, int index) {
        text = text.replaceAll("[^\\d-]", "");
        String[] parts = text.split("-");
        return Integer.parseInt(parts[index]);
    }

    @Description("Add test script to verify that amount of filtered products is equal to the number \n" +
            "of products in the filter checkbox")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-30")
    @Test(description = "LVTAQC672-30")
    public void verifyThatFilteredProductsAmountIsEqualToAmountInFilterCheckbox() {
        var searchTerm = "Віскі";

        var searchResultPage = homePage
                .getHeader()
                .search(searchTerm)
                .getAdulthoodConfirmationModal()
                .confirmAdulthood();

        var filter = searchResultPage.getFilter();

        var filterCheckboxName = "до 45 років";
        int productsAmountInFilterCheckbox = filter.getProductsAmountInFilterCheckbox(MATURATION_PERIOD, filterCheckboxName);

        var filteredProductsList = filter
                .filterBySection(MATURATION_PERIOD, filterCheckboxName)
                .getProducts();

        Assertions.assertThat(filteredProductsList)
                .as("Amount of filtered products should be equal to number of products in filter checkbox")
                .hasSize(productsAmountInFilterCheckbox);
    }
}