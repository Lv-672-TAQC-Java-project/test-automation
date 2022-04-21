package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ReviewsPage {

    public List<Review> getReviews() {
        List<Review> reviews = new LinkedList<>();
        String reviewPath = "//div[@class='comment']";
        int amountOfReviews = $$x(reviewPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(30)).size();

        for (int i = 1; i <= amountOfReviews; i++) {
            reviews.add(new Review(String.format("(%s)[%s]", reviewPath, i)));
        }

        return reviews;
    }

    public String getTitle() {

        return $x("//h2[@class='product-tabs__heading']").text();
    }

    public Review getReview(int index) {

        return new Review(String.format("(//div[@class='comment'])[%s]", index));
    }

    public Review getLastReview() {

        return new Review(String.format("(//div[@class='comment'])[last()]"));
    }

    @Step("Choose {option} and get sorted reviews on the Reviews page")
    public ReviewsPage sortBy(SortingOption option) {
        String sortOptionPath = String.format("//select/option[@value='%s']", option.getOptionName());
        $x(sortOptionPath).click();
        $x(sortOptionPath).shouldBe(selected);

        return this;
    }
}
