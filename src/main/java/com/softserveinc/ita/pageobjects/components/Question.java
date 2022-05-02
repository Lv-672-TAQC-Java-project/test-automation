package com.softserveinc.ita.pageobjects.components;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@AllArgsConstructor
public class Question {

    private final String rootElementPath;

    public String getTimeOfQuestion() {

        return $x(format("%s%s",rootElementPath,"//time[1]")).getText();
    }

}
