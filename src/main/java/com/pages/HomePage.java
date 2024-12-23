package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Keyword;
import com.utils.WaitFor;

public class HomePage {
	@FindBy(xpath = "//a[@href=\"/shop/kids\"]")
	private WebElement kidsMenu;

	@FindBy(xpath = "//div[@class=\"desktop-backdropStyle\"]")
	private WebElement flyout;
	
	
	public HomePage() { 
		PageFactory.initElements(Keyword.driver, this);
	}
	public void hoverOnKidsMenu() {
		Keyword.hoverOn(kidsMenu);
  System.out.println("hover on kids menu");
	}

	public void waitForFlyout() {
		WaitFor.elementToBeClickable(flyout);
		System.out.println("Wait for flyout open");
	 }
   
	
	public void clickOnFlyoutMenuItem(String itemName) {
		flyout.findElement(By.xpath("//*[contains(text(),'"+itemName+"')]")).click();
		System.out.println(" click on item ");
	}
}
