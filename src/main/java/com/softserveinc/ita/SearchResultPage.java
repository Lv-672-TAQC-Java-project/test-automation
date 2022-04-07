package com.softserveinc.ita;

import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SearchResultPage {

    private Header header = new Header();

    public SearchResultPage addProductToComparisonModal(int index) {
        String productNumber = String.format("(//div[@class='goods-tile__inner'])[%s]", index);
        String addToComparisonModalButton = "//*[@class ='compare-button ng-star-inserted']";
        String addProductToComparisonModal = productNumber + addToComparisonModalButton;
        $x(addProductToComparisonModal).shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }
}
