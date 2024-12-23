package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Keyword;
import com.utils.Format;
import com.utils.WaitFor;

public class StorePage {
	@FindBy(xpath="//a[@href=\"/shop/kids\"]/parent::div/descendant::ul/li[2]/a")
	private WebElement tShirtMenu;
	@FindBy(xpath="//span[@class=\"title-count\"]")
	private WebElement titleCount;
	@FindBy(xpath="//span[@class=\"categories-num\"])[1]")
	private WebElement categoryCount;
	
	
public StorePage() {
	PageFactory.initElements(Keyword.driver,this);
}

public static void waitForTshirtCategoryToBeClickable() {
	
	//WaitFor.elementToBeClickable(tShirtMenu);

	
	 
}
public String getTitleCount() {
	
	String titleCnt =titleCount.getText();
	int count = Format.exctractNumberFrom(titleCnt);
	return count+"";
	
}
public String getCategoryCount() {
	
	String categoryCnt= categoryCount.getText();
	int count = Format.exctractNumberFrom(categoryCnt);
	return count+"";
	
	
	

	
}
}
