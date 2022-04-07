package com.softserveinc.ita;

import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();

    public ComparisonPage showOnlyDifferences() {
        String locator = "//*[contains (text(), ' Тільки відмінності')]";
        $x(locator).shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }

    public String getAllCharacteristicsList() {
        String characteristicsLocator = "//*[@class = 'characteristic-list']";
        return $x(characteristicsLocator).getText();
    }

    public String getOnlyDifferentCharacteristicsList() {
        String characteristicsLocator = "//*[@class = 'characteristic-list']";
        showOnlyDifferences();
        return $x(characteristicsLocator).getText();
    }
}
