package com.testcase;

import io.qameta.allure.*;
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

@Epic("Product Tests")
@Feature("Kids T-Shirts")
public class TestNgProductTestCase extends TestBase {

    @Test(description = "Goto T shirt page and verify T shirt count")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify the count of kids' t-shirts on the product page")
    @Description("This test navigates to the kids' t-shirt page and verifies the title count matches the category count.")
    public void verifyCountofItemsForKidsTshirt() throws Exception {
        ChromeDriver driver = new ChromeDriver();
        Allure.step("Launching the browser and navigating to Myntra.");
        driver.get("https://www.myntra.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Actions act = new Actions(driver);

        Allure.step("Hovering over the Kids menu.");
        WebElement KidsMenu = driver.findElement(By.xpath("//a[@href=\"/shop/kids\"]"));
        act.moveToElement(KidsMenu).perform();

        By Tshirt = By.xpath("//a[@href=\"/shop/kids\"]/parent::div/descendant::ul/li[2]/a");
        wait.until(ExpectedConditions.elementToBeClickable(Tshirt));
        Allure.step("Clicking on the T-shirt link.");
        driver.findElement(Tshirt).click();

        By titleCnt = By.xpath("//span[@class=\"title-count\"]");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(titleCnt));

        Allure.step("Extracting title and category counts.");
        String titleCountInTxt = driver.findElement(titleCnt).getText();
        String categoryCountInTxt = driver.findElement(By.xpath("(//span[@class=\"categories-num\"])[1]")).getText();

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

        Allure.step("Verifying that title count matches the category count.");
        Assert.assertEquals(titleCount, CatCount, "Title count and category count are not the same.");
    }

    @Test(description = "Verify count of kids' t-shirts using the framework")
    @Severity(SeverityLevel.NORMAL)
    @Story("Framework-based verification for kids' t-shirts")
    public void verifyCountofItemForKidsTshirtUsingFramework() {
        Allure.step("Hovering over the Kids menu.");
        Keyword.hoverOn(Locator.kidsMenu);

        try {
            WaitFor.elementToBeClickable(Locator.tshirt);
        } catch (StaleElementReferenceException e) {
            WaitFor.elementNotToBeStale(Locator.tshirt);
        }

        Allure.step("Clicking on the T-shirt link.");
        Keyword.clickOn(Locator.tshirt);

        Allure.step("Extracting title and category counts.");
        WaitFor.elementToBePresent(Locator.titleCnt);
        String titleCountInTxt = Keyword.getTextOf(Locator.titleCnt);
        int titleCount = Format.exctractNumberFrom(titleCountInTxt);

        String categoryCountInTxt = Keyword.getTextOf(Locator.CategoryCountInTxt);
        int categoryCount = Format.exctractNumberFrom(categoryCountInTxt);

        Allure.step("Verifying that title count matches the category count.");
        Assert.assertEquals(titleCount, categoryCount, "Title count and category count are not the same.");
    }

    @Test(description = "Verify count of kids' t-shirts using Page Object Model")
    @Severity(SeverityLevel.MINOR)
    @Story("Page Object Model-based verification for kids' t-shirts")
    public void verifyCountOfItemsForKidsTShirtUsingPom() {
        HomePage homepage = new HomePage();
        Allure.step("Hovering over the Kids menu.");
        homepage.hoverOnKidsMenu();

        Allure.step("Waiting for the flyout menu.");
        homepage.waitForFlyout();

        Allure.step("Clicking on the T-shirt menu item.");
        homepage.clickOnFlyoutMenuItem("T-Shirt");

        StorePage storepage = new StorePage();
        Allure.step("Waiting for the T-shirt category to be clickable.");
        storepage.waitForTshirtCategoryToBeClickable();

        Allure.step("Extracting title and category counts.");
        String titleCount = storepage.getTitleCount();
        String categoryCount = storepage.getCategoryCount();

        Allure.step("Verifying that title count matches the category count.");
        Assert.assertEquals(titleCount, categoryCount, "Title count and category count are not the same.");
    }

    // Additional tests can be enhanced similarly with Allure annotations and steps.
}
