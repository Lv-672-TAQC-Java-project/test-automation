package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class HomePage {

    private HeaderPage headerPage;

    public HomePage() {
        headerPage = new HeaderPage();
    }

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }
}
