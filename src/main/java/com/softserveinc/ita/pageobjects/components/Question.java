package com.softserveinc.ita.pageobjects.components;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@AllArgsConstructor
public class Question {

    private final String rootElementPath;

    public String getQuestionDate() {

        return $x(format("%s//time[1]", rootElementPath)).text();
    }
}
