package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Cart;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.models.CategoryName;
import com.softserveinc.ita.pageobjects.product.InCartProduct;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.pageobjects.product.RecommendedProduct;
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

    @Description("Verify that product items quantity and summary price in the 'Кошик' pop-up window increased" +
            "after adding first product from 'Також рекомендуємо' section.")
    @Issue("https://jira.softserve.academy/projects/LVTAQC672/issues/LVTAQC672-10")
    @Test
    public void verifyAddingProductFromRecommendationsSection() {
//      Test precondition
        homePage.emptyCart();

        Header header = homePage.getHeader();
        header
                .openCatalog()
                .openSubCategoryPage(CategoryName.SPORTS_AND_HOBBIES, "Ракетки для настільного тенісу")
                .getProduct(1)
                .addToCart();

        Cart cart = header.openCart();

        int productPrice = cart.getTotalPrice();

        RecommendedProduct recommendedProduct = cart.getRecommendedProduct(1);

        int recommendedProductPrice = recommendedProduct.getPrice();

        int totalPriceAfterAddingRecommendedProduct = recommendedProduct
                .addToCart()
                .getTotalPrice();

        assertThat(totalPriceAfterAddingRecommendedProduct)
                .as("Total price after adding recommended product should increase " +
                        "in amount of recommended product price")
                .isEqualTo(productPrice + recommendedProductPrice);
    }
}