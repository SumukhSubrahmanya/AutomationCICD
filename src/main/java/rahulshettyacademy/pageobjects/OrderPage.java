package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// PageFactory ---> using this the code can be made clean and easy to understand

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
