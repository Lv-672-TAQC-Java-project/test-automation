package com.softserveinc.ita.pageobjects.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FilterCategory {
    LAPTOPS_AND_COMPUTERS("section_id=80253"),
    SMARTPHONES_TV_AND_ELECTRONICS("section_id=4627949"),
    SERVICES("section_id=4630262"),
    HOUSEHOLD_APPLIANCES_INTERIOR("section_id=80025"),
    GOODS_GOR_CHILDREN("section_id=88468"),
    TOOLS_AND_AUTOMOTIVE_PRODUCTS("section_id=4627858"),
    PLUMBING_AND_REPAIR("section_id=4628418"),
    FLASKS("section_id=4653010"),
    SPORTS_AND_HOBBIES("section_id=4627893"),
    GOODS_FOR_BUSINESS("section_id=4627851"),
    HOUSEHOLD_GOODS("section_id=2394287"),
    REDUCED_ACCESSORIES("section_id=101808");

    private final String categorySectionId;
}
