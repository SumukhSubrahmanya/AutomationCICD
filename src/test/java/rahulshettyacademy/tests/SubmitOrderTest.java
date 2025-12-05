package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException,InterruptedException
	{
		// TODO Auto-generated method stub
		LandingPage landingPage = launchApplication();
		String cvv = "386";
		String cardname = "Tester Testing";
		String countryName = "india";

		//landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginAplication(input.get("email"),input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.fillCardDetails(cvv, cardname);
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String message = confirmationPage.verifyOrderConfirmation();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

		Boolean itemMatch = confirmationPage.checkItemPresence(input.get("product"));
		Assert.assertTrue(itemMatch);
		Thread.sleep(2000);

	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginAplication("testinguser1212@gmail.com", "Test123@1");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	

	
	
	@DataProvider
	public Object[][] getData() throws IOException {
	    /*HashMap<String, String> map = new HashMap<String, String>();
	    map.put("email", "testinguser1212@gmail.com");
	    map.put("password", "Test123@1");
	    map.put("product", "ZARA COAT 3");

	   HashMap<String, String> map1 = new HashMap<String, String>();
	    map1.put("email", "testinguser1212@gmail.com");
	    map1.put("password", "Test123@1");
	    map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

	    return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	

}
