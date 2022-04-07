package com.softserveinc.ita;

import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SearchResultPage {

    private Header header = new Header();

    public SearchResultPage addNotebooksToComparisonModal() {
        String addToComparisonModalLocator = "//*[@class ='compare-button ng-star-inserted']";
        String firstNotebookLocator = "//*[@data-goods-id  = '334830985'] " + addToComparisonModalLocator;
        String secondNotebookLocator = "//*[@data-goods-id  = '332149516'] " + addToComparisonModalLocator;
        $x(firstNotebookLocator).shouldBe(visible, Duration.ofSeconds(10)).click();
        $x(secondNotebookLocator).shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }
}
