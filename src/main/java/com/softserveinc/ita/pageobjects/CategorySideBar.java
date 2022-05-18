package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.CategoryName;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class CategorySideBar {

    @Step("Opened category page")
    public CategoryPage openCategoryPage(CategoryName categoryName) {
        String categoryPath = categoryName.getCategoryPath();
        String link = categoryPath.contains("https:") ?
                format("//div[@class = 'fat-wrap']//a[@href='%s']", categoryPath) :
                format("//div[@class = 'fat-wrap']//a[@href='https://rozetka.com.ua/ua/%s']", categoryPath);
        $x(link).click();

        return new CategoryPage();
    }
//    I've made this as a separate method and page class, because page with flawed products
//    is built slightly differently than any other page written in CategoryName enum
    @Step("Opened flawed products page")
    public FlawedProductsPage openFlawedProductsPage() {
        $x("//*[@class = 'menu-categories__link' and contains(@href, 'rasprodaja')]").click();

        return new FlawedProductsPage();
    }
}
