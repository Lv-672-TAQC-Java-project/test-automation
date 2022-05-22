package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.product.InOrderProduct;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class OrderPlacementPage extends BasePage {

    @Step("Submitted an order")
    public OrderPlacementPage submitOrder() {
        $x("//div[@class='checkout-total__buttons']").click();

        return this;
    }

    public boolean isOpened() {
        return isDisplayed($x("//div[@class='central-wrapper']/h1"), ofSeconds(5));
    }

    public boolean isFieldRequired(String fieldName) {
        return $x(format("//div[contains(@class, '%s')]", fieldName))
                .getAttribute("class")
                .contains("validation_type_error");
    }

    public List<InOrderProduct> getProducts() {
        var inOrderProductsPath = "//div[@class = 'checkout-product']";

        return rangeClosed(1, $$x(inOrderProductsPath)
                .shouldHave(sizeGreaterThanOrEqual(0), ofSeconds(20))
                .size())
                .mapToObj(i -> new InOrderProduct(format("(%s)[%s]", inOrderProductsPath, i)))
                .collect(toList());
    }
}