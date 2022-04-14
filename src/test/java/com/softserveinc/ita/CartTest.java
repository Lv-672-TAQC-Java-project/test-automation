package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Header;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyThatTotalProductsPriceHasDoubled() {
        Header header = homePage.getHeader();
        String searchTerm = "Asus";
        int first = 1;
        header
                .search(searchTerm)
                .getProduct(first)
                .addToCart();

        int totalPrice = header
                .openCart()
                .getTotalPrice();

        int totalPriceUpdated = header
                .openCart()
                .getProduct(first)
                .addOneMoreProduct()
                .getTotalPrice();

        assertThat(totalPriceUpdated)
                .as("Total products price should double")
                .isEqualTo(totalPrice * 2);
    }
}
