package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdulthoodConfirmation {
    CONFIRM("//div[contains(@class, 'rz-btn_blue')]"),
    DO_NOT_CONFIRM("//div[contains(@class, 'rz-btn_gray')]");

    @Override
    public String toString() {
        return confirmationButtonPath;
    }

    private final String confirmationButtonPath;
}
