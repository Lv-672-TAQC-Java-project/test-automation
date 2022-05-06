package com.softserveinc.ita.pageobjects.product;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.pageobjects.ProductDetailsPage;
import com.softserveinc.ita.pageobjects.ReviewsPage;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.models.ProductAvailability;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class Product {

    private final String rootElementPath;

    public String getName() {

        return $x(String.format("%s%s", rootElementPath,
                "//span[@class = 'goods-tile__title']")).text();
    }

    public ProductAvailability getAvailability() {
        String classAttribute = $x(String.format("%s//div[contains(@class, 'goods-tile__availability')]",
                rootElementPath)).getAttribute("class");
        String productAvailability = classAttribute
                .substring(classAttribute.indexOf("--") + 2, classAttribute.lastIndexOf(" "))
                .toUpperCase();
        return ProductAvailability.valueOf(productAvailability);
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
        $x(String.format("%s%s", rootElementPath, "//descendant::span[@class='goods-tile__title']")).click();

        return new ProductDetailsPage();
    }

    @Step("Opened product reviews page")
    public ReviewsPage openReviewsPage() {
        $x(String.format("%s//span[@class='goods-tile__reviews-link']", rootElementPath)).click();

        return new ReviewsPage();
    }
}
