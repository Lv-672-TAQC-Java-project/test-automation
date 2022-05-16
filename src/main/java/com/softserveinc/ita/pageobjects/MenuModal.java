package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MenuModal {

    @Step("Opened comparison modal")
    public ComparisonModal openComparisonModal() {
        $x("//ul[contains(@class, 'side-menu__list--top')]//li[3]").click();

        return new ComparisonModal();
    }

    public HomePage close(){
        $x("//button[contains(@class, 'side-menu__close')]").click();

        return new HomePage();
    }
}
