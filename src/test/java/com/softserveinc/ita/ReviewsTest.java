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
        String productName = "Ноутбук Acer Nitro 5 AN517-54-58CY";
        Product product = homePage
                .getHeader()
                .search("Acer")
                .getProduct(productName);

        ReviewsPage productReviews = product.openReviewsPage();

        String productTitleInReviews = productReviews.getTitle();

        assertThat(productTitleInReviews)
                .as("Title in productReviews should contain product name")
                .contains(productName);

        List<Review> reviews = productReviews.getReviews();

        List<Integer> sortedReviewsRating = reviews.stream()
                .map(Review::getRating)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        productReviews.sortBy(ReviewSortingOption.HELPFUL);

        Review firstReview = productReviews.getReview(1);
        Review lastReview = productReviews.getLastReview();

        assertThat(firstReview.isRatingDisplayed())
                .as("First review should have rating after sorting by 'Helpful'")
                .isTrue();
        assertThat(lastReview.isRatingDisplayed())
                .as("Last review should not have a rating")
                .isFalse();

        List<Integer> reviewsRatingByMostHelpful = reviews.stream()
                .map(Review::getRating)
                .collect(Collectors.toList());

        assertThat(sortedReviewsRating)
                .as("The sorted reviews should be equal to the sorted reviews by 'Most helpful'")
                .isEqualTo(reviewsRatingByMostHelpful);
    }
}
