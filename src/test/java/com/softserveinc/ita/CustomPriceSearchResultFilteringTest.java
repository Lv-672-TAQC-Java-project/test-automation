package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomPriceSearchResultFilteringTest extends TestRunner {

    @Description("Verify filtering products by custom price functionality")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-7")
    @Test(description = "LVTAQC672-7")
    public void verifyCustomPriceFilteringFunctionality() {
        Header header = new Header();
        String searchTerm = "Asus";
        String priceRangeMinimum = "2000";
        String priceRangeMaximum = "24000";

        SearchResultPage searchResultPage = header
                .search(searchTerm)
                .getFilter()
                .filterByPrice(priceRangeMinimum, priceRangeMaximum);

        List<Product> products = searchResultPage.getProducts();
        List<Integer> pricesList = searchResultPage.getProductsPrices(products);

        int highestPrice = Collections.max(pricesList);
        int lowestPrice = Collections.min(pricesList);

        int intPriceRangeMaximum = Integer.valueOf(priceRangeMaximum);
        int intPriceRangeMinimum = Integer.valueOf(priceRangeMinimum);

        assertThat(highestPrice)
                .as("Highest price in the list shouldn't be more expensive than maximum custom price range value")
                .isLessThanOrEqualTo(intPriceRangeMaximum)
                .isGreaterThan(intPriceRangeMinimum);

        assertThat(lowestPrice)
                .as("Lowest price in the list shouldn't be less expensive than minimum custom price range value")
                .isGreaterThanOrEqualTo(intPriceRangeMinimum)
                .isLessThan(intPriceRangeMaximum);
    }
}
