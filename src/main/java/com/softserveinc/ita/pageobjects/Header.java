package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.Keys.ENTER;

public class Header {
    @Step("Searched for {term}")
    public SearchResultPage search(String term) {
        String inputFieldPath = "//*[@name='search']";
        $x(inputFieldPath).sendKeys(term);
        $x(inputFieldPath).sendKeys(ENTER);

        return new SearchResultPage();
    }

    @Step("Opened catalog")
    public Catalog openCatalog() {
        $x("//*[@id='fat-menu']").click();

        return new Catalog();
    }

    @Step("Opened comparison modal")
    public ComparisonModal openComparisonModal() {
        $x("//rz-comparison/button").click();
        $x("//a[@class = 'comparison-modal__link']").click();

        return new ComparisonModal();
    }

    @Step("Opened cart")
    public Cart openCart() {
        $x("//rz-cart/button").click();

        return new Cart();
    }


}
