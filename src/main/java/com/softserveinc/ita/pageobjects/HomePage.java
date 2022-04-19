package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class HomePage {

    private Header header = new Header();
    private CategorySideBar categorySideBar = new CategorySideBar();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }
}
