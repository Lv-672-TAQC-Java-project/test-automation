package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastViewedProductsRememberedTest extends TestRunner {
    @Description("Verify that in the 'Останні переглянуті товари' products section are " +
            "three products that were viewed before in this section.")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-33")
    @Test(description = "LVTAQC672-33")
    public void verifyThatTheLastViewedProductsAreRememberedInTheLastViewedProductsSection() {
        var searchTerm = "Стілець";
        var header = homePage.getHeader();
        var searchResultPage = header.search(searchTerm);

        var firstProduct = searchResultPage.getProduct(1);
        var firstProductName = firstProduct.getName();
        //need that to add the product to the section
        firstProduct.openDetailsPage();

        header.search(searchTerm);
        var secondProduct = searchResultPage.getProduct(2);
        var secondProductName = secondProduct.getName();
        secondProduct.openDetailsPage();

        header.search(searchTerm);
        var thirdProduct = searchResultPage.getProduct(3);
        var thirdProductName = thirdProduct.getName();
        thirdProduct.openDetailsPage();

        var lastViewedProductsNames = header
                .openHomePage()
                .getLastViewedProductsSection()
                .getNames();

        assertThat(lastViewedProductsNames)
                .as(firstProductName + " should be in the recently viewed products section")
                .contains(firstProductName)
                .as(secondProductName + " should be in the recently viewed products section")
                .contains(secondProductName)
                .as(thirdProductName + " should be in the recently viewed products section")
                .contains(thirdProductName);
    }
}
