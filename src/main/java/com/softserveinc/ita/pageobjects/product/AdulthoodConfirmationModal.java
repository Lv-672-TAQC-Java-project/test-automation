package com.softserveinc.ita.pageobjects.product;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.AdulthoodConfirmation;

import static com.codeborne.selenide.Selenide.$x;

public class AdulthoodConfirmationModal {

    public SearchResultPage confirmAdulthood(AdulthoodConfirmation confirmation) {
        SelenideElement confirmationButton = $x(confirmation.getConfirmationButtonPath());

        confirmationButton.click();
        confirmationButton.shouldNot(Condition.exist);

        return new SearchResultPage();
    }
}