package com.softserveinc.ita;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class ComparisonModal {

    @Step("choose {product} from comparison list")
    public ComparisonPage chooseProductFromComparisonList(String product) {

        String productLinkPath = String.format("//*[contains(@class, 'comparison-modal__list')]//a[contains(text(), '%s')]",product);
        $x(productLinkPath)
                .click();

        return new ComparisonPage();
    }
}
