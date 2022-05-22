package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.Keys.ENTER;

public class Header {

    private void commonSearch(String term) {
        String inputFieldPath = "//*[@name='search']";
        $x(inputFieldPath).sendKeys(term);
        $x(inputFieldPath).sendKeys(ENTER);
    }

    @Step("Searched for {term}")
    public SearchResultPage search(String term) {
        commonSearch(term);

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

        return new ComparisonModal();
    }

    @Step("Opened cart")
    public Cart openCart() {
        $x("//rz-cart/button").click();

        return new Cart();
    }

    @Step("Searched for {fullProductName}")
    public ProductDetailsPage exactSearch(String fullProductName) {
        commonSearch(fullProductName);

        return new ProductDetailsPage();
    }

    @Step("Opened home page")
    public HomePage openHomePage() {
        $x("//a[@class='header__logo']").click();
        $x("//h2[@class = 'main-goods__heading ng-star-inserted']").shouldBe(visible, ofSeconds(30));

        return new HomePage();
    }
}
