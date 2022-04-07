package com.softserveinc.ita;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class ComparisonModal {

    public boolean isVisibleProduct() {
        sleep(3000);
        for (SelenideElement element : $$x("//a[@class='product__heading']"))
//    return element.should(Condition.visible, Duration.ofSeconds(3)).isDisplayed();
            return element.should(Condition.visible).isDisplayed();

        return false;
    }


    @Step("count products in comparison modal")
    public ElementsCollection countProduct(int amountProduct) {

        return $$x("//a[@class='product__heading']").shouldHave(size(amountProduct));
    }

    @Step("deleted products from comparison modal")
    public ComparisonModal deleteElement(int countElement) {
        sleep(2000);

//        for (int i = 0; i < countElement; i++){
////            sleep(2000);
//            $$x("//button[@class='button button--white button--small popup-menu__toggle popup-menu__toggle--context']").get(i).click();
//        $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']").click();
//        }

        for (int i = 1; i <= countElement; i++) {
            String linkText = String.format("//li[@class='products-grid__cell ng-star-inserted'][%s]" +
                    "//button[@class='button button--white button--small popup-menu__toggle popup-menu__toggle--context']", i);
            $x(linkText).click();
//            sleep(2000);
            $x("//button[@class='button button--medium button--with-icon button--link context-menu-actions__button']").click();
        }
        return this;
    }
}
