package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class SideBarCategory {

    @Step("Opened product from SideBarCategory")
    public Category openCategory(String nameCategory) {
        String linkText = String.format("//div[@class = 'fat-wrap']//a[text() = '%s']", nameCategory);
        $x(linkText).click();

        return new Category();
    }
}