package com.softserveinc.ita.pageobjects.product;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AdulthoodConfirmationModal {

    @Step("Confirmed adulthood")
    public SearchResultPage confirmAdulthood() {
        var confirmationButton = $x("//div[contains(@class, 'rz-btn_blue')]");

        confirmationButton.click();
        confirmationButton.shouldNot(Condition.exist);

        return new SearchResultPage();
    }

    @Step("Declined adulthood")
    public SearchResultPage declineAdulthood() {
        var declineButton = $x("//div[contains(@class, 'rz-btn_gray')]");

        declineButton.click();
        declineButton.shouldNot(Condition.exist);

        return new SearchResultPage();
    }
}