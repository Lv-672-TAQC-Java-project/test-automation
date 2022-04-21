package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewsTest extends TestRunner {

    @Test
    public void verifySortingReviewsByMostHelpful() {
        String productName = "Ноутбук HP Pavilion Gaming 15-ec2013ua";
        Product product = homePage
                .getHeader()
                .search("Ноутбук HP")
                .getProduct(productName);

        ReviewsPage reviewsPage = product.openReviewsPage();

        String productTitleInReviews = reviewsPage.getTitle();

        assertThat(productTitleInReviews)
                .as("The title on the reviews page should contain " + productName)
                .contains(productName);

        List<Review> reviews = reviewsPage.getReviews();

        List<Integer> sortedReviewsRating = reviews.stream()
                .map(Review::getRating)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        reviewsPage.sortBy(SortingOption.HELPFUL);

        assertThat(reviewsPage.getReview(1).isRatingDisplayed())
                .as("First review should have a rating after sorting by 'Helpful'")
                .isTrue();
        assertThat(reviewsPage.getLastReview().isRatingDisplayed())
                .as("Last review should not have a rating after sorting by 'Helpful'")
                .isFalse();

        List<Integer> reviewsRatingByMostHelpful = reviews.stream()
                .map(Review::getRating)
                .collect(Collectors.toList());

        assertThat(sortedReviewsRating)
                .as("The sorted reviews should be equal to the sorted reviews by 'Most helpful'")
                .isEqualTo(reviewsRatingByMostHelpful);
    }
}
