package com.softserveinc.ita;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonPage {
    private Header header = new Header();

    public Header getHeader() {
        return header;
    }

    public SearchResultPage addMoreProduct() {
        $x("//a/span[@class = 'comparison-settings__label']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();

        return new SearchResultPage();
    }

    public boolean areProductsDisplayed() {
        boolean isProductDisplayed = false;
        String productsPath = "//rz-products-section//li";
        int size = $$x(productsPath).size();

        for (int index = 0; index < size; index++) {
            isProductDisplayed = $x(String.format("%s[%d]", productsPath, index + 1))
                    .shouldBe(Condition.visible, Duration.ofSeconds(10))
                    .isDisplayed();
        }
        return isProductDisplayed;
    }


}
