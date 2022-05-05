package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.CategoryPage;
import com.softserveinc.ita.pageobjects.SubCategoryPage;
import com.softserveinc.ita.pageobjects.models.CategoryName;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Catalog {
    @Step("Hovered Category and opened '{subCategoryName}' Sub Category")
    public SubCategoryPage openSubCategoryPage(CategoryName categoryName, String subCategoryName) {
        $x(String.format("//ul[@class='menu-categories ng-star-inserted']/li/a[contains(@href,'%s')]",
                categoryName.getCategoryPath())).hover();
        $x(String.format("//a[@class='menu__link' and text()[contains(.,'%s')]]", subCategoryName)).click();

        return new SubCategoryPage();
    }

    @Step("Opened {categoryName} Category page")
    public CategoryPage openCategoryPage(CategoryName categoryName) {
        $x(String.format("//ul[@class='menu-categories ng-star-inserted']/li/a[contains(@href,'%s')]",
                categoryName.getCategoryPath())).click();

        return new CategoryPage();
    }
}
