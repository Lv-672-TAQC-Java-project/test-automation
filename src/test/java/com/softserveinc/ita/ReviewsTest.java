package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.components.Review;
import com.softserveinc.ita.pageobjects.models.ReviewSortingOption;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.stream.Collectors;

import static com.softserveinc.ita.pageobjects.models.CategoryName.LAPTOPS_AND_COMPUTERS;
import static com.softserveinc.ita.pageobjects.models.FilterSectionName.SELLER;
import static com.softserveinc.ita.pageobjects.models.RatingNumber.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Description("Add a test script to cover filter function for reviews by rating in common rating section")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-37")
    @Test(description = "LVTAQC672-37")
    public void verifyFilterFunctionForReviewsByRating() {
        var product = homePage
                .getHeader()
                .openCatalog()
                .openSubCategoryPage(LAPTOPS_AND_COMPUTERS, "Монітори")
                .getProduct(1);
        var productName = product.getName();

        var reviewsTab = product.openReviewsTab();
        var productTitleInReviews = reviewsTab.getTitle();

        assertThat(productTitleInReviews)
                .as("The title on the reviews page should contain " + productName)
                .containsIgnoringCase(productName);

        var reviewsFilteringModal = reviewsTab.openReviewsFilteringModal();

        var filteredReviews = reviewsFilteringModal
                .filterReviews(FOUR)
                .openReviewsTab()
                .getReviews();

        int expectedRatingNumber = 4;
        filteredReviews
                .forEach(review -> AssertionsForClassTypes.assertThat(review.getRating())
                        .as("Product name should be " + expectedRatingNumber)
                        .isEqualTo(expectedRatingNumber));
    }
}
