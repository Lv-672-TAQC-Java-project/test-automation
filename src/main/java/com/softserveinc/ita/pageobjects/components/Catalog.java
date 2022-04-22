package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.SubCategoryPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Catalog {
    @Step("Hovered '{categoryName}' Category and opened '{subCategoryName}' Sub Category")
    public SubCategoryPage openSubCategoryPage(String categoryName, String subCategoryName) {
        $x(String.format("//ul[@class='menu-categories ng-star-inserted']//*[text()[contains(.,'%s')]]",
                categoryName)).hover();
        $x(String.format("//a[@class='menu__link' and text()[contains(.,'%s')]]", subCategoryName)).click();

        return new SubCategoryPage();
    }
}
