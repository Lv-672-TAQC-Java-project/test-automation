package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsComparisonTest extends TestRunner {
    @Test
    public void verifyShowOnlyDifferencesFunctionality() {
        Header header = homePage.getHeader();
        String searchTerm = "notebook";

        SearchResultPage searchResultPage = header.search(searchTerm);

        Product firstProduct = searchResultPage.getProduct(1);
        Product secondProduct = searchResultPage.getProduct(2);

        firstProduct.addToListOfComparisons();
        secondProduct.addToListOfComparisons();

        String firstProductName = firstProduct.getName();
        String secondProductName = secondProduct.getName();
        
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
