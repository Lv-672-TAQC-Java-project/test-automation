package com.softserveinc.ita;

import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.softserveinc.ita.pageobjects.models.CategoryName.PLUMBING_AND_REPAIR;
import static org.assertj.core.api.Assertions.assertThat;

public class ComparisonTest extends TestRunner {

    @Description("Add test script to cover delete function in Rozetka's comparison list")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-9")
    @Test(description = "LVTAQC672-9")
    public void verifyDeleteProductFromComparisonPage() {
        var productNames = new ArrayList<String>();
        productNames.add("Ванна акрилова CERSANIT VIRGO 170 +");
        productNames.add("Ванна акрилова BESCO Modern 130х70");
        productNames.add("Ванна акрилова SERENA SE-4001 150x70");

        var subCategory = "Ванни";

        var comparisonProducts = homePage
                .getCategorySideBar()
                .openCategoryPage(PLUMBING_AND_REPAIR)
                .openSubCategoryPage(subCategory)
                .addProductsToComparison(productNames)
                .openComparisonModal()
                .getSubCategory(subCategory)
                .openComparisonPage();

        var expectedString = "Ванна";

        comparisonProducts.getAllComparisonPageProducts()
                .forEach(product -> assertThat(product.getProductName())
                        .as("name in comparison should contain text " + expectedString)
                        .containsIgnoringCase(expectedString));

        assertThat(comparisonProducts.getAllComparisonPageProducts())
                .as("should be three added products")
                .hasSize(3);
        comparisonProducts
                .getProduct(1)
                .remove();
        assertThat(comparisonProducts.getAllComparisonPageProducts())
                .as("should be two added products")
                .hasSize(2);
    }

    @Description("Verify that products added to the comparison")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-12")
    @Test(description = "LVTAQC672-12")
    public void verifyThatProductsAddedToTheComparison() {
        var searchTerm = "Lenovo";

        var header = homePage.getHeader();

        var searchResultPage = header
                .search(searchTerm)
                .getFilterCategorySideBar()
                .filterBySubCategory("Планшети")
                .getProduct(1)
                .addToListOfComparisons()
                .getProduct(2)
                .addToListOfComparisons();

        var categoryName = "Планшет";
        var comparisonPage = header
                .openComparisonModal()
                .openComparisonPage(categoryName);

        var productList = comparisonPage.getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 2 products should be in comparison")
                .hasSize(2);

        productList
                .forEach(product -> assertThat(product.getProductName())
                        .as("All products should contain " + searchTerm)
                        .containsIgnoringCase(searchTerm)
                        .as("All products should contain " + categoryName)
                        .containsIgnoringCase(categoryName));

        comparisonPage
                .addMoreModels()
                .getProductNotAddedToComparison(1)
                .addToListOfComparisons();
        productList = header
                .openComparisonModal()
                .openComparisonPage(categoryName)
                .getAllComparisonPageProducts();

        assertThat(productList)
                .as("The 3 products should be in comparison")
                .hasSize(3);

        productList
                .forEach(product -> assertThat(product.getProductName())
                        .as("All products should contain " + categoryName)
                        .containsIgnoringCase(categoryName));
    }

    @Description("Verify 'show only differences' functionality when comparing products")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-6")
    @Test(description = "LVTAQC672-6")
    public void verifyShowOnlyDifferencesFunctionality() {
        var header = homePage.getHeader();
        var searchTerm = "notebook";

        var searchResultPage = header.search(searchTerm);

        var firstProduct = searchResultPage.getProduct(1);
        var secondProduct = searchResultPage.getProduct(2);

        firstProduct.addToListOfComparisons();
        secondProduct.addToListOfComparisons();

        var firstProductName = firstProduct.getName();
        var secondProductName = secondProduct.getName();

        var productCategory = "Ноутбуки";
        var comparisonPage =
                header
                        .openComparisonModal()
                        .getSubCategory(productCategory)
                        .openComparisonPage();

        var firstComparableProduct = comparisonPage.getProduct(firstProductName);
        var secondComparableProduct = comparisonPage.getProduct(secondProductName);

        var firstProductCharacteristics = firstComparableProduct.getCharacteristics();
        var secondProductCharacteristics = secondComparableProduct.getCharacteristics();

        comparisonPage.showOnlyDifferences();

        var firstProductCharacteristicsAfterChange = firstComparableProduct.getCharacteristics();
        var secondProductCharacteristicsAfterChange = secondComparableProduct.getCharacteristics();

        assertThat(firstProductCharacteristicsAfterChange)
                .as("After change products characteristics lists should display only distinctive characteristics")
                .doesNotContainSequence(secondProductCharacteristicsAfterChange);

        assertThat(firstProductCharacteristics)
                .as("Before change characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(firstProductCharacteristicsAfterChange
                        .size());

        assertThat(secondProductCharacteristics)
                .as("full characteristics list should have greater size than list which displays only differences")
                .hasSizeGreaterThan(secondProductCharacteristicsAfterChange
                        .size());
    }

    @Description("Add test script to cover delete function in comparison modal")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-39")
    @Test(description = "LVTAQC672-39")
    public void verifyThatSubCategoryDeletedFromComparisonModal() {
        var header = homePage
                .emptyComparisonModal()
                .getHeader();

        var filterCategorySideBar = header
                .search("lenovo")
                .getFilterCategorySideBar();

        filterCategorySideBar
                .filterBySubCategory("Чохли для мобільних телефонів")
                .getProduct(1)
                .addToListOfComparisons();

        var subCategoryName = "Блоки живлення для ноутбуків";
        filterCategorySideBar
                .filterBySubCategory(subCategoryName)
                .getProduct(1)
                .addToListOfComparisons();

        var comparisonModal = header
                .openMenuSideBar()
                .openComparisonModal();

        var subCategories = comparisonModal.getSubCategories();

        assertThat(subCategories)
                .as("The 2 subCategories should be in comparison modal")
                .hasSize(2);

        subCategories = comparisonModal
                .getSubCategory(subCategoryName)
                .remove()
                .getSubCategories();

        assertThat(subCategories)
                .as("The 1 subCategories should be in comparison modal")
                .hasSize(1);
    }
}
