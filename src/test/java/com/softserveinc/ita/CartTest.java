package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Cart;
import com.softserveinc.ita.pageobjects.InCartProduct;
import com.softserveinc.ita.pageobjects.Product;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends TestRunner {

    @Test
    public void verifyRemovalFunctionalityInTheCart() {

        Product firstProductFromSearch = homePage
                .getHeader()
                .search("Фотоапарати")
                .getProduct(1);

        String firstProductTitleOfTheSearchResult = firstProductFromSearch.getName();

        InCartProduct firstProductInTheCart = firstProductFromSearch
                .addToCart()
                .getHeader()
                .openCart()
                .getProduct(1);

        String firstProductTitleInTheCart = firstProductInTheCart.getName();

        assertThat(firstProductTitleOfTheSearchResult)
                .as("The cart should contain added product")
                .isEqualTo(firstProductTitleInTheCart);

        Cart cartAfterRemovingTheProduct = firstProductInTheCart
                .openCartProductActions()
                .removeProductFromCart();

        assertThat(cartAfterRemovingTheProduct.isCartEmpty())
                .as("Cart should be empty")
                .isTrue();
    }
}
