package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.*;
import io.qameta.allure.Step;
import org.checkerframework.checker.index.qual.PolyUpperBound;

import static com.codeborne.selenide.Selenide.$x;
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

    @Step("Opened menu modal")
    public MenuModal openMenuModal(){
        $x("//rz-mobile-user-menu//button").click();

        return new MenuModal();
    }
}
