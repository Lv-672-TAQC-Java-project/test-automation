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

        String firstComment = reviewsPage
                .getReview(1)
                .getComment();

        String lastComment = reviewsPage
                .getLastReview()
                .getComment();

        List<Integer> sortedReviewsRating = reviews
                .stream()
                .map(Review::getRating)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        reviewsPage.sortBy(SortingOption.HELPFUL);

        sortingWait(firstComment, lastComment, reviewsPage);

        List<Integer> reviewsRatingByMostHelpful = reviews
                .stream()
                .map(Review::getRating)
                .collect(Collectors.toList());

        assertThat(sortedReviewsRating)
                .as("The sorted reviews should be equal to the sorted reviews by 'Most helpful'")
                .isEqualTo(reviewsRatingByMostHelpful);
    }

    public static void sortingWait(String firstComment, String lastComment, ReviewsPage reviewsPage) {

        int count = 0;

        while (count < 1) {
            if (!firstComment.equals(reviewsPage.getReview(1).getComment()) || !lastComment.equals(reviewsPage.getLastReview().getComment())) {
                count++;
            }
        }
    }
}
