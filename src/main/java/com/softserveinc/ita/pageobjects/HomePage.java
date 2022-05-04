package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.pageobjects.components.Header;
import com.softserveinc.ita.pageobjects.product.InSectionProduct;
import io.qameta.allure.Step;
import lombok.Getter;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

@Getter
public class HomePage {

    private final CategorySideBar categorySideBar = new CategorySideBar();
    private final Header header = new Header();

    @Step("Opened home page")
    public HomePage open() {
        Selenide.open("https://rozetka.com.ua/ua/");

        return this;
    }

    /**
     * Cart testing precondition.
     * Use this method to empty the cart before the test.
     */
    @Step("Emptied cart and close popup")
    public HomePage emptyCart() {
        header
                .openCart()
                .empty()
                .close();

        return this;
    }

    public List<String> getTheRecentlyViewedProductsNames(){
        String inSectionProductsPath = "(//ul[@class='main-goods__grid ng-star-inserted'])[1]//div[@class='tile']";
        int inSectionProductsAmount = $$x(inSectionProductsPath)
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(10))
                .size();

        return rangeClosed(1, inSectionProductsAmount)
                .mapToObj(i -> new InSectionProduct(format("(%s)[%s]", inSectionProductsPath, i)).getName())
                .collect(toList());
    }
}
