package com.softserveinc.ita.pageobjects.components;

import com.softserveinc.ita.pageobjects.product.InSectionProduct;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

abstract class GenericSection {

    public List<InSectionProduct> getProducts() {
        var inSectionProductsPath = format("(//ul[@class='main-goods__grid ng-star-inserted'])[%s]//div[@class='tile']",
                getSectionIndex());

        //has not changed rangeClosed to $$x().stream()
        //to pass to the constructor of class xpath instead of SelenideElement
        return rangeClosed(1, $$x(inSectionProductsPath)
                .shouldHave(sizeGreaterThanOrEqual(0), ofSeconds(20))
                .size())
                .mapToObj(i -> new InSectionProduct(format("(%s)[%s]", inSectionProductsPath, i)))
                .collect(toList());
    }

    public List<String> getNames() {

        return getProducts()
                .stream()
                .map(InSectionProduct::getName)
                .collect(toList());
    }

    abstract int getSectionIndex();
}
