package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.pageobjects.ComparisonPage;
import com.softserveinc.ita.pageobjects.product.ComparisonPageProduct;
import com.softserveinc.ita.utils.TestRunner;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.softserveinc.ita.pageobjects.models.CategoryName.PLUMBING_AND_REPAIR;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTest extends TestRunner {

    @Test
    public void verifyDeleteFunction() {
//        List<String> productNames = new ArrayList<>();
//        productNames.add("Ванна акрилова CERSANIT VIRGO 170 +");
//        productNames.add("Ванна акрилова BESCO Modern 130х70");
//        productNames.add("Ванна акрилова SERENA SE-4001 150x70");

        List<String> productNames = Stream.of("Ванна акрилова CERSANIT VIRGO 170 +",
                "Ванна акрилова BESCO Modern 130х70",
                "Ванна акрилова SERENA SE-4001 150x70")
                .collect(toList());


        ComparisonPage products = homePage
                .getCategorySideBar()
                .openCategoryPage(PLUMBING_AND_REPAIR)
                .openSubCategoryPage("Ванни")
//                .addProductsToComparison(productNames)
                .addProductsToComparison(productNames)
                .openComparisonModal()
                .openComparisonPage("Ванни");

        String expectedString = "Ванна";

//        for (ComparisonPageProduct product : products.getAllComparisonPageProducts()) {
//            assertThat(product.getProductName())
//                    .as("name in comparison should contain text " + expectedString)
//                    .containsIgnoringCase(expectedString);
//        }
        List.of(products)
                .forEach(item -> assertThat(products
                        .getProduct(String.valueOf(item))
                        .getProductName())
                        .as("name in comparison should contain text " + expectedString)
                        .containsIgnoringCase(expectedString));

        assertThat(products.getAllComparisonPageProducts())
                .as("should be three added products")
                .hasSize(3);
        products.getProduct(String.valueOf(1)).remove();
        assertThat(products.getAllComparisonPageProducts())
                .as("should be two added products")
                .hasSize(2);
    }
}
