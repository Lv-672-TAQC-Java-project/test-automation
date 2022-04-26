package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class Review {

    private final String rootElementPath;

    @Step("Get a rating of the review")
    public int getRating() {

        return $$x(String.format("%s%s", rootElementPath, "//*[name()='path' and @fill='#ffa900']"))
                .shouldHave(sizeGreaterThanOrEqual(0), ofSeconds(20))
                .size();
    }

    public String getComment(){
        return $x(String.format("%s%s", rootElementPath, "/div[@class='comment__inner']//div/p"))
                .shouldBe(visible,ofSeconds(10))
                .text();
    }
}
