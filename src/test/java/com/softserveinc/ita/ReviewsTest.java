package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewsTest extends TestRunner {

    @Test
    public void verifySortingReviewsByMostUseful() {
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

        List<Integer> actualReviewsRating = new ArrayList<>();
        for (Review review : reviews) {
            actualReviewsRating.add(review.getRating());
        }
        List<Integer> sortedReviewsRating = actualReviewsRating.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        productReviews.sortBy("Найкорисніші");

        List<Integer> reviewsRatingByMostUseful = new ArrayList<>();
        for (Review review : reviews) {
            reviewsRatingByMostUseful.add(review.getRating());
        }

        assertThat(sortedReviewsRating)
                .as("The sorted reviews should be equal to the sorted reviews by 'Most useful'")
                .isEqualTo(reviewsRatingByMostUseful);
    }
}
