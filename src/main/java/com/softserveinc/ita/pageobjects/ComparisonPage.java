package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();

    @Step("amount added of products to the comparison")
    public int getAmountAddedProducts() {

        return $$x("//a[@class='product__heading']")
                .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(3)).size();
    }

    @Step("if heading product had Text")
    public boolean isHeadingProductContainText(String heading) {
        for (int i = 1; i <= getAmountAddedProducts(); i++)
            return $x(String.format("(//a[@class='product__heading'])[%d]", i)).text().contains(heading);

        return false;
    }

    @Step("amount deleted products from comparison")
    public ComparisonPage deleteAmountAddedProducts(int amount) {
        for (int i = 1; i <= amount; i++) {
            $x(String.format("//li[@class='products-grid__cell ng-star-inserted'][%s]", i)
                    + "//button[@class='button button--white button--small popup-menu__toggle popup-menu__toggle--context']")
                    .click();
            $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']")
                    .click();
        }

        return this;
    }
}
