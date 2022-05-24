package com.softserveinc.ita.pageobjects.product;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.pageobjects.AdditionalProductService;
import com.softserveinc.ita.pageobjects.modal.Cart;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class InCartProduct {

    private final String rootElementPath;

    public String getName() {

        return $x(String.format("%s%s", rootElementPath, "//div[@class='cart-product__main']/a")).text();
    }

    @Step("Removed product from the cart")
    public Cart remove() {
        $x(String.format("%s%s", rootElementPath, "//rz-popup-menu/button")).click();
        $x("//li[@class='popup-menu__item ng-star-inserted']//button").click();

        return new Cart();
    }

    @Step("Opened additional services")
    public Cart expandAdditionalServicesSection() {
        SelenideElement additionalServicesButton = $x(String.format("%s%s", rootElementPath,
                "//button[contains(@class, 'cart-services__toggle')]"));
        SelenideElement hiddenAdditionalServicesList = $x(String.format("%s%s", rootElementPath,
                "//ul[@Class='cart-services__list display-none']"));

        //If there is more than one product in the cart, the sections of additional services are hidden and need to be
        //expanded. And if there is one product in the cart, its section is not hidden and does not need to be expanded.
        if (hiddenAdditionalServicesList.exists()) {
            additionalServicesButton.click();
        }

        return new Cart();
    }

    public AdditionalProductService getAdditionalProductService(String productName, int index) {

        return new AdditionalProductService(productName, index);
    }

    @Step("Add one more product from the cart")
    public Cart addOneMoreProduct() {
        $x(String.format("%s//button[@data-testid='cart-counter-increment-button']", rootElementPath)).click();
        SelenideElement loadSpinner = $x("//div[@class='modal__content modal__content--locked']");
        Duration timeout = ofSeconds(10);

        //sometimes page opens instead of popup
        if (isDisplayed(loadSpinner, timeout)) {
            loadSpinner.shouldNotBe(visible, timeout);
        }

        return new Cart();
    }

    public String getAlertMessage() {

        return $x("//*[@role = 'alert']").text();
    }

    public int getProductsQuantity() {
        var quantityValue = $x("//*[@class = 'cart-counter']//child::input").getValue();
        int quantity = Integer.valueOf(quantityValue);

        return quantity;
    }

    public int getPrice() {
        var price = $x(rootElementPath + "//p[@data-testid='cost']")
                .text()
                .replace("₴", "")
                .replace(" ", "");

        return Integer.parseInt(price);
    }
}
