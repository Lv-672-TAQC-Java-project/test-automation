package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class HomePage {

    private Header header = new Header();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }

    @Step("Opened product from Menu")
    public SubCatalog openMenu(String nameLinkMenu) {
        String linkText = String.format("//div[@class = 'fat-wrap']//a[text() = '%s']", nameLinkMenu);
        $x(linkText).click();

        return new SubCatalog();
    }

}
