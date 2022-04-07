package com.softserveinc.ita;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Product {

    private final String xPath;
    private String title;
    SelenideElement basketButton;

    public Product(String xPath) {
        this.xPath = xPath;
    }

    public String getTitle() {
        if (title == null) {
            title = $x(String.format("%s%s", xPath, "//span[@class='goods-tile__title']")).text();
        }
        return title;
    }

    public SearchResultPage addToCart() {
        if (basketButton == null) {
            basketButton = $x(String.format("%s%s", xPath, "//button[@aria-label='Купити']"));
        }
        basketButton.click();

        return new SearchResultPage();
    }
}
