package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.CategoryPage;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.models.ProductAvailability;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.pageobjects.models.CategoryName.CLOTHES_SHOES_AND_JEWELRY;
import static com.softserveinc.ita.pageobjects.models.CategoryName.HOUSEHOLD_APPLIANCES;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.*;
import static com.softserveinc.ita.pageobjects.models.PopularBrandName.ADIDAS;
import static com.softserveinc.ita.pageobjects.models.PopularBrandName.ASICS;
import static com.softserveinc.ita.pageobjects.models.ProductAvailability.OUT_OF_STOCK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilterTest extends TestRunner {
    @Description("Verify that filtered products contains searching term “Asus” in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-11")
    @Test(description = "LVTAQC672-11")
    public void verifyThatFilteredProductsContainsExpectedTerm() {
        List<Product> filteredProductsList = homePage
                .getHeader()
                .search("мобільний телефон")
                .getFilter()
                .filterBySection(MANUFACTURER, "Apple")
                .getProducts();

        String expectedTerm = "iPhone";
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

        List<Product> products = searchResultPage.getProducts();
        List<Integer> pricesList = searchResultPage.getProductsPrices(products);

        pricesList.forEach(productPrice -> Assertions.assertThat(productPrice)
                .as("products prices should not exceed or be lower than custom price range")
                .isGreaterThanOrEqualTo(priceRangeMinimum)
                .isLessThanOrEqualTo(priceRangeMaximum));
    }
  
    @Description("Verify that filtered products contain 'Закінчився' status")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-13")
    @Test(description = "LVTAQC672-13")
    public void verifyThatFilteredProductsContainExpectedStatus() {

        List<Product> filteredProducts = homePage
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

    @Description("Verify that the 'ASICS' checkbox in the 'Alphabetical index' filter section is checked and " +
            "filtered products contains searching term “ASICS” in their names.")
    @Issue("https://jira.softserve.academy/projects/LVTAQC672/issues/LVTAQC672-34")
    @Test(description = "LVTAQC672-34")
    public void verifyPopularBrandsBlockFunctionality() {
        var searchResultPage = homePage
                .getHeader()
                .openCatalog()
                .openCategoryPage(CLOTHES_SHOES_AND_JEWELRY)
                .openPopularBrendProductsPage(ASICS);

        var filter = searchResultPage.getFilter();
        filter.setBrandName(ASICS);

        String brandName = ASICS.getPopularBrandName();

        assertThat(filter
                .getCheckboxTag()
                .text()
                .equalsIgnoreCase(brandName))
                    .as("Brand name checkbox should be checked")
                    .isTrue();

        var brandProducts = searchResultPage.getProducts();

        brandProducts.forEach(product -> assertThat(product.getName())
                .as("Product name should contains " + brandName)
                .containsIgnoringCase(brandName));
    }
}
