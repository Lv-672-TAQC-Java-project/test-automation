package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;

@Getter
public class HomePage {

    private Header header = new Header();
    private SideBarCategory sidebarCategory = new SideBarCategory();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }
}
