package com.softserveinc.ita;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MenuCategories {

    @Step("Opened link in goods")
    public GoodsCell openLinkInGoodsCell(String linkGoods){
        sleep(5000);

        String linkText = String.format(
                "//div[@class='wrapper central-wrapper js-wrapper ng-star-inserted']" +
                        "//li[@class='portal-grid__cell ng-star-inserted']" +
                        "//a[contains(text(),'%s')]", linkGoods);
        $x(linkText).click();

        return new GoodsCell();
    }
}
