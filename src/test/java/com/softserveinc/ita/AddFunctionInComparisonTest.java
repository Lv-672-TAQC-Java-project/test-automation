package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.ComparisonPageProduct;
import com.softserveinc.ita.pageobjects.Header;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddFunctionInComparisonTest extends TestRunner {
    @Test
    public void verifyThatProductsInComparisonListAreDisplayed() {
        String searchFirstTerm = "Lenovo";
        String searchSecondTerm = "Планшет";

        Header header = homePage.getHeader();

        ComparisonPage comparisonPage = header
                .search(searchFirstTerm)
                .getFilter()
                .filterByCategoryLink("Комп'ютери та ноутбуки", "Планшети")
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons()
                .getHeader()
                .openComparisonModal()
                .openComparisonPage(searchSecondTerm);

        List<ComparisonPageProduct> productList = comparisonPage.getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 2 products should be in comparison")
                .hasSize(2);

        productList.forEach(product -> assertThat(product.getProductName())
                .as("All products should contain " + searchFirstTerm)
                .containsIgnoringCase(searchFirstTerm)
                .as("All products should contain " + searchSecondTerm)
                .containsIgnoringCase(searchSecondTerm));

        productList = comparisonPage
                .addMoreModels()
                .getProduct(3)
                .addToListOfComparisons()
                .getHeader()
                .openComparisonModal()
                .openComparisonPage(searchSecondTerm)
                .getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 3 products should be in comparison")
                .hasSize(3);

        productList.forEach(product -> assertThat(product.getProductName())
                .as("All products should contain " + searchSecondTerm)
                .containsIgnoringCase(searchSecondTerm));
    }
}
