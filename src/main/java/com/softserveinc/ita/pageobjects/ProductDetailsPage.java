package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.pageobjects.models.ProductDetailsTabName;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class ProductDetailsPage {

    private final SelenideElement productNameLabel = $x("//div[@class='product__heading']/child::h1");

    public boolean isOpened() {
        return isDisplayed($x("//product-tab-main[@class='ng-star-inserted']"), ofSeconds(5));
    }

    public String getProductName() {
        return productNameLabel.getText();
    }

    @Step("Added product to cart")
    public Cart addToCart() {
        productNameLabel.scrollIntoView(true);
        $x("//button[contains(@class,'buy-button button button--with-icon')]").click();

        return new Cart();
    }

    @Step("Opened product reviews tab")
    public ReviewsTab openReviewsTab() {
        $x("//li/a[contains(@href,'comments')]").click();

        return new ReviewsTab();
    }

    @Step("Opened product characteristic tab")
    public CharacteristicTab openCharacteristicTab() {
        $x("//ul[@class='tabs__list']/li[2]").click();

        return new CharacteristicTab();
    }

    @Step("Opened product question tab")
    public QuestionTab openQuestionTab() {
        $x(format("//ul[@class='tabs__list']/li[4]")).click();

        return new QuestionTab();
    }

    public String getActiveFootSize() {
        return $x("//a[@class='var-options__block var-options__block_state_active ng-star-inserted']" +
                "/span").getText();
    }
}
