package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.Header;
import com.softserveinc.ita.pageobjects.SearchResultPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsComparisonTest extends TestRunner {
    @Test
    public void verifyShowOnlyDifferencesFunctionality() {
        Header header = homePage.getHeader();
        String searchTerm = "notebook";

        SearchResultPage searchResultPage =
                header
                        .search(searchTerm)
                        .getProduct(1)
                        .addToListOfComparisons()
                        .getProduct(2)
                        .addToListOfComparisons();

        String firstProductName = searchResultPage
                .getProduct(1)
                .getName();
        String secondProductName = searchResultPage
                .getProduct(2)
                .getName();

        String productCategory = "Ноутбуки";
        ComparisonPage comparisonPage =
                header
                        .openComparisonModal()
                        .openComparisonPage(productCategory);

        List<String> firstProductBeforeChangeCharacteristicList = comparisonPage
                .getProduct(firstProductName)
                .getCharacteristics();
        List<String> secondProductBeforeChangeCharacteristicList = comparisonPage
                .getProduct(secondProductName)
                .getCharacteristics();

        comparisonPage.showOnlyDifferences();

        List<String> firstProductAfterChangeCharacteristicList = comparisonPage
                .getProduct(firstProductName)
                .getCharacteristics();
        List<String> secondProductAfterChangeCharacteristicList = comparisonPage
                .getProduct(secondProductName)
                .getCharacteristics();

        assertThat(firstProductAfterChangeCharacteristicList)
                .as("After change products characteristics lists should display only distinctive characteristics")
                .doesNotContainSequence(secondProductAfterChangeCharacteristicList);

        assertThat(firstProductBeforeChangeCharacteristicList)
                .as("Before change characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(firstProductAfterChangeCharacteristicList
                        .size());

        assertThat(secondProductBeforeChangeCharacteristicList)
                .as("full characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(secondProductAfterChangeCharacteristicList
                        .size());
    }
}
