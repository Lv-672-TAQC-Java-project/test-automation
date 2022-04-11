package com.softserveinc.ita.deprecated.irybak;

import com.softserveinc.ita.ComparisonPage;
import com.softserveinc.ita.TestRunner;
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
                .addProductToComparisonList()
                .getProduct(2)
                .addProductToComparisonList()
                .getHeader()
                .openComparisonModal()
                .chooseProductFromComparisonList(searchTerm);

        assertThat(productList.getSize())
                .as("The 2 products should be in comparison")
                .isEqualTo(2);
        assertThat(productList.areProductsDisplayed())
                .as("The products should be displayed in comparison")
                .isTrue();

        productList
                .addMoreProduct()
                .getProduct(1)
                .addProductToComparisonList()
                .getHeader()
                .openComparisonModal()
                .chooseProductFromComparisonList(searchTerm)
                .areProductsDisplayed();

        assertThat(productList.getSize())
                .as("The 3 products should be in comparison")
                .isEqualTo(3);
        assertThat(productList.areProductsDisplayed())
                .as("The products should be displayed in comparison")
                .isTrue();
    }
}
