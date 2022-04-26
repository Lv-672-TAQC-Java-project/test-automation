package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomPriceProductsFilteringTest extends TestRunner {

    @Description("Verify filtering products by custom price functionality")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-7")
    @Test(description = "LVTAQC672-7")
    public void verifyCustomPriceFilteringFunctionality() {
        String searchTerm = "Asus";
        String priceRangeMinimum = "2000";
        String priceRangeMaximum = "24000";

        SearchResultPage searchResultPage = homePage
                .getHeader()
                .search(searchTerm)
                .getFilter()
                .filterByPrice(priceRangeMinimum, priceRangeMaximum);

        List<Product> products = searchResultPage.getProducts();
        List<Integer> pricesList = searchResultPage.getProductsPrices(products);

        int intPriceRangeMaximum = Integer.valueOf(priceRangeMaximum);
        int intPriceRangeMinimum = Integer.valueOf(priceRangeMinimum);

        assertThat(intPriceRangeMaximum)
                .as("Maximal custom price value can't be lower than minimal custom price value")
                .isGreaterThanOrEqualTo(intPriceRangeMinimum);

        pricesList.forEach(productPrice -> assertThat(productPrice)
                        .as("products prices should not exceed or be lower than custom price range")
                        .isGreaterThanOrEqualTo(intPriceRangeMinimum)
                        .isLessThanOrEqualTo(intPriceRangeMaximum));
    }
}
