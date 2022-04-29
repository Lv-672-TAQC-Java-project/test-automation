package com.softserveinc.ita.pageobjects.components;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

@AllArgsConstructor
public class Review {

    private final String rootElementPath;

    public int getRating() {

        return $$x(format("%s//*[name()='path' and @fill='#ffa900']", rootElementPath))
                .shouldHave(sizeGreaterThanOrEqual(0), ofSeconds(20))
                .size();
    }
}
