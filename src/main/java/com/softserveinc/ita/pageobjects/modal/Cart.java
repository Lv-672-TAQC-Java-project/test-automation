package com.softserveinc.ita.pageobjects.modal;

import com.softserveinc.ita.pageobjects.BasePage;
import com.softserveinc.ita.pageobjects.HomePage;
import com.softserveinc.ita.pageobjects.OrderPlacementPage;
import com.softserveinc.ita.pageobjects.product.InCartProduct;
import com.softserveinc.ita.pageobjects.product.RecommendedProduct;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class Cart extends BasePage {

    public InCartProduct getProduct(int index) {

        return new InCartProduct(format("(//div[@class='cart-product ng-star-inserted'])[%s]", index));
    }

    public InCartProduct getProduct(String name) {

        return new InCartProduct(format("//a[@title=%s]/ancestor::div[@class='cart-product ng-star-inserted']", "\"" + name + "\""));
    }

    public boolean isEmpty() {

        return isDisplayed($x("//div[@data-testid='empty-cart']/h4"), ofSeconds(5));
    }

    public int getTotalPrice() {
        $x("//div[@class='modal__content modal__content--locked']").shouldNotBe(visible);

        return Integer.parseInt($x("//div[@class='cart-receipt__sum-price']/span").text());
    }

    public boolean isOpened() {
        Duration time = ofSeconds(5);
        var isModalView = isDisplayed($x("//div[@class='modal__header']"), time);
        var isFullScreenView = isDisplayed($x("//h1[@class='cart-page__heading']"), time);

        return isModalView || isFullScreenView;
    }

    public List<InCartProduct> getInCartProducts() {
        var inCartProductsPath = "//div[@class='cart-product ng-star-inserted']";
        int amountOfInCartProducts = $$x(inCartProductsPath)
                .shouldHave(sizeNotEqual(0), ofSeconds(10))
                .size();

        return rangeClosed(1, amountOfInCartProducts)
                .mapToObj(i -> new InCartProduct(format("(%s)[%s]", inCartProductsPath, i)))
                .collect(toList());
    }

    public RecommendedProduct getRecommendedProduct(int recommendedProductNumber) {
        return new RecommendedProduct(recommendedProductNumber);
    }

    @Step("Closed cart")
    public HomePage close() {
        var closeButton = $x("//button[@class='modal__close']");
        //sometimes page opens instead of popup
        if (isDisplayed(closeButton, ofSeconds(5))) {
            closeButton.click();
        }

        return new HomePage();
    }

    @Step("Emptied cart")
    public Cart empty() {
        while (!isEmpty()) {
            getProduct(1).remove();
        }
        return this;
    }

    @Step("Submitted an order")
    public OrderPlacementPage submitOrder() {
        $x("//div[@class='cart-receipt ng-star-inserted']/a").click();
        $x("//legend[@class = 'checkout-block__title']").shouldBe(visible, ofSeconds(30));

        return new OrderPlacementPage();
    }
}
