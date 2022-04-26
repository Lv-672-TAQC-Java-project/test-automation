package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.CategoryName;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CategorySideBar {

    @Step("Opened category page")
    public CategoryPage openCategoryPage(CategoryName categoryName) {
        String categoryPath = categoryName.getCategoryPath();
        String link = categoryPath.contains("https:") ?
                String.format("//div[@class = 'fat-wrap']//a[@href='%s']", categoryPath) :
                String.format("//div[@class = 'fat-wrap']//a[@href='https://rozetka.com.ua/ua/%s']", categoryPath);
        $x(link).click();

        return new CategoryPage();
    }
}
