package com.softserveinc.ita.pageobjects.components;

import lombok.AllArgsConstructor;

import java.util.Date;

import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.utils.DateUtil.toDate;

@AllArgsConstructor
public class Question {

    private final String rootElementPath;

    public Date getDate() {

        return toDate($x(rootElementPath + "//time[1]").text());
    }
}
