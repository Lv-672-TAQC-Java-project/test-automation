package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class Review {

    private final String rootElementPath;

    public int getRating() {

        return $$x(String.format("%s%s", rootElementPath, "//*[name()='path' and @fill='#ffa900']"))
                .shouldHave(sizeGreaterThanOrEqual(0), ofSeconds(20))
                .size();
    }
}
