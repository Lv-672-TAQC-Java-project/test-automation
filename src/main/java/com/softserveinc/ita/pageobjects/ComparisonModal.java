package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ComparisonModal {

    @Step("Opened Comparison Page {category}")
    public ComparisonPage openComparisonPage(String category) {

        String categoryLinkPath = format("//*[contains(@class, 'comparison-modal__list')]//a[contains(text(), '%s')]", category);
        $x(categoryLinkPath).click();

        return new ComparisonPage();
    }

    public ItemComparisonModal getItem(String name) {
        String itemsPath = format("//a[contains(text(), '%s')]//ancestor::*[contains(@class, 'comparison-modal__list')]", name);
        $x(itemsPath).click();

        return new ItemComparisonModal(itemsPath);
    }

    public ItemComparisonModal getItem(int index) {
        String itemsPath = format("//*[contains(@class, 'comparison-modal__list')]//li[%s]", index);
        $x(itemsPath).click();

        return new ItemComparisonModal(itemsPath);
    }

    public List<ItemComparisonModal> getItems() {
        List<ItemComparisonModal> items = new LinkedList<>();
        String itemsPath = "//*[contains(@class, 'comparison-modal__list')]//li";
        int itemsCount = $$x(itemsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10)).size();

        for (int i = 1; i <= itemsCount; i++) {
            items.add(new ItemComparisonModal(format("(%s)[%s]", itemsPath, i)));
        }

        return items;
    }

    @Step("Closed comparison modal")
    public HomePage close() {
        $x("//button[@class = 'modal__close']").click();

        return new HomePage();
    }
}
