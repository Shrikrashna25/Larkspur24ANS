package com.locators;

public interface Locator {
	String kidsMenu = "xpath##//a[@href=\"/shop/kids\"]";
    String tshirt ="xpath##//a[@href=\\\"/shop/kids\\\"]/parent::div/descendant::ul/li[2]/a ";
    String titleCnt ="xpath##//span[@class=\\\"title-count\\\"]";
    String CategoryCountInTxt ="xpath##//span[@class=\\\"categories-num\\\"])[1]";
}
