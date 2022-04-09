package com.softserveinc.ita.olehpysko;

import com.softserveinc.ita.ComparisonModal;
import com.softserveinc.ita.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteInComparisonListTest extends TestRunner {

    @Test
    public void verifyDeleteFunctionInComparisonList() {

        ComparisonModal plumbigAndRepairPage = homePage
                .openLinkInMenu("Сантехніка та ремонт")
                .openLinkInGoodsCell("Ванни")
                .buttonLibraInCatalog(3)
                .buttonLibraInHead()
                .buttonToComparison();

        assertThat(plumbigAndRepairPage.isVisibleProduct()).isTrue()
                .as("should be visible");
        assertThat(plumbigAndRepairPage.countProduct(3))
                .as("should be three products");
        plumbigAndRepairPage.deleteElement(1);
        assertThat(plumbigAndRepairPage.isVisibleProduct()).isTrue()
                .as("should be visible");
        assertThat(plumbigAndRepairPage.countProduct(2))
                .as("should be two products");
    }
}
