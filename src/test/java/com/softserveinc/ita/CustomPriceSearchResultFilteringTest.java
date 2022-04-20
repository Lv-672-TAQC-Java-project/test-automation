package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Product;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomPriceSearchResultFilteringTest extends TestRunner {

    @Test
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
        List<Integer> pricesList = searchResultPage
                .getDisplayedProductsPrices(products);

        int highestPrice = Collections.max(pricesList);
        int lowestPrice = Collections.min(pricesList);

        int intPriceRangeMaximum = Integer.valueOf(priceRangeMaximum);
        int intPriceRangeMinimum = Integer.valueOf(priceRangeMinimum);

        assertThat(priceRangeMaximum)
                .as("Filter option won't be available if minimal desirable price is higher than maximal")
                .isGreaterThan(priceRangeMinimum);

        assertThat(highestPrice)
                .as("Highest price in the list shouldn't be more expensive than maximum custom price range value")
                .isLessThanOrEqualTo(intPriceRangeMaximum)
                .isGreaterThanOrEqualTo(intPriceRangeMinimum);

        assertThat(lowestPrice)
                .as("Lowest price in the list shouldn't be less expensive than minimum custom price range value")
                .isGreaterThanOrEqualTo(intPriceRangeMinimum)
                .isLessThanOrEqualTo(intPriceRangeMaximum);
    }
}
