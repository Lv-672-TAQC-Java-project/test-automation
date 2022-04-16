package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SearchResultSidebar {

    public SidebarPriceRangeComponent getPriceRangeComponent() {
        return new SidebarPriceRangeComponent();
    }
}
