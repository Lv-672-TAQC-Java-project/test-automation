package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Category {

    @Step("Opened sub category")
    public SearchResultPage openSubCategory(String nameSubCategory) {
        String linkText = String.format(
                "//div[@class='tile-cats']//a[contains(text(),'%s')]", nameSubCategory);
        $x(linkText).click();

        return new SearchResultPage();
    }
}