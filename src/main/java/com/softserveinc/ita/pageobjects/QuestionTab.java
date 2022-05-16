package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.Question;
import com.softserveinc.ita.pageobjects.models.QuestionSortingOption;
import io.qameta.allure.Step;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;
import static java.time.Duration.*;
import static java.util.stream.IntStream.rangeClosed;

public class QuestionTab {

    @Step("selected sort option in tab question")
    public QuestionTab sort(QuestionSortingOption sortOption) {
        $x("//select").selectOption(sortOption.ordinal());

        return this;
    }

    public Question getQuestion(int index) {

        return new Question(format
                ("//li[@class='product-questions__list-item ng-star-inserted'][%s]", index));
    }

    public List<Date> getQuestionsDates() {
        var questionPath = "//div[@class='comment']";
        int questionsAmount;

        if ($x(questionPath).is(visible)) {
            questionsAmount = $$x(questionPath)
                    .shouldHave(sizeNotEqual(0), ofSeconds(60))
                    .size();
        } else {
            questionsAmount = 0;
        }

        return rangeClosed(1, questionsAmount)
                .mapToObj(i -> getQuestion(i).getDate())
                .collect(Collectors.toList());
    }
}
