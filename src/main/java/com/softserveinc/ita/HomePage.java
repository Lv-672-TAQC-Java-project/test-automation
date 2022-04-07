package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private Header header = new Header();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }

    @Step("Opened product page from Menu")
    public MenuCategories openLinkInMenu(String linkMenu){
        String linkText = String.format("//ul[@class='menu-categories menu-categories_type_main']" +
                "/li[@class='menu-categories__item ng-star-inserted']/a[contains(text(),'%s')]", linkMenu);
        $x(linkText).click();

        return new MenuCategories();
    }

}
