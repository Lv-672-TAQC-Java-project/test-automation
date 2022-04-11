package com.softserveinc.ita;

import io.qameta.allure.Step;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();

    @Step("Showed only different characteristics")
    public ComparisonPage showOnlyDifferences() {
        String showOnlyDifferencesLocator = "//*[@class = 'comparison-settings'] //*[@type = 'button'][1]";
        $x(showOnlyDifferencesLocator).click();
        return this;
    }

    @Step("Get list of currently displayed characteristics")
    public List<String> getAllCharacteristicsList() {
        String characteristicsListLocator = "//li[@class = 'ng-star-inserted']";
        return $$x(characteristicsListLocator).texts();
    }
}
