package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Product;
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

        for (Product product : products) {
            String productName = product.getName();
            System.out.println(productName);
            assertThat(productName)
                    .as(productName + " should contain " + searchTerm)
                    .containsIgnoringCase(searchTerm);
        }
    }
}
