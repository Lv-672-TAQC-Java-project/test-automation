package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.pageobjects.components.Header;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class HomePage {

    private final Header header = new Header();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }
}
