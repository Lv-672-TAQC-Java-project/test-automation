package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CategorySideBar {

    @Step("Opened category page")
    public CategoryPage openCategoryPage(CategoryName categoryName) {
        if (categoryName
                .getCategoryPath()
                .contains("https:")) {
            $x(String.format("//div[@class = 'fat-wrap']//a[@href='%s']", categoryName.getCategoryPath()))
                    .click();
        } else {
            $x(String.format("//div[@class = 'fat-wrap']//a[@href='https://rozetka.com.ua/ua/%s']", categoryName.getCategoryPath()))
                    .click();
        }

        return new CategoryPage();
    }
}
