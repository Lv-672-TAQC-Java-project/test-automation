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
        String searchTerm = "notebook";
        String productCategory = "Ноутбуки";

        Header header = homePage.getHeader();

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

        ComparisonPage comparisonPage =
                header
                        .openComparisonModal()
                        .openComparisonPage(productCategory);

//        List<String> firstProductFullCharacteristicListSize = comparisonPage
//                .getProductCharacteristics(firstProductName);
//        List<String> secondProductFullCharacteristicListSize = comparisonPage
//                .getProductCharacteristics(secondProductName);

        List<String> firstProductFullCharacteristicList = comparisonPage
                .getProduct(firstProductName)
                .getCharacteristics();
        List<String> secondProductFullCharacteristicList = comparisonPage
                .getProduct(secondProductName)
                .getCharacteristics();

        assertThat(comparisonPage
                .showOnlyDifferences()
                .getProduct(firstProductName)
                .getCharacteristics())
                .as("products characteristics lists should display only distinctive characteristics")
                .doesNotContainSequence(comparisonPage
                        .getProduct(secondProductName)
                        .getCharacteristics());

        assertThat(firstProductFullCharacteristicList)
                .as("full characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(comparisonPage
                        .getProduct(firstProductName).getCharacteristics().size());

        assertThat(secondProductFullCharacteristicList)
                .as("full characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(comparisonPage
                        .getProductCharacteristics(secondProductName).size());
    }
}
