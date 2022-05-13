package com.softserveinc.ita.pageobjects.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class TiresSubcategoryFilterSection {

    public boolean isOpened() {
        return isDisplayed($x("//div[@class='widget-auto ng-star-inserted']"), ofSeconds(5));
    }

    @Step("Set year option")
    public TiresSubcategoryFilterSection setYear(String option) {
        $x("(//div[@class='widget-auto__cell ng-star-inserted'])[1]").click();
        $x(format("//option[@class='ng-star-inserted' and @value='%s']", option)).click();

        return this;
    }

    @Step("Set brand option")
    public TiresSubcategoryFilterSection setBrand(String option) {
        $x("(//div[@class='widget-auto__cell ng-star-inserted'])[2]").click();
        $x(format("//option[@class='ng-star-inserted' and @value='%s']", option)).click();

        return this;
    }

    @Step("Set model option")
    public TiresSubcategoryFilterSection setModel(String option) {
        $x("(//div[@class='widget-auto__cell ng-star-inserted'])[3]").click();
        $x(format("//option[@class='ng-star-inserted' and @value='%s']", option)).click();

        return this;
    }

    @Step("Set car configuration option")
    public TiresSubcategoryFilterSection setCarConfiguration(String option) {
        $x("(//div[@class='widget-auto__cell ng-star-inserted'])[4]").click();
        $x(format("//option[@class='ng-star-inserted' and @value='%s']", option)).click();

        return this;
    }

    @Step("Selected tire size")
    public TiresSubcategoryFilterSection selectTireSize(String tireSize) {
        $x(format("//span[contains(text(),'%s')]", tireSize)).click();

        return this;
    }

    @Step("Filtered products")
    public TiresSubcategoryFilterSection filter() {
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
