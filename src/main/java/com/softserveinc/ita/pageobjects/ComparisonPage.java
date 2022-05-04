package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.product.ComparisonPageProduct;
import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;
import static java.util.stream.IntStream.*;

@Getter
public class ComparisonPage {

    private final Header header = new Header();

    public ComparisonPageProduct getProduct(int index) {
        return new ComparisonPageProduct(
                format("//*[@class='products-grid__cell ng-star-inserted'][%s]", index));
    }

    @Step("add more models to the comparison")
    public SubCategoryPage addMoreModels() {
        $x("//a/span[@class = 'comparison-settings__label']").click();

        return new SubCategoryPage();
    }

    public ComparisonPageProduct getProduct(String name) {
        return new ComparisonPageProduct(
                format("//a[normalize-space(text()) = '%s']//ancestor::li", name));
    }

    public List<ComparisonPageProduct> getAllComparisonPageProducts() {
        String productsPath = "//*[@class='products-grid__cell ng-star-inserted']";
        int amountOfProducts = $$x(productsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10)).size();

        return rangeClosed(1, amountOfProducts)
                .mapToObj(i -> new ComparisonPageProduct(format("(%s)[%s]", productsPath, i)))
                .collect(Collectors.toList());
    }
}
