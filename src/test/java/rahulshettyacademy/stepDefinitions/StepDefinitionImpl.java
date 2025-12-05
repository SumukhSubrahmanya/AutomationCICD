package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		//code
		landingpage=launchApplication();
		
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password)
	{
		//code
		productCatalogue = landingPage.loginAplication(username,password);		
	}
	
	@When("^I add (.+) to the cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		//code
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
	}
	
	//And Checkout <productName> and submit the order
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		//checkoutPage.fillCardDetails(cvv, cardname);
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		//code
		String message = confirmationPage.verifyOrderConfirmation();
		Assert.assertTrue(message.equalsIgnoreCase(string));

		//Boolean itemMatch = confirmationPage.checkItemPresence(productName);
		//Assert.assertTrue(itemMatch);
		
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable
	{
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
		driver.close();
	}
	
	
}
