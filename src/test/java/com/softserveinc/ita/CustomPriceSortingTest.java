package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Product;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomPriceSortingTest extends TestRunner {

    @Test
    public void verifyCustomPriceSortingFunctionality() {
        String searchTerm = "Asus";
        String priceRangeMinimum = "1000";
        String priceRangeMaximum = "27600";

        SearchResultPage searchResultPage = homePage
                .getHeader()
                .search(searchTerm)
                .getSearchResultSidebar()
                .getPriceRangeComponent()
                .typeInMinimumPriceRange(priceRangeMinimum)
                .typeInMaximumPriceRange(priceRangeMaximum)
                .sortSearchResultsByPriceRange();

        List<Product> products = searchResultPage.getProducts();
        List<Integer> pricesList = searchResultPage
                .getAllProductsPrices(products);

        String highestPrice = String.valueOf(Collections.max(pricesList));
        String lowestPrice = String.valueOf(Collections.min(pricesList));

        assertThat(highestPrice)
                .as("Highest price in the list shouldn't be more expensive than maximum custom price range value")
                .isLessThanOrEqualTo(priceRangeMaximum)
                .isGreaterThan(priceRangeMinimum);

        assertThat(lowestPrice)
                .as("Lowest price in the list shouldn't be less expensive than minimum custom price range value")
                .isGreaterThanOrEqualTo(priceRangeMinimum)
                .isLessThan(priceRangeMaximum);
    }
}
