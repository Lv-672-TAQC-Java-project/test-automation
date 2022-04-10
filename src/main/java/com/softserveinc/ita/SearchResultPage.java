package com.softserveinc.ita;

import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SearchResultPage {

    private Header header = new Header();
    
    public SearchResultPage addProductToComparisonModal(int productIndex) {
        String productNumber = String.format("(//div[@class='goods-tile__inner'])[%s]", productIndex);
        String addToComparisonModalButton = "//*[@class ='compare-button ng-star-inserted']";
        String addProductToComparisonModal = productNumber + addToComparisonModalButton;
        $x(addProductToComparisonModal).click();
        return this;
    }

}
