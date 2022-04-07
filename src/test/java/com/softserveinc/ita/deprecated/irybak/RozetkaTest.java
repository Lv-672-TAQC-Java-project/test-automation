package com.softserveinc.ita.deprecated.irybak;

import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RozetkaTest extends TestRunner {
    @Test
    public void verifyThatProductsInComparisonListAreDisplayed() {
        boolean areProductsDisplayed = homePage
                .getHeader()
                .search("Lenovo")
                .getFilter()
                .chooseOneOfCategory("Планшети")
                .getProduct(1)
                .addProductToComparisonList()
                .getProduct(2)
                .addProductToComparisonList()
                .getHeader()
                .openComparisonModal()
                .chooseProductFromComparisonList("Планшети")
                .addMoreProduct()
                .getProduct(1)
                .addProductToComparisonList()
                .getHeader()
                .openComparisonModal()
                .chooseProductFromComparisonList("Планшети")
                .areProductsDisplayed();

        assertThat(areProductsDisplayed)
                .as("The products should be displayed in comparison")
                .isTrue();

    }
}
