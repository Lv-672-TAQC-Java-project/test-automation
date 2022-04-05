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

    @Step("Go to catalog")
    public CatalogComponent goToCatalog() {
        $x("//*[@id='fat-menu']").click();

        return new CatalogComponent();
    }

    @Step("Go to comparison")
    public ComparisonComponent goToComparison() {
        $x("//rz-comparison/button").click();

        return new ComparisonComponent();
    }

    @Step("Go to cart")
    public CartComponent goToCart() {
        $x("//rz-cart/button").click();

        return new CartComponent();
    }
}
