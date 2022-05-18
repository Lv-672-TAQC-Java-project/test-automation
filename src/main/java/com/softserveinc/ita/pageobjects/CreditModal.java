package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class CreditModal {

    public int getAmountOfPayments() {

        return $$x("//option[@class='ng-star-inserted']")
                .shouldHave(sizeNotEqual(0), ofSeconds(60))
                .size();
    }

    public int getCreditPeriod(int periods) {

        $x(format("//option[@class='ng-star-inserted'][%s]", periods)).click();

        return Integer.parseInt($x(format("//option[@class='ng-star-inserted'][%s]", periods))
                .getText()
                .replaceAll("\\D+", ""));
    }

    public int getCreditPrice() {

        return Integer.parseInt($x("//span[@class='credit-variant__price']")
                .getText()
                .replaceAll("\\D+", ""));
    }

}
