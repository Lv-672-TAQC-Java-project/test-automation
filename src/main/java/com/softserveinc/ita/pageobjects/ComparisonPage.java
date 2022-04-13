package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ComparisonPage {

    private Header header = new Header();

    @Step("Showed only distinctive products characteristics")
    public ComparisonPage showOnlyDifferences() {
        String showOnlyDifferencesLocator = "//*[@class = 'comparison-settings'] //*[@type = 'button'][1]";
        $x(showOnlyDifferencesLocator).click();
        return this;
    }

    public List<String> getProductCharacteristics(String name) {
        String charsLocator = String.format("//*[@class = 'comparison-characteristic__label'" +
                "and contains(text(), '%s')]" +
                "/following-sibling::dd[1]", name);
        return $$x(charsLocator).shouldHave(sizeGreaterThan(0)).texts();
    }
}
