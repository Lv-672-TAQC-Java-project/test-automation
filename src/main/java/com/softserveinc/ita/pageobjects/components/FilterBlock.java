package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.models.FilterBlockDropdown;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.lang.String.format;
import static com.codeborne.selenide.Condition.*;
import static java.time.Duration.ofSeconds;

public class FilterBlock {

    public boolean isOpened() {
        return isDisplayed($x("//div[@class='widget-auto ng-star-inserted']"), ofSeconds(5));
    }

    @Step("Set dropdown option")
    public FilterBlock setDropdownOption(FilterBlockDropdown filterBlockDropdown, String option) {
        $x(format("(//div[@class='widget-auto__cell ng-star-inserted'])[%d]", filterBlockDropdown.getDropdownNumber())).click();
        $x(format("//option[@class='ng-star-inserted' and @value='%s']", option)).click();

        return this;
    }

    public FilterBlock selectTireSize(String tireSize) {
        $x(format("//span[contains(text(),'%s')]", tireSize)).click();

        return this;
    }

    public FilterBlock submitFilterParameters() {
        $x("//button[contains(@class,'navy widget-auto__submit')]")
                .shouldBe(visible, enabled)
                .click();

        var spinner = $x("//main[@class='ng-star-inserted preloader_type_element']");
        if (isDisplayed(spinner, ofSeconds(5))) {
            spinner.shouldNotBe(visible);
        }

        return this;
    }
}
