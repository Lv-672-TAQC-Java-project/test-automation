package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.SortQuestionType;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.StringToDateConverter.convertStringToDate;
import static java.lang.String.*;
import static java.util.stream.IntStream.range;

public class ProductQuestionPage {

    @Step("amounted question on page")
    public int getAmountQuestion() {

        return $$x("//div[@class='comment']")
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(60))
                .size();
    }

    @Step("selected sort option in tab question")
    public ProductQuestionPage selectTypeSort(SortQuestionType sortOption) {
        String sortDefault = "//select[@id='sort-select']";
        String sortSelect = format("//select/option[@value='%s']", sortOption.getSortOption());

        if (!$x(sortDefault)
                .getText()
                .equals($x(sortSelect)
                        .getText())) {
            $x(sortDefault).selectOptionByValue(sortOption.getSortOption());
        }

        return this;
    }

    public String getTimeOfQuestion(int numberQuestion) {

        return $x(format("(//li[@class='product-questions__list-item ng-star-inserted'][%s]//time)[1]", numberQuestion))
                .getText();
    }

    public List<Date> addStringDateToList(String date) {

        return range(1, getAmountQuestion())
                .mapToObj(i -> convertStringToDate(date))
                .distinct()
                .collect(Collectors.toList());
    }
@Step("")
    public List<Date> getUniqueDate() {
        return range(1, getAmountQuestion())
                .mapToObj(i -> convertStringToDate(getTimeOfQuestion(i)))
                .distinct()
                .collect(Collectors.toList());
    }
}
