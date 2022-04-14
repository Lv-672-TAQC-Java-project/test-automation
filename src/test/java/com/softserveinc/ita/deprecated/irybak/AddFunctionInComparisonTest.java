package com.softserveinc.ita.deprecated.irybak;


import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddFunctionInComparisonTest extends TestRunner {
    @Test
    public void verifyThatProductsInComparisonListAreDisplayed() {

        String searchTerm = "Планшети";

        ComparisonPage productList = homePage
                .getHeader()
                .search("Lenovo")
                .getFilter()
                .chooseOneOfCategory(searchTerm)
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons()
                .getHeader()
                .openComparisonModal()
                .openComparisonPage(searchTerm);

        assertThat(productList.getSize())
                .as("The 2 products should be in comparison")
                .isEqualTo(2);
        assertThat(productList.areProductsDisplayed())
                .as("The products should be displayed in comparison")
                .isTrue();

        productList
                .addMoreProduct()
                .getProduct(1)
                .addToListOfComparisons()
                .getHeader()
                .openComparisonModal()
                .openComparisonPage(searchTerm)
                .areProductsDisplayed();

        assertThat(productList.getSize())
                .as("The 3 products should be in comparison")
                .isEqualTo(3);
        assertThat(productList.areProductsDisplayed())
                .as("The products should be displayed in comparison")
                .isTrue();
    }
}
