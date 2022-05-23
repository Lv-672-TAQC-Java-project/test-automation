package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.models.SortOrder;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Collections;

import static com.softserveinc.ita.pageobjects.models.QuestionSortingOption.*;
import static java.util.Collections.reverseOrder;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

public class SortTest extends TestRunner {
    @Description("Add test script to cover 'From cheap to expensive' sort functionality in Rozetka")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-15")
    @Test(description = "LVTAQC672-15")
    public void verifyThatProductsAreSortedFromCheapToExpensive() {
        var header = homePage.getHeader();
        var searchTerm = "Декоративна штукатурка RZTK";
        var searchResultPage = header.search(searchTerm);

        var softAssert = new SoftAssertions();
        softAssert.assertThat(searchResultPage.getSearchTermLabel())
                .as("Search result page should contain label with" + searchTerm)
                .contains(searchTerm);

        var products = searchResultPage.getProducts();
        var productPrices = searchResultPage.getProductsPrices(products);
        Collections.sort(productPrices);

        int productsAmount = searchResultPage.getFoundProductsAmountInCategory();
        searchResultPage.sort(SortOrder.FROM_CHEAP);

        softAssert.assertThat(searchResultPage.getFoundProductsAmountInCategory())
                .as("Amount of found products that are displayed under category label should not change after sort")
                .isEqualTo(productsAmount);

        softAssert.assertAll();

        var sortedProductsFromCheap = searchResultPage.getProducts();
        var sortedProductsFromCheapPrices = searchResultPage.getProductsPrices(sortedProductsFromCheap);

        assertThat(sortedProductsFromCheapPrices)
                .as("Prices of sorted products (from cheap to expensive) should increase")
                .isEqualTo(productPrices);
    }

    @Description("Add test script to cover sorting function in tab list 'Питання' by date in Rozetka")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-8")
    @Test(description = "LVTAQC672-8")
    public void verifySortingQuestionByDateInQuestionTab() {
        var questionsTab = homePage
                .getHeader()
                .search("iphone")
                .getProduct(1)
                .openDetailsPage()
                .openQuestionTab();

        var questionsDates = questionsTab.getQuestionsDates();

        var softAssert = new SoftAssertions();
        softAssert.assertThat(questionsDates)
                .as("should be greater three dates in the list")
                .hasSizeGreaterThan(3);

        softAssert.assertAll();

        questionsTab.sort(DATE);

        range(1, (questionsDates.size() - 1))
                .forEach(i -> assertThat(questionsDates.get(i))
                        .as("date should be sorted to fall down")
                        .isAfterOrEqualTo(questionsDates.get(i + 1)));

        assertThat(questionsDates)
                .as("date should be sorted to grow up")
                .isSortedAccordingTo(reverseOrder());
    }
}
