package com.softserveinc.ita;


import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.ProductAvailability;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.apache.commons.lang.math.Range;
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

    @Description("Add a test script to cover a set of filters: 'Процесор', 'Обсяг оперативної пам'яті', 'Діагональ екрана' in the 'Ноутбуки' category")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-32")
    @Test(description = "LVTAQC672-32")
    public void verifyThatFilteredProductContainExpectedCharacteristics() {
        var subCategoryPage = homePage
                .getHeader()
                .openCatalog()
                .openSubCategoryPage(LAPTOPS_AND_COMPUTERS, " HP (Hewlett Packard) ");

        assertThat(subCategoryPage.getTitle())
                .as("The title of the subCategory page should contain HP")
                .containsIgnoringCase("HP");

        var processor = "Intel Core i5";
        var screenDiagonal = "15\"-15.6\"";
        var randomAccessMemoryAmount = "16 - 24 ГБ";

        var productCharacteristics = subCategoryPage
                .getFilter()
                .filterBySection(PROCESSOR, processor)
                .getFilter()
                .filterBySection(SCREEN_DIAGONAL, screenDiagonal)
                .getFilter()
                .filterBySection(RANDOM_ACCESS_MEMORY_AMOUNT, randomAccessMemoryAmount)
                .getProduct(1)
                .openDetailsPage()
                .openCharacteristicTab()
                .getProductCharacteristics();

        assertThat(productCharacteristics.get("Процесор"))
                .as("The processor name must contain a value from the filter " + processor)
                .containsIgnoringCase(processor);

        var screenDiagonalAmount = productCharacteristics.get("Діагональ екрана");
        var screenDiagonalValueFromCharacteristics = Double.parseDouble(screenDiagonalAmount.substring(0, 4));

        screenDiagonal = screenDiagonal.replaceAll("[^\\d-.]", "");
        String[] amount = screenDiagonal.split("-");
        var firstScreenDiagonalValue = Double.parseDouble(amount[0]);
        var secondScreenDiagonalValue = Double.parseDouble(amount[1]);

        assertThat(screenDiagonalValueFromCharacteristics)
                .as("Screen diagonal value on the characteristics tab should be within the range of values of the selected filter")
                .isBetween(firstScreenDiagonalValue, secondScreenDiagonalValue);

        var memoryAmount = productCharacteristics.get("Обсяг оперативної пам'яті");
        int memoryAmountFromCharacteristics = Integer.parseInt(memoryAmount.replaceAll("[^\\d]", ""));

        memoryAmount = memoryAmount.replaceAll("[^\\d-]", "");
        String[] parts = memoryAmount.split("-");
        int firstMemoryValue = Integer.parseInt(parts[0]);
        int secondMemoryValue = Integer.parseInt(parts[1]);

        assertThat(memoryAmountFromCharacteristics)
                .as("The amount of RAM on the characteristics tab should be within the range of values of the selected filter")
                .isBetween(firstMemoryValue, secondMemoryValue);
    }
}
