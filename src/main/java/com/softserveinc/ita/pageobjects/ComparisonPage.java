package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();
    private String productsPath = "//rz-products-section//li";

    @Step("add more products to the comparison")
    public SearchResultPage addMoreProduct() {
        $x("//a/span[@class = 'comparison-settings__label']")
                .click();

        return new SearchResultPage();
    }

    public int getSize(){
        return $$x(productsPath).shouldHave(sizeGreaterThan(0), Duration.ofSeconds(3)).size();
    }

    public boolean areProductsDisplayed() {
        boolean isProductDisplayed = false;
        for (int index = 0; index < getSize(); index++) {
            isProductDisplayed = $x(String.format("%s[%d]", productsPath, index + 1))
                    .shouldBe(visible, Duration.ofSeconds(10))
                    .isDisplayed();
        }
        return isProductDisplayed;
    }

}
