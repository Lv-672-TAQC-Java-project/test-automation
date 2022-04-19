package com.softserveinc.ita.deprecated.irybak;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.ComparisonPageProduct;
import com.softserveinc.ita.pageobjects.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddFunctionInComparisonTest extends TestRunner {
    @Test
    public void verifyThatProductsInComparisonListAreDisplayed() {
        String searchFirstTerm = "Lenovo";
        String searchSecondTerm = "Планшет";

        List<ComparisonPageProduct> productList = homePage
                .getHeader()
                .search(searchFirstTerm)
                .getFilter()
                .filterByCategoryLink("Комп'ютери та ноутбуки", "Планшети")
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons()
                .getHeader()
                .openComparisonModal()
                .openComparisonPage(searchSecondTerm)
                .getAllComparisonPageProducts();

        assertThat(productList.size())
                .as("The 2 products should be in comparison")
                .isEqualTo(2);

        productList.forEach(product -> assertThat(product.getProductName())
                .as("All products should contain " + searchFirstTerm)
                .containsIgnoringCase(searchFirstTerm));


        productList = new ComparisonPage()
                .addMoreModels()
                .getProduct(3)
                .addToListOfComparisons()
                .getHeader()
                .openComparisonModal()
                .openComparisonPage(searchSecondTerm)
                .getAllComparisonPageProducts();

        assertThat(productList.size())
                .as("The 3 products should be in comparison")
                .isEqualTo(3);

        productList.forEach(product -> assertThat(product.getProductName())
                .as("All products should contain " + searchSecondTerm)
                .containsIgnoringCase(searchSecondTerm));

    }
}
