package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchResultSidebar {
    private final String rootElementPath;

    public SidebarPriceRangeComponent getPriceRangeSidebarComponent() {
        return new SidebarPriceRangeComponent("//*[@data-filter-name = 'price']");
    }
}
