package com.softserveinc.ita.pageobjects;

import com.softserveinc.ita.pageobjects.components.Question;
import com.softserveinc.ita.pageobjects.models.QuestionSortingOption;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.*;

public class QuestionPage {

    @Step("amounted question on page")
    public int getAmountQuestion() {

        return $$x("//div[@class='comment']")
                .shouldHave(sizeNotEqual(0), Duration.ofSeconds(60))
                .size();
    }

    @Step("selected sort option in tab question")
    public QuestionPage selectTypeSort(QuestionSortingOption sortOption) {
        $x("//select").selectOptionByValue(sortOption.getSortOption());

        return this;
    }

    public Question getQuestion(int index) {

        return new Question(format
                ("//li[@class='product-questions__list-item ng-star-inserted'][%s]", index));
    }
}
