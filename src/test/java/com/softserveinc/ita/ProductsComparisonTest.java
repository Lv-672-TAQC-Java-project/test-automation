package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsComparisonTest extends TestRunner {
    @Test
    public void verifyShowOnlyDifferencesFunctionality() {
        Header header = new Header();
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

        List<String> firstProductCharacteristics = comparisonPage
                .getProduct(firstProductName)
                .getCharacteristics();
        List<String> secondProductCharacteristics = comparisonPage
                .getProduct(secondProductName)
                .getCharacteristics();

        comparisonPage.showOnlyDifferences();

        List<String> firstProductCharacteristicsAfterChange = comparisonPage
                .getProduct(firstProductName)
                .getCharacteristics();
        List<String> secondProductCharacteristicsAfterChange = comparisonPage
                .getProduct(secondProductName)
                .getCharacteristics();

        assertThat(firstProductCharacteristicsAfterChange)
                .as("After change products characteristics lists should display only distinctive characteristics")
                .doesNotContainSequence(secondProductCharacteristicsAfterChange);

        assertThat(firstProductCharacteristics)
                .as("Before change characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(firstProductCharacteristicsAfterChange
                        .size());

        assertThat(secondProductCharacteristics)
                .as("full characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(secondProductCharacteristicsAfterChange
                        .size());
    }
}
