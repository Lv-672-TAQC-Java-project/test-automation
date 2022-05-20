package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.CLOTHES_SHOES_AND_JEWELRY;
import static com.softserveinc.ita.pageobjects.models.ShoeSize.*;
import static org.assertj.core.api.Assertions.*;

public class ProductParameterTest extends TestRunner {
    @Description("Verify that selected product parameter displayed in product details page.")
    @Issue("https://jira.softserve.academy/projects/LVTAQC672/issues/LVTAQC672-34")
    @Test(description = "LVTAQC672-34")
    public void verifyPopularBrandsBlockFunctionality() {
        var productDetailsPage = homePage
                .getHeader()
                .openCatalog()
                .openCategoryPage(CLOTHES_SHOES_AND_JEWELRY)
                .openBrandProductsPage("Asics")
                .getProduct("Кросівки Asics Gel-Kayano 28 1011B189-400 Сині")
                .getShoeSize(SHOE_SIZE_44_5);

        var selectedShoeSize = productDetailsPage.getSelectedShoeSize();

        assertThat(selectedShoeSize)
                .as("active foot size should be equal to selected size")
                .isEqualTo(productDetailsPage.getSelectedShoeSize());
    }

}
