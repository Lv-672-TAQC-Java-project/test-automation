package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.QuestionTab;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.models.SortOrder;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.softserveinc.ita.pageobjects.models.QuestionSortingOption.*;
import static java.util.Comparator.*;
import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;


public class SortTest extends TestRunner {
    @Description("Add test script to cover 'From cheap to expensive' sort functionality in Rozetka")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-15")
    @Test(description = "LVTAQC672-15")
    public void verifyThatProductsAreSortedFromCheapToExpensive() {
        Header header = homePage.getHeader();
        String searchTerm = "Декоративна штукатурка RZTK";
        SearchResultPage searchResultPage = header.search(searchTerm);

        Assertions.assertThat(searchResultPage.getSearchTermLabel())
                .as("Search result page should contain label with" + searchTerm)
                .contains(searchTerm);

        List<Product> products = searchResultPage.getProducts();
        List<Integer> productPrices = searchResultPage.getProductsPrices(products);
        Collections.sort(productPrices);

        List<Product> sortedProductsFromCheap = searchResultPage
                .sort(SortOrder.FROM_CHEAP)
                .getProducts();
        List<Integer> sortedProductsFromCheapPrices = searchResultPage.getProductsPrices(sortedProductsFromCheap);

        assertThat(sortedProductsFromCheapPrices)
                .as("Prices of sorted products (from cheap to expensive) should increase")
                .isEqualTo(productPrices);
    }

    @Description("Add test script to cover sorting function in tab list 'Питання' by date in Rozetka")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-8")
    @Test(description = "LVTAQC672-8")
    public void verifySortingFunctionByDate() {
        QuestionTab questionsTab = homePage
                .getHeader()
                .search("iphone")
                .getProduct(1)
                .openDetailsPage()
                .openQuestionTab();
//                .sort(DATE);

        int amountOfDates = 3;

        var questionsDates = questionsTab.getUniqueQuestionsDates();

//        System.out.println(questionsDates);

        assertThat(questionsDates)
//                .as("should not contain duplicate")
//                .doesNotHaveDuplicates()
                .as("should be greater than " + amountOfDates)
                .hasSizeGreaterThan(amountOfDates);

        questionsTab.sort(DATE);

        assertThat(questionsDates).isSortedAccordingTo(reverseOrder());

//        range(1, amountOfDates).forEach(i -> assertThat(questionsDates.get(i))
//                .as("date should be sorted to fall down")
//                .isAfter(questionsDates.get(i + 1)));
    }
}
