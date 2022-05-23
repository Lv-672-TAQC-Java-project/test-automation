package com.softserveinc.ita.pageobjects;

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

    @Step("Opened Comparison Page {category}")
    public ComparisonPage openComparisonPage(String category) {

        var categoryLinkPath = format("//*[contains(@class, 'comparison-modal__list')]//a[contains(text(), '%s')]", category);
        $x(categoryLinkPath).click();

        return new ComparisonPage();
    }

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
