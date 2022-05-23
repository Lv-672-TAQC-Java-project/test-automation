package com.softserveinc.ita.pageobjects.product;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.pageobjects.ProductDetailsPage;
import com.softserveinc.ita.pageobjects.ReviewsTab;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.ProductState;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.WebElementUtil.isDisplayed;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class Product {

    private final String rootElementPath;

    public String getName() {

        return $x(rootElementPath + "//span[@class = 'goods-tile__title']").text();
    }

    public ProductState getState() {
        var classAttribute = $x(String.format("%s//ancestor::div[contains(@class, 'goods-tile') and @data-tile = 'small']",
                rootElementPath)).getAttribute("class");

        return classAttribute.contains("state") ? ProductState.UNAVAILABLE : ProductState.AVAILABLE;
    }

    public int getPrice() {
        String price = $x(String.format("%s%s", rootElementPath, "//span[@class = 'goods-tile__price-value']"))
                .text()
                .replace(" ", "");

        return Integer.parseInt(price);
    }

    @Step("Added product to cart")
    public SearchResultPage addToCart() {
        $x(String.format("%s//button[contains(@class, 'goods-tile__buy-button')]", rootElementPath))
                .scrollIntoView(false)
                .click();

        return new SearchResultPage();
    }

    @Step("Added product to list of comparisons")
    public SearchResultPage addToListOfComparisons() {
        $x(rootElementPath + "//*[@href = '#icon-compare']").shouldBe(Condition.visible);

        //used productName because getProductNotAddedToComparison method doesn't find xpath 'button[contains(@class, 'active')]'
        //declared here because rootElementPath can change after click
        var productName = $x(rootElementPath + "//a[contains(@class,'goods-tile__h')]").getAttribute("title");

        $x(rootElementPath + "//button[contains(@class, 'compare-button')]").click();

        $x(String.format("//a[contains(@title,'%s')]/parent::div" +
                "//button[contains(@class, 'active')]", productName)).shouldBe(Condition.visible, Duration.ofSeconds(10));

        return new SearchResultPage();
    }

    @Step("Opened product details page")
    public ProductDetailsPage openDetailsPage() {
        $x(rootElementPath + "//descendant::span[@class='goods-tile__title']")
                .scrollIntoView(false)
                .click();
        $x("//div[@class = 'product__heading']").shouldBe(visible, ofSeconds(30));

        return new ProductDetailsPage();
    }

    @Step("Opened product reviews tab")
    public ReviewsTab openReviewsTab() {
        $x(String.format("%s//span[@class='goods-tile__reviews-link']", rootElementPath)).click();

        return new ReviewsTab();
    }

    public boolean isDefectDescriptionVisible() {
        $x(rootElementPath)
                .shouldBe(visible, ofSeconds(10))
                .hover();
        var flawDescription = rootElementPath + "//*[@class = 'goods-tile__hidden-content ng-star-inserted']";
        $x(flawDescription).hover();

        return isDisplayed($x(flawDescription), ofSeconds(10));

    }
}
