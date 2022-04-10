package com.softserveinc.ita;

import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();

    public ComparisonPage showOnlyDifferences() {
        String locator = "//*[@class = 'comparison-settings'] //*[@type = 'button'][1]";
        $x(locator).click();
        return this;
    }

    public String getAllCharacteristicsText() {
        String characteristicsLocator = "//*[@class = 'characteristic-list']";
        return $x(characteristicsLocator).text();
    }
}
