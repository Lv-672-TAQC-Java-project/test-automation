package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.models.FootSize;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.CategoryName.CLOTHES_SHOES_AND_JEWELRY;
import static com.softserveinc.ita.pageobjects.models.BrandName.*;
import static com.softserveinc.ita.pageobjects.models.FootSize.*;
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
                .openBrandProductsPage(ASICS)
                .getProduct(2)
                .getFootSize(SIZE_44_5);

        var activeFootSize = productDetailsPage.getActiveFootSize();

        assertThat(activeFootSize)
                .as("active foot size should be equal to selected size")
                .isEqualTo(productDetailsPage.getActiveFootSize());
    }

}
