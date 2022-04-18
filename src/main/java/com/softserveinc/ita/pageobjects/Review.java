package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;

@AllArgsConstructor
public class Review {

    private final String rootElementPath;

    public int getRating() {
        int amountOfFilledStars = $$x(String.format("%s%s", rootElementPath, "//*[name()='path' and @fill='#ffa900']"))
                .shouldHave(sizeGreaterThanOrEqual(0), Duration.ofSeconds(20))
                .size();

        return amountOfFilledStars;
    }
}
