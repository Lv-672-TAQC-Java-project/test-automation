package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.Question;
import com.softserveinc.ita.pageobjects.models.QuestionSortingOption;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.DateUtil.toDate;
import static java.lang.String.*;
import static java.util.stream.IntStream.range;

public class QuestionTab {

    @Step("amounted question on page")
    public int getQuestionAmount() {

        return $$x("//div[@class='comment']")
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(60))
                .size();
    }

    @Step("selected sort option in tab question")
    public QuestionTab sort(QuestionSortingOption sortOption) {
        $x("//select").selectOption(sortOption.getSortOption());

        return this;
    }

    public Question getQuestion(int index) {

        return new Question(format
                ("//li[@class='product-questions__list-item ng-star-inserted'][%s]", index));
    }

    public List<Date> getQuestionsDates() {

        return range(1, getQuestionAmount())
                .mapToObj(i -> toDate(getQuestion(i).getQuestionDate()))
                .distinct()
                .collect(Collectors.toList());
    }
}
