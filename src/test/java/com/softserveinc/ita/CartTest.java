package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyRemovalFunctionalityInTheCart() {
        var header = homePage.getHeader();

        Product firstProduct = header
                .search("Фотоапарати")
                .getProduct(1);

        String firstProductName = firstProduct.getName();

        firstProduct.addToCart();

        InCartProduct productInCart = header
                .openCart()
                .getProduct(firstProductName);

        String productNameInCart = productInCart.getName();

        assertThat(firstProductName)
                .as("The product name in the cart should be equal to the name of added product")
                .isEqualTo(productNameInCart);

        Cart cart = productInCart.remove();

        assertThat(cart.isEmpty())
                .as("Cart should be empty")
                .isTrue();
    }

    @Test
    public void verifyThatProductItemAddedToTheCart() {
        String searchTerm = "DeWALT";
        Header header = homePage.getHeader();
        SearchResultPage searchResultPage = header.search(searchTerm);

        assertThat(searchResultPage.getSearchTermLabel())
                .as("Search term label should be displayed")
                .contains(searchTerm);

        Product firstProduct = searchResultPage.getProduct(1);
        String firstProductName = firstProduct.getName();

        firstProduct.addToCart();

        Cart cart = header.openCart();

        assertThat(cart.isOpened())
                .as("Cart modal should be displayed")
                .isTrue();

        List<InCartProduct> inCartProducts = cart.getInCartProducts();
        assertThat(inCartProducts)
                .anySatisfy(product -> assertThat(product.getName())
                        .as(product.getName() + " should contain " + firstProductName)
                        .containsIgnoringCase(firstProductName));
    }

    @Test
    public void verifyThatTotalProductsPriceHasDoubled() {
        Header header = homePage.getHeader();
        String searchTerm = "Asus";

        Cart cart = header.openCart();
        while (!cart.isEmpty()) {
            cart
                    .getProduct(1)
                    .remove();
        }
        cart.close();

        String productName = "Ноутбук ASUS TUF Gaming F15 FX506HCB-HN161 (90NR0723-M04940) Eclipse Gray";
        header
                .search(searchTerm)
                .getProduct(productName)
                .addToCart();

        header.openCart();
        int totalPrice = cart.getTotalPrice();

        int totalPriceUpdated = cart
                .getProduct(productName)
                .addOneMoreProduct()
                .getTotalPrice();

        assertThat(totalPriceUpdated)
                .as("Total products price should double")
                .isEqualTo(totalPrice * 2);
    }
}
