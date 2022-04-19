package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CategorySideBar {

    @Step("Opened category page")
    public CategoryPage openCategoryPage(String nameCategory) {
        String linkText = String.format("//div[@class = 'fat-wrap']//a[text() = '%s']", nameCategory);
        $x(linkText).click();

        return new CategoryPage();
    }
}
