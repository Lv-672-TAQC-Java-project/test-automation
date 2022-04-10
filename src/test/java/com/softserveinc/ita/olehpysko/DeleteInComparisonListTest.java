package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.ComparisonModal;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteInComparisonListTest extends TestRunner {

    @Test
    public void verifyDeleteFunctionInComparisonList() {

        ComparisonModal plumbigAndRepairPage = homePage
                .openMenu("Сантехніка та ремонт")
                .openSubMenu("Ванни")
                .addAmountProductsToComparison(3)
                .openComparisonModal();

        assertThat(plumbigAndRepairPage.isLastProductDisplayed())
                .as("should be visible").isTrue();
        assertThat(plumbigAndRepairPage.checkAmountAddedProducts())
                .as("should be three products").isEqualTo(3);
        plumbigAndRepairPage.deleteAmountProducts(1);
        assertThat(plumbigAndRepairPage.isLastProductDisplayed())
                .as("should be visible").isTrue();
        assertThat(plumbigAndRepairPage.checkAmountAddedProducts())
                .as("should be two products").isEqualTo(2);
    }
}
