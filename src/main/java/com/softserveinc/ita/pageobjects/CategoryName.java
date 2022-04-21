package com.softserveinc.ita.pageobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryName {
    LAPTOPS_AND_COMPUTERS("computers-notebooks/c80253/"),
    SMARTPHONES_TV_AND_ELECTRONICS("telefony-tv-i-ehlektronika/c4627949/"),
    PRODUCTS_FOR_GAMERS("game-zone/c80261/"),
    HOUSEHOLD_APPLIANCES("https://bt.rozetka.com.ua/ua/"),
    HOUSEHOLD_GOODS("tovary-dlya-doma/c2394287/"),
    TOOLS_AND_AUTOMOTIVE_PRODUCTS("instrumenty-i-avtotovary/c4627858/"),
    PLUMBING_AND_REPAIR("santekhnika-i-remont/c4628418/"),
    COTTAGE_GARDEN_AND_VEGETABLE_GARDEN("dacha-sad-ogorod/c2394297/"),
    SPORTS_AND_HOBBIES("sport-i-uvlecheniya/c4627893/"),
    CLOTHES_SHOES_AND_JEWELRY("shoes_clothes/c1162030/"),
    BEAUTY_AND_HEALTH("krasota-i-zdorovje/c4629305/"),
    CHILDRENS_GOODS("kids/c88468/"),
    PET_PRODUCTS("zootovary/c3520929/"),
    STATIONERY_AND_BOOKS("office-school-books/c4625734/"),
    ALCOHOLIC_BEVERAGES_AND_PRODUCTS("alkoholnie-napitki-i-produkty/c4626923/"),
    GOODS_FOR_BUSINESS_AND_SERVICES("tovary-dlya-biznesa/c4627851/");

    private final String categoryName;
}
