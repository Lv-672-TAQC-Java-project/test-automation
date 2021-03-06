package com.softserveinc.ita.pageobjects.modal;

import com.softserveinc.ita.pageobjects.BasePage;
import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.HomePage;
import com.softserveinc.ita.pageobjects.components.SubCategoryComparisonModal;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ComparisonModal extends BasePage {

    public SubCategoryComparisonModal getSubCategory(String subCategory) {
        var subCategoryPath = format("//a[contains(text(), '%s')]" +
                "//ancestor::*[contains(@class, 'comparison-modal__item')]", subCategory);

        return new SubCategoryComparisonModal(subCategoryPath);
    }

    public SubCategoryComparisonModal getSubCategory(int index) {
        var subCategoryPath = format("(//*[contains(@class, 'comparison-modal__item')])[%s]", index);

        return new SubCategoryComparisonModal(subCategoryPath);
    }

    public List<SubCategoryComparisonModal> getSubCategories() {
        var subCategories = new LinkedList<SubCategoryComparisonModal>();
        var subCategoriesPath = "//*[contains(@class, 'comparison-modal__list')]//li";
        int subCategoriesCount = $$x(subCategoriesPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10))
                .size();

        for (int i = 1; i <= subCategoriesCount; i++) {
            subCategories.add(new SubCategoryComparisonModal(format("%s[%s]", subCategoriesPath, i)));
        }

        return subCategories;
    }

    @Step("Closed comparison modal")
    public HomePage close() {
        $x("//button[@class = 'modal__close']").click();

        return new HomePage();
    }

    @Step("Removed all sub categories from comparison modal")
    public ComparisonModal removeAllSubCategories() {
        //loop to check if categories list is not empty
        while ($x("//rz-comparison-modal/*")
                .getAttribute("class")
                .contains("list")) {
            getSubCategory(1).remove();
        }

        return this;
    }
}
