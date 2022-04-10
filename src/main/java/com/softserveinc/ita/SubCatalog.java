package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SubCatalog {

    @Step("Opened sub menu")
    public SearchResultPage openSubMenu(String nameLinkProduct) {
        String linkText = String.format(
                "//div[@class='tile-cats']//a[contains(text(),'%s')]", nameLinkProduct);
        $x(linkText).click();

        return new SearchResultPage();
    }
}
