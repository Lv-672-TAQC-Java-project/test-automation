package com.softserveinc.ita;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class Filter {

    public SearchResultPage chooseOneOfCategory(String product) {
        String productLinkPath = String.format("//li[contains(@class, 'categories-filter__item')]//span[text() = '%s']", product);
        $x(productLinkPath)
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();

        return new SearchResultPage();
    }
}
