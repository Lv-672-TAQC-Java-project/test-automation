package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.AdditionalProductService;
import com.softserveinc.ita.pageobjects.Cart;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.product.InCartProduct;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
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

    @Description("Verify that product item added to the cart")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-1")
    @Test(description = "LVTAQC672-1")
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

    @Description("Add test script to cover 'Additional services' functionality in Rozetka’s cart " +
            "and that the total products price works correctly")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-14")
    @Test(description = "LVTAQC672-14")
    public void verifyThatTotalPriceChangedAfterAddingAdditionalService() {
        Header header = homePage.getHeader();
        String searchTerm = "Asus";
        SearchResultPage searchResultPage = header.search(searchTerm);

        assertThat(searchResultPage.getSearchTermLabel())
                .as("Search result page should contain label with" + searchTerm)
                .contains(searchTerm);

        Product firstProduct = searchResultPage.getProduct(1);
        firstProduct.addToCart();

        Cart cart = header.openCart();
        String firstProductName = firstProduct.getName();
        InCartProduct cartProduct = cart.getProduct(firstProductName);
        String cartProductName = cartProduct.getName();

        assertThat(cartProductName)
                .as("Product name in cart should be same as name of added product to it")
                .contains(firstProductName);

        int totalPrice = cart.getTotalPrice();
        cartProduct.expandAdditionalServicesSection();
        AdditionalProductService additionalProductService = cartProduct.getAdditionalProductService(cartProductName, 1);
        additionalProductService.select();
        int totalPriceUpdated = cart.getTotalPrice();
        int additionalProductServiceCost = additionalProductService.getPrice();

        assertThat(totalPriceUpdated)
                .as("Total price should be increased by the cost of the first selected additional service")
                .isEqualTo(totalPrice + additionalProductServiceCost);
    }
}