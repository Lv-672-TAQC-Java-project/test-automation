package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends TestRunner {

    @Description("Verify that products include the search term in their names.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-3")
    @Test(description = "LVTAQC672-3")
    public void verifyThatProductsIncludeTheSearchTermInTheirNames() {
        String searchTerm = "Asus";
        var products = homePage
                .getHeader()
                .search(searchTerm)
                .getProducts();

        assertThat(products)
                .allSatisfy(product -> assertThat(product.getName())
                        .as(product.getName() + " should contain " + searchTerm)
                        .containsIgnoringCase(searchTerm));
    }
}
