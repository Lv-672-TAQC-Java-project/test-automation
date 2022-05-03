package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.Cart;
import com.softserveinc.ita.pageobjects.ComparisonModal;
import com.softserveinc.ita.pageobjects.ProductDetailsPage;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import io.qameta.allure.Step;

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
}
