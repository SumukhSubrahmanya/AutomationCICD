package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException,InterruptedException
	{
		String productName = "ZARA COAT 3";
		String cvv = "386";
		String cardname = "Tester Testing";
		String countryName = "india";

		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginAplication("testinguser1212@gmail.com", "Test1231");
		String errorMessage = landingPage.getErrorMessage();
		System.out.println(errorMessage);
		Assert.assertEquals("Incorrect email or password.",errorMessage);

	}
	
	@Test
	public void ProductErrorValidation() throws IOException,InterruptedException
	{
		// TODO Auto-generated method stub
		//LandingPage landingPage = launchApplication();
		String productName = "ZARA COAT 3";
		String cvv = "386";
		String cardname = "Tester Testing";
		String countryName = "india";

		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginAplication("testinguser1212@gmail.com", "Test123@1");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
