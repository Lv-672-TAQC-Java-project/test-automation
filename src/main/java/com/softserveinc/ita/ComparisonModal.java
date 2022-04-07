package com.softserveinc.ita;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    public ComparisonPage goToNotebooksComparisonPage() {
        String locator = "//a[@class = 'comparison-modal__link' and contains (text(), 'Ноутбуки')]";
        $x(locator).shouldBe(visible, Duration.ofSeconds(10)).click();
        return new ComparisonPage();
    }
}
