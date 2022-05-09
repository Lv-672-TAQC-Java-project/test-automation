package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SubCategoryPage extends SearchResultPage {

    public String getTitle() {

        return $x("//h1[@class='catalog-heading ng-star-inserted']")
                .shouldBe(visible)
                .text();
    }
}
