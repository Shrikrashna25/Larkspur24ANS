package com.testcase;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.Keyword;
import com.base.TestBase;
import com.locators.Locator;
import com.pages.HomePage;
import com.pages.StorePage;
import com.utils.Format;
import com.utils.WaitFor;

public class ProductTest extends TestBase {
	@Test(description = "Goto T shirt page and verify T shirt count")
	public void verifyCountofItemsForKidsTshirt() throws Exception {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.pollingEvery(Duration.ofMillis(500));
		Actions act = new Actions(driver);
		WebElement KidsMenu = driver.findElement(By.xpath("//a[@href=\"/shop/kids\"]")); 
		act.moveToElement(KidsMenu).perform();
		By Tshirt = By.xpath("//a[@href=\"/shop/kids\"]/parent::div/descendant::ul/li[2]/a");
		wait.until(ExpectedConditions.elementToBeClickable(Tshirt));
		driver.findElement(Tshirt).click();
		By titleCnt = (By.xpath("//span[@class=\"title-count\"]"));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(titleCnt));
		String titleCountInTxt = driver.findElement(titleCnt).getText();
		System.out.println(titleCountInTxt);

		String categoryCountInTxt = driver.findElement(By.xpath("(//span[@class=\"categories-num\"])[1]")).getText();
		System.out.println(categoryCountInTxt);

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(titleCountInTxt);
		int titleCount = 0;
		while (m.find()) {
			titleCount = Integer.parseInt(m.group());
		}
		m = p.matcher(categoryCountInTxt);
		int CatCount = 0;
		while (m.find()) {
			CatCount = Integer.parseInt(m.group());
		}
		Assert.assertEquals(titleCount, CatCount, "title count and cat count not same");
	}

	@Test // Test case using Framework
	public void verifyCountofItemForKidsTshirtUsingFramwork() {
		Keyword.hoverOn(Locator.kidsMenu);
		try {
			WaitFor.elementToBeClickable(Locator.tshirt);
		} catch (StaleElementReferenceException e) {
			WaitFor.elementNotToBeStale(Locator.tshirt);
		}
		Keyword.clickOn(Locator.tshirt);
		WaitFor.elementToBePresent(Locator.titleCnt);
		String TitleCountInTxt = Keyword.getTextOf(Locator.titleCnt);
		int titleCount = Format.exctractNumberFrom(TitleCountInTxt);
		String categoryCountInTxt = Keyword.getTextOf(Locator.CategoryCountInTxt);
		int categoryCount = Format.exctractNumberFrom(categoryCountInTxt);
		Assert.assertEquals(titleCount, categoryCount, "Title count and category count are not same");

	}

	@Test /// Test case using Page Object Model
	public void verifyCountOfItemsForKidsTShirtUsingPom() throws InterruptedException {

		HomePage homepage = new HomePage();
		homepage.hoverOnKidsMenu();
		
		homepage.waitForFlyout();
		homepage.clickOnFlyoutMenuItem("T-Shirt");
		StorePage storepage = new StorePage();
		storepage.waitForTshirtCategoryToBeClickable();
		String titleCount = storepage.getTitleCount();
		String categoryCount = storepage.getCategoryCount();
		Assert.assertEquals(titleCount, categoryCount, "Title count and category count are not same");

	}
	
		
}
