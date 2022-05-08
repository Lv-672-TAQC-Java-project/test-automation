package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.Question;
import com.softserveinc.ita.pageobjects.models.QuestionSortingOption;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;
import static java.util.stream.IntStream.range;

public class QuestionTab {

    public int getQuestionsAmount() {
        String questionPath = "//div[@class='comment']";

        if ($x(questionPath).is(visible)) {

            return $$x(questionPath)
                    .shouldHave(sizeGreaterThan(0), Duration.ofSeconds(60))
                    .size();
        } else {

            return 0;
        }
    }

    @Step("selected sort option in tab question")
    public QuestionTab sort(QuestionSortingOption sortOption) {
        $x("//select").selectOption(sortOption.ordinal());

        return this;
    }

    public Question getQuestion(int index) {

        return new Question(format
                ("//li[@class='product-questions__list-item ng-star-inserted'][%s]", index));
    }

    public List<Date> getUniqueQuestionsDates() {

        return range(1, getQuestionsAmount())
                .mapToObj(i -> getQuestion(i).getDate())
                .distinct()
                .collect(Collectors.toList());
    }
}
