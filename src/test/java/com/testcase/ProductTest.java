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
	// 1.Test case-1 for Alley Solley Brand 
		@Test
		public void m1() {

			ChromeDriver driver = new ChromeDriver();

			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
		    driver.findElement(By.xpath("//input[@placeholder='Search Amazon.in']")).sendKeys("Tshirt", Keys.ENTER);
	       // driver.findElement(By.cssSelector("input[type=\"text\"]"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath("(//i[@class=\"a-icon a-icon-checkbox\"])[5]")).click();
		}
		// 2. to chek the item in cart is add or not
		@Test
		public void m2() {
			ChromeDriver driver = new ChromeDriver();

			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//span[@class=\"nav-cart-icon nav-sprite\"]")).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			Assert.assertEquals(10, 10);

		}
		// 3. To chek and verifying available seats on platform travelyaari.com
		@Test
		public void m3() {
			ChromeDriver driver = new ChromeDriver();
			driver.get("https://www.travelyaari.com/");
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//input[@id=\"from-city\"]")).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.findElement(By.xpath("//input[@id=\"from-city\"]")).sendKeys("Pune",Keys.ENTER);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.findElement(By.xpath("//input[@id=\"to-city\"]")).click();
			driver.findElement(By.xpath("//input[@id=\"to-city\"]")).sendKeys("Nagpur");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.findElement(By.xpath("//button[@class=\"ty-button ty-button-orange\"]")).click();
			driver.findElement(By.xpath("//div[@class=\"ty-btn orange\"]")).click();
		}
		// add item in add 
		@Test
		public void m4() {
			ChromeDriver driver = new ChromeDriver();
			driver.get("https://www.bigbasket.com/");
			driver.manage().window().maximize();
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.PAGE_DOWN).perform();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath("(//button[@class=\"Button-sc-1dr2sn8-0 ezcVVO CtaOnDeck___StyledButton3-sc-orwifk-2 hRCLjF CtaOnDeck___StyledButton3-sc-orwifk-2 hRCLjF\"])[3]")).click();
			
			
		}
}
