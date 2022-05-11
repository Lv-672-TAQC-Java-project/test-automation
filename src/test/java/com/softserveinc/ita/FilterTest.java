package com.softserveinc.ita;


import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.ProductAvailability;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.HOUSEHOLD_APPLIANCES;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.MANUFACTURER;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.PRODUCT_AVAILABILITY;
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

        var filteredProduct = subCategoryPage
                .getFilter()
                .filterBySection(MANUFACTURER, manufacturer)
                .getFilter()
                .filterBySection(PROCESSOR, processor)
                .getFilter()
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

        var memoryValueInCharacteristics = productCharacteristics.get("Обсяг оперативної пам'яті");
        int memorySize = Integer.parseInt(memoryValueInCharacteristics.replaceAll("[^\\d]", ""));

        int firstMemorySizeFromFilter = parseStringIntoNumbers(memoryValue, 0);
        int secondMemorySizeFromFilter = parseStringIntoNumbers(memoryValue, 1);

        assertThat(memorySize)
                .as("The size of RAM on the characteristics tab should be within the range of values of the selected filter")
                .isBetween(firstMemorySizeFromFilter, secondMemorySizeFromFilter);
    }

    private int parseStringIntoNumbers(String text, int index) {
        text = text.replaceAll("[^\\d-]", "");
        String[] parts = text.split("-");
        return Integer.parseInt(parts[index]);
    }
}
