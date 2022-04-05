package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class HeaderPage {
    @Step("Searched for {term}")
    public SearchResultPage search(String term) {
        String inputFieldPath = "//*[@name='search']";
        $x(inputFieldPath).sendKeys(term);
        $x(inputFieldPath).sendKeys(ENTER);

        return new SearchResultPage();
    }

    @Step("Open catalog")
    public Catalog openCatalog() {
        $x("//*[@id='fat-menu']").click();

        return new Catalog();
    }

    @Step("Open comparison")
    public Comparison openComparison() {
        $x("//rz-comparison/button").click();

        return new Comparison();
    }

    @Step("Open cart")
    public Cart openCart() {
        $x("//rz-cart/button").click();

        return new Cart();
    }
}
