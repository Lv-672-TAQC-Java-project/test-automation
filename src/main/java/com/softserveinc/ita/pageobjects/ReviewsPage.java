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
    public ReviewsPage sortBy(ReviewSortingOption option, List<Review> reviews) {
        String firstComment = reviews
                .stream()
                .findFirst()
                .get()
                .getComment();

        String lastComment = reviews
                .stream()
                .reduce((first, second) -> second)
                .get()
                .getComment();

        String sortOptionPath = String.format("//select/option[@value='%s']", option.getOptionName());
        $x(sortOptionPath).click();
        $x(sortOptionPath).shouldBe(selected);

        String firstCommentAfterSoring = reviews
                .stream()
                .findFirst()
                .get()
                .getComment();

        String lastCommentAfterSorting = reviews
                .stream()
                .reduce((first, second) -> second)
                .get()
                .getComment();

        int count = 0;
        while (count < 1) {
            if (!firstComment.equals(firstCommentAfterSoring) || !lastComment.equals(lastCommentAfterSorting)) {
                count++;
            }
        }

        return this;
    }
}
