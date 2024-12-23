package com.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public abstract class Keyword {
	public static RemoteWebDriver driver = null;

	public static void openBrowser(String browserName) throws Exception {
		if (browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();

		} else {
			throw new Exception(browserName);
		}

	}

	public static void launchUrl(String Url) {
		driver.get(Url);
		driver.manage().window().maximize();

	}

	public static void hoverOn(WebElement element) {

		Actions act = new Actions(driver);
		act.moveToElement(element).perform();

	}

	public static void hoverOn(String element) {
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath(element));
		act.moveToElement(ele).perform();
	}

	public static void enterText(String locator, String textToEneter) {
		getWebElement(locator).sendKeys("textToEnter");

	}

	public static By getBy(String locator) {

		By by = null;
		String LocatorType = locator.split("##")[0];
		String LocatorValue = locator.split("##")[1];
		if (LocatorType.contentEquals("id")) {
			by = By.id(LocatorValue);
		} else if (LocatorType.contentEquals("name")) {
			by = By.name(LocatorValue);
		} else if (LocatorType.contentEquals("className")) {
			by = By.className(LocatorValue);
		} else if (LocatorType.contentEquals("tagName")) {
			by = By.tagName(LocatorValue);
		} else if (LocatorType.contentEquals("Linktext")) {
			by = By.linkText(LocatorValue);
		} else if (LocatorType.contentEquals("PartialLinkText")) {
			by = By.partialLinkText(LocatorValue);
		} else if (LocatorType.contentEquals("xpath")) {
			by = By.xpath(LocatorValue);
		} else if (LocatorType.contentEquals("CSS")) {
			by = By.cssSelector(LocatorValue);
		}

		return null;

	}

	public static WebElement getWebElement(String locator) {

		String LocatorType = locator.split("##")[0];
		String LocatorValue = locator.split("##")[1];
		WebElement element = null;
		if (LocatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("ClassName")) {
			driver.findElement(By.className(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("tagName")) {
			driver.findElement(By.tagName(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("LinkText")) {
			driver.findElement(By.linkText(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("partialLinkText")) {
			driver.findElement(By.partialLinkText(LocatorValue));
		} else if (LocatorType.equalsIgnoreCase("css")) {
			driver.findElement(By.cssSelector(LocatorValue));
		}

		return element;
	}

	public static void clickOn(String locator) {
		getWebElement(locator).click();
	}

	public static String getTextOf(String locator) {
		String value = null;
		value = getWebElement(locator).getText();
		return value;

	}

}
