package com.stepdefinations;

import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;

import com.base.Keyword;
import com.pages.HomePage;
import com.pages.StorePage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class First {
	@Given("Browser is opened and app url is launched")
	public void browserIsOpened() throws Exception {
		
		Keyword.openBrowser("Chrome");
		Keyword.launchUrl("https://www.myntra.com/");
	}
	@When("User hovers on Kids Menu")
     public void hoversOnKidsMenu() {
	HomePage homepage = new HomePage();
	homepage.hoverOnKidsMenu();
}
	@And("Click on T-Shirt")
	public void clickOnTshirt() {
		HomePage homepage = new HomePage();
		homepage.waitForFlyout();
		homepage.clickOnFlyoutMenuItem("T-Shirts");
	}
//	@Then("Wait for T-Shirt page to launch")
//	public void waitForTshirt-page(){
		StorePage storepage = new StorePage();
//		storepage.waitForTshirtCategoryToBeClickable();
//	}
	@And("Verify the category count and title count to be equal")
	public void verifyCatCountAndTitleCount() {
		StorePage storepage = new StorePage();
	String titleCount =	storepage.getTitleCount();
    String CategoryCount=storepage.getCategoryCount();
    Assert.assertEquals(titleCount, CategoryCount, "title count and cat count not same");
	}
	// This is the Parameterized cucumber test case ,parameterized feature file
	int x,y,result=0;
	@Given("I have number{int} and {int}")
	public void acceptNumbers(int x,int y) {
		this.x=x;
		this.y=y;
	}
	@When(" I add them")
	public void addNumbers() {
		result=x+y;
	}
	@Then("verify is reslut is prime")
	public void checkIfAdditionIsPrime() {
		int count=1;
		for(int i=2;i<result/2;i++) {
			if(result%i==0) {
				count++;
				if(count>=2) {
					break;
				}
			}
		}
		if(count>=2) {
			System.out.println(result+"is not prime");
		}else {
			System.out.println(result+"is prime number");
		}
	}
	
	// list of parameter Example test case 
	
	Map<String, Integer>fruits;
	@Given("I have following fruits:")
	public void acceptFruitListOfFruits(Map<String, Integer>fruits) {
		this.fruits=fruits;
		}
	@Then("print them one bye one")
	public void printFruitsList() {
		for (Entry<String,Integer> fruit : fruits.entrySet()) {
			System.out.println(fruit.getKey()+"="+fruit.getValue());
		}
	}
	// Data table example
	String pincode;
	@Given("I have {string}")
	public void acceptPincode(String pincode) {
		this.pincode=pincode;
	}
	@Then("print the pincode")
	public void printPincode() {
		System.out.println(this.pincode);
	}
}
