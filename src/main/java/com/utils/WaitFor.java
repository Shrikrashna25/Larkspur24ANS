package com.utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.Keyword;

public class WaitFor {

	private static WebDriverWait wait = null;

	static {
		wait = new WebDriverWait(Keyword.driver, Duration.ofSeconds(60));
		wait.pollingEvery(Duration.ofMillis(500));
		wait.ignoring(NoSuchElementException.class);

	}

	public static void elementToBeClickable(String locator) {

		WebElement element = Keyword.getWebElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public static void elementToBePresent(String locator) {

		wait.until(ExpectedConditions.presenceOfElementLocated(Keyword.getBy(locator)));// By class object
	}

	public static void elementNotToBeStale(String locator) {
		WebElement element = Keyword.getWebElement(locator);
		wait.until(ExpectedConditions.stalenessOf(element));
	}

	public static void elementToBeClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
		System.out.println("wait for flyout");
	}
}
