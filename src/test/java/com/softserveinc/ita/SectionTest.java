package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.product.Product;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SectionTest extends TestRunner {
    @Description("Verify that in the 'Останні переглянуті товари' products section are " +
            "three products that were watched before in this section.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-33")
    @Test(description = "LVTAQC672-33")
    public void verifyThatTheLastWatchedProductsAreRememberedInTheRecentlyViewedProductsSection() {
        String searchTerm = "Стілець";
        var header = homePage.getHeader();
        var searchResultPage = header.search(searchTerm);

        var firstProduct = searchResultPage.getProduct(1);
        String firstProductName = firstProduct.getName();
        firstProduct.openDetailsPage();

        header.search(searchTerm);
        var secondProduct = searchResultPage.getProduct(2);
        String secondProductName = secondProduct.getName();
        secondProduct.openDetailsPage();

        header.search(searchTerm);
        var thirdProduct = searchResultPage.getProduct(3);
        String thirdProductName = thirdProduct.getName();
        thirdProduct.openDetailsPage();

        header.openHomePage();
        var recentlyViewedProductsNames = homePage.getTheRecentlyViewedProductsNames();

        assertThat(recentlyViewedProductsNames)
                .as(firstProductName + " should be in the recently viewed products section")
                .contains(firstProductName)
                .as(secondProductName + " should be in the recently viewed products section")
                .contains(secondProductName)
                .as(thirdProductName + " should be in the recently viewed products section")
                .contains(thirdProductName);
    }
}
