package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.models.SortOptionInTabQuestion;
import io.qameta.allure.Step;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.util.stream.IntStream.range;

public class ProductQuestionPage {

    @Step("amounted question on page")
    public int getAmountQuestion() {

        return $$x("//div[@class='comment']")
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(60))
                .size();
    }

    @Step("selected sort option in tab question")
    public ProductQuestionPage sortSelectOption(SortOptionInTabQuestion sortOption) {
        String sortDefault = "//select[@id='sort-select']";
        String sortSelect = String.format("//select/option[@value='%s']", sortOption.getSortOption());

        if (!$x(sortDefault)
                .getText()
                .equals($x(sortSelect)
                        .getText())) {
            $x(sortDefault).selectOptionByValue(sortOption.getSortOption());
        }

        return this;
    }

    public String getTimeOfQuestion(int numberQuestion) {

        return $x(String.format("(//li[@class='product-questions__list-item ng-star-inserted'][%s]//time)[1]", numberQuestion))
                .getText();
    }

    @Step("converted String date to Date format")
    public Date convertDate(String date) {
        LocalDate localDate = null;
        if (date.matches(".*\\d.*")) {
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter
                            .ofPattern("dd MMMM uuuu")
                            .withLocale(new Locale("uk"));
            localDate = LocalDate.parse(date, dateTimeFormatter);
        }

        return Date.from(localDate
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }

@Step("")
    public List<Date> getUniqueDate() {
        return range(1, getAmountQuestion())
                .mapToObj(i -> convertDate(getTimeOfQuestion(i)))
                .distinct()
                .collect(Collectors.toList());
    }

    @Step("")
    public Date getOneDate() {

        return null;
    }
}
