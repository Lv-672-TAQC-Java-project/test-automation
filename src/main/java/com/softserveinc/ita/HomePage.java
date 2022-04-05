package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class HomePage extends HeaderPage {
    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }
}
