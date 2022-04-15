package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Cart;
import com.softserveinc.ita.pageobjects.Header;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyThatTotalProductsPriceHasDoubled() {
        Header header = homePage.getHeader();
        String searchTerm = "Asus";
        header
                .search(searchTerm)
                .getProduct(1)
                .addToCart();

        Cart cart = header.openCart();
        int totalPrice = cart.getTotalPrice();

        int totalPriceUpdated = cart
                .getProduct(1)
                .addOneMoreProduct()
                .getTotalPrice();

        assertThat(totalPriceUpdated)
                .as("Total products price should double")
                .isEqualTo(totalPrice * 2);
    }
}
