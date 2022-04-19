package com.softserveinc.ita.pageobjects;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public enum CategoryName {
    LAPTOPS_AND_COMPUTERS("Ноутбуки та комп’ютери"),
    SMARTPHONES_TV_AND_ELECTRONICS("Смартфони, ТВ і електроніка"),
    PRODUCTS_FOR_GAMERS("Товари для геймерів"),
    HOUSEHOLD_APPLIANCES("Побутова техніка"),
    HOUSEHOLD_GOODS("Товари для дому"),
    TOOLS_AND_AUTOMOTIVE_PRODUCTS("Інструменти та автотовари"),
    PLUMBING_AND_REPAIR("Сантехніка та ремонт"),
    COTTAGE_GARDEN_AND_VEGETABLE_GARDEN("Дача, сад і город"),
    SPORTS_AND_HOBBIES("Спорт і захоплення"),
    CLOTHES_SHOES_AND_JEWELRY("Одяг, взуття та прикраси"),
    BEAUTY_AND_HEALTH("Краса та здоров’я"),
    CHILDRENS_GOODS("Дитячі товари"),
    PET_PRODUCTS("Зоотовари"),
    STATIONERY_AND_BOOKS("Канцтовари та книги"),
    ALCOHOLIC_BEVERAGES_AND_PRODUCTS("Алкогольні напої та продукти"),
    GOODS_FOR_BUSINESS_AND_SERVICES("Товари для бізнесу та послуги");

    private final String nameCategory;

    CategoryName(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }
}
