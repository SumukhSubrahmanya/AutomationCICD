package rahulshettyacademy.pageobjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMesssage;

	@FindBy(css = "td.m-3 .title")
	List<WebElement> items;

	public String verifyOrderConfirmation() {
		String message = confirmationMesssage.getText();
		return message;
	}

	public Boolean checkItemPresence(String productName) {
		Boolean product = items.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return product;
	}

}
