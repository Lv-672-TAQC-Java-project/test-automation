package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.ReviewSortingOption;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class ReviewsPage {

    public List<Review> getReviews() {
        String reviewPath = "//div[@class='comment']";
        int amountOfReviews = $$x(reviewPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(30))
                .size();

        return rangeClosed(1, amountOfReviews)
                .mapToObj(i -> new Review(String.format("(%s)[%s]", reviewPath, i)))
                .collect(toList());
    }

    public String getTitle() {

        return $x("//h2[@class='product-tabs__heading']")
                .shouldBe(visible)
                .text();
    }

    @Step("Choose {option} and get sorted reviews on the Reviews page")
    public ReviewsPage sortBy(ReviewSortingOption option) {
        String firstCommentTextPath = "(//div[@class='comment'])[1]/div[@class='comment__inner']//div/p";
        String lastCommentTextPath = "(//div[@class='comment'])[last()]/div[@class='comment__inner']//div/p";

        String firstCommentTextBeforeSorting = $x(firstCommentTextPath)
                .shouldBe(visible)
                .text();

        String lastCommentTextBeforeSorting = $x(lastCommentTextPath)
                .shouldBe(visible)
                .text();

        String sortOptionPath = String.format("//select/option[@value='%s']", option.getOptionName());
        $x(sortOptionPath).click();
        $x(sortOptionPath).shouldBe(selected);

        $x(firstCommentTextPath).shouldNotHave(text(firstCommentTextBeforeSorting), Duration.ofSeconds(20));
        $x(lastCommentTextPath).shouldNotHave(text(lastCommentTextBeforeSorting), Duration.ofSeconds(20));

        return this;
    }
}
