package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.Cart;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.models.CategoryName;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.COTTAGE_GARDEN_AND_VEGETABLE_GARDEN;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.PRODUCT_AVAILABILITY;
import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends TestRunner {

    @Description("Add a test script to cover the removal functionality in the cart")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-5")
    @Test(description = "LVTAQC672-5")
    public void verifyRemovalFunctionalityInTheCart() {
        homePage.emptyCart();
        var header = homePage.getHeader();

        var firstProduct = header
                .search("Фотоапарати")
                .getProduct(1);

        String firstProductName = firstProduct.getName();

        firstProduct.addToCart();

        var productInCart = header
                .openCart()
                .getProduct(firstProductName);

        String productNameInCart = productInCart.getName();

        assertThat(firstProductName)
                .as("The product name in the cart should be equal to the name of added product")
                .isEqualTo(productNameInCart);

        var cart = productInCart.remove();

        assertThat(cart.isEmpty())
                .as("Cart should be empty")
                .isTrue();
    }

    @Description("Verify that product item added to the cart")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-1")
    @Test(description = "LVTAQC672-1")
    public void verifyThatProductItemAddedToTheCart() {
        var searchTerm = "DeWALT";
        var header = homePage.getHeader();
        var searchResultPage = header.search(searchTerm);

        assertThat(searchResultPage.getSearchTermLabel())
                .as("Search term label should be displayed")
                .contains(searchTerm);

        var firstProduct = searchResultPage.getProduct(1);
        var firstProductName = firstProduct.getName();

        firstProduct.addToCart();

        var cart = header.openCart();

        assertThat(cart.isOpened())
                .as("Cart modal should be displayed")
                .isTrue();

        var inCartProducts = cart.getInCartProducts();
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
        homePage.emptyCart();

        var header = homePage.getHeader();
        var searchTerm = "Asus";
        var searchResultPage = header.search(searchTerm);

        var softAssert = new SoftAssertions();

        softAssert.assertThat(searchResultPage.getSearchTermLabel())
                .as("Search result page should contain label with" + searchTerm)
                .contains(searchTerm);

        var firstProduct = searchResultPage.getProduct(1);
        var firstProductName = firstProduct.getName();
        firstProduct.addToCart();

        var cart = header.openCart();

        var cartProduct = cart.getProduct(firstProductName);
        var cartProductName = cartProduct.getName();

        softAssert.assertThat(cartProductName)
                .as("Product name in cart should be same as name of added product to it")
                .contains(firstProductName);

        int totalPrice = cart.getTotalPrice();
        cartProduct.expandAdditionalServicesSection();
        var additionalProductService = cartProduct.getAdditionalProductService(cartProductName, 1);
        additionalProductService.select();
        int additionalProductServiceCost = additionalProductService.getPrice();

        softAssert.assertThat(cart.isOpened())
                .as("Cart modal should be displayed")
                .isTrue();

        softAssert.assertAll();

        int totalPriceUpdated = cart.getTotalPrice();

        assertThat(totalPriceUpdated)
                .as("Total price should be increased by the cost of the first selected additional service")
                .isEqualTo(totalPrice + additionalProductServiceCost);
    }

    @Description("Verify that product items quantity and summary price in the 'Кошик' pop-up window increased" +
            "after adding first product from 'Також рекомендуємо' section.")
    @Issue("https://jira.softserve.academy/projects/LVTAQC672/issues/LVTAQC672-10")
    @Test
    public void verifyAddingProductFromRecommendationsSection() {
        homePage.emptyCart();

        var header = homePage.getHeader();

        var product = header
                .openCatalog()
                .openSubCategoryPage(CategoryName.SPORTS_AND_HOBBIES, "Ракетки для настільного тенісу")
                .getProduct(1);

        int productPrice = product.getPrice();

        product.addToCart();

        var cart = header.openCart();

        var recommendedProduct = cart.getRecommendedProduct(1);

        int recommendedProductPrice = recommendedProduct.getPrice();

        recommendedProduct.addToCart();

        assertThat(cart.isOpened())
                .as("Cart modal should be displayed")
                .isTrue();

        int totalPrice = cart.getTotalPrice();

        assertThat(totalPrice)
                .as("Total price after adding recommended product should increase " +
                        "in amount of recommended product price")
                .isEqualTo(productPrice + recommendedProductPrice);
    }

    @Description("Verify that the total products price has doubled after adding one more product from the cart.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-4")
    @Test(description = "LVTAQC672-4")
    public void verifyThatTotalProductsPriceHasDoubled() {
        homePage.emptyCart();

        Header header = homePage.getHeader();
        header
                .openCatalog()
                .openSubCategoryPage(COTTAGE_GARDEN_AND_VEGETABLE_GARDEN, " Cаджанці дерев ")
                .getFilter()
                .filterBySection(PRODUCT_AVAILABILITY, "Є в наявності")
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

    @Description("Add test script to verify that user can't add more than one same flawed product to the cart.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-38")
    @Test(description = "LVTAQC672-38")
    public void verifyThatUserCanAddOnlyOneSameFlawedProductToTheCart() {
        var header = homePage.getHeader();
        var flawedProductsCategory = "телевізори та монітори";

        var flawedProductsPage =
                homePage
                        .getCategorySideBar()
                        .openFlawedProductsPage();

        var flawedProductsPageLabel = flawedProductsPage.getCategoryLabel();

        var subCategoryPage = flawedProductsPage.openFlawedProductsCategoryPage(flawedProductsCategory);

        var subCategoryPageLabel = subCategoryPage.getFlawedProductsSubCategoryLabel(flawedProductsPageLabel);

        var softAssert = new SoftAssertions();

        softAssert.assertThat(subCategoryPageLabel)
                .as("Search result page category label should contain " + flawedProductsCategory)
                .contains(flawedProductsCategory);

        var flawedProduct = subCategoryPage.getProduct(1);
        var isProductDefectVisible = flawedProduct.isDefectDescriptionVisible();

        softAssert.assertThat(isProductDefectVisible)
                .as("Red description message should be visible after hovering mouse over flawed product")
                .isTrue();

        flawedProduct.addToCart();
        var cart = header.openCart();
        var cartProduct = cart.getProduct(1);

        var cartProductName = cartProduct.getName();
        var expectedWord = "Уцінка";

        softAssert.assertThat(cartProductName)
                .as("Flawed product name should have word 'Уцінка' in its name")
                .endsWith(expectedWord);

        softAssert.assertAll();

        cartProduct.addOneMoreProduct();
        var alertMessage = cartProduct.getAlertMessage();
        var expectedAlert = "Недостатньо товару для покупки";

        assertThat(alertMessage)
                .as("It is not possible to buy more than one of the same flawed product.")
                .isEqualTo(expectedAlert);

        int productQuantity = cartProduct.getProductsQuantity();

        assertThat(productQuantity)
                .as("Quantity of same flawed product added to the cart can't exceed 1")
                .isEqualTo(1);
    }
}
