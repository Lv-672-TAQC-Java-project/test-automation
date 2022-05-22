package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.CreditTypeNumber;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class CreditModal {

    public int getAmountCreditsTypes() {

        return $$x("//option[@class='ng-star-inserted']")
                .shouldHave(sizeNotEqual(0), ofSeconds(60))
                .size();
    }

    public int getCreditPaymentsAmount(CreditTypeNumber creditTypeNumber) {
        var selectPeriod = format("//option[@class='ng-star-inserted'][%s]", creditTypeNumber.getCreditTypeNumber());
        $x(selectPeriod).click();

        return Integer.parseInt($x(selectPeriod)
                .getText()
                .replaceAll("\\D+", ""));
    }

    public int getCreditPrice() {

        return Integer.parseInt($x("//span[@class='credit-variant__price']")
                .getText()
                .replaceAll("\\D+", ""));
    }

}
