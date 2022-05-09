package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.pageobjects.models.AdulthoodConfirmation;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.lang.String.*;
import static java.time.Duration.ofSeconds;

public class CategoryPage {

    @Step("Opened sub category page")
    public SubCategoryPage openSubCategoryPage(String subCategoryName) {
        $x(format("//div[@class='tile-cats']//a[contains(text(),'%s')]", subCategoryName)).click();

        return new SubCategoryPage();
    }

    public CategoryPage confirmAdulthood(AdulthoodConfirmation confirmation) {
        SelenideElement confirmationButton = $x(confirmation.toString());

        //Sometimes adulthood confirmation modal doesn't appear on this page
        if (isDisplayed(confirmationButton, ofSeconds(2))) {
            confirmationButton.click();
            confirmationButton.shouldNot(Condition.exist);
        } else {
            return this;
        }

        return this;
    }
}
