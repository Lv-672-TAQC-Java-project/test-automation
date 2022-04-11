package com.softserveinc.ita;

import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SearchResultPage {

    private Header header = new Header();

    //This method will be replaced
    @Step("Added product number {productIndex} to the comparison List")
    public SearchResultPage addProductToComparisonModal(int productIndex) {
        String productNumber = String.format("(//div[@class='goods-tile__inner'])[%s]", productIndex);
        String addToComparisonModalButton = "//*[@class ='compare-button ng-star-inserted']";
        String addProductToComparisonModal = productNumber + addToComparisonModalButton;
        $x(addProductToComparisonModal).click();
        return this;
    }

}
