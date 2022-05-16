package com.softserveinc.ita.pageobjects.product;

import lombok.AllArgsConstructor;

import static com.codeborne.selenide.Selenide.$x;

@AllArgsConstructor
public class InSectionProduct {

    private final String rootElementPath;

    public String getName() {

        return $x(rootElementPath + "//a[@class='tile__title']").text();
    }
}
