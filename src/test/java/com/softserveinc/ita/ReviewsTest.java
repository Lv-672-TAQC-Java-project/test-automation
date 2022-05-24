package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.components.Review;
import com.softserveinc.ita.pageobjects.models.FilterSectionName;
import com.softserveinc.ita.pageobjects.models.ReviewSortingOption;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.stream.Collectors;

import static com.softserveinc.ita.pageobjects.models.FilterSectionName.SELLER;

public class ReviewsTest extends TestRunner {

    @Description("Add a test script to cover the 'Найкорисніші' sorting function on the product reviews page")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-16")
    @Test(description = "LVTAQC672-16")
    public void verifySortingReviewsByMostHelpful() {
        var productName = "Ноутбук HP Pavilion Gaming 15-ec2013ua";
        var reviewsTab = homePage
                .getHeader()
                .search("Ноутбук HP Pavilion")
                .getFilter()
                .filterBySection(SELLER, "Rozetka")
                .getProduct(productName)
                .openReviewsTab();

        var productTitleInReviews = reviewsTab.getTitle();

        var softAssert = new SoftAssertions();
        softAssert.assertThat(productTitleInReviews)
                .as("The title on the reviews page should contain " + productName)
                .contains(productName);

        var reviews = reviewsTab.getReviews();

        var sortedReviewsRating = reviews
                .stream()
                .map(Review::getRating)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());

        reviewsTab.sortBy(ReviewSortingOption.HELPFUL);

        var reviewsRatingByMostHelpful = reviews
                .stream()
                .map(Review::getRating)
                .collect(Collectors.toList());

        softAssert.assertThat(sortedReviewsRating)
                .as("The sorted reviews should be equal to the sorted reviews by 'Most helpful'")
                .isEqualTo(reviewsRatingByMostHelpful);

        softAssert.assertAll();
    }
}
