package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends TestRunner {
    @Test
    public void verifyThatProductsIncludeTheSearchTermInTheirNames() {
        String searchTerm = "Asus";
        List<Product> products = homePage
                .getHeader()
                .search(searchTerm)
                .getProducts();

        assertThat(products)
                .allSatisfy(product -> assertThat(product.getName())
                        .as(product.getName() + " should contain " + searchTerm)
                        .containsIgnoringCase(searchTerm));
    }
}
