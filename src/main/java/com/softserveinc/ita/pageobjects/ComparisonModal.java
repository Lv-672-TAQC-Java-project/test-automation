package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.product.Product;
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

    public ComparisonModalItem getItem(String name){
        String categoryLinkPath = format("//a[contains(text(), '%s')]//ancestor::*[contains(@class, 'comparison-modal__list')]", name);
        $x(categoryLinkPath).click();

        return new ComparisonModalItem(categoryLinkPath);
    }

    public List<ComparisonModalItem> getItems() {
        List<ComparisonModalItem> products = new LinkedList<>();
        String productsPath = "//*[contains(@class, 'comparison-modal__list')]//li";
        int amountOfProducts = $$x(productsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10)).size();

        for (int i = 1; i <= amountOfProducts; i++) {
            products.add(new ComparisonModalItem(format("(%s)[%s]", productsPath, i)));
        }

        return products;
    }
}
