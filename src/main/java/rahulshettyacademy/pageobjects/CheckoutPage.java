package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='field small']/input[@class='input txt']")
	WebElement CVV;

	@FindBy(xpath = "//div[@class='field']/input[@class='input txt']")
	WebElement cardname;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement SelectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	// By country = By.cssSelector("[placeholder='Select Country']");
	By results = By.cssSelector(".ta-results");

	public void fillCardDetails(String cvv, String CardName) {
		CVV.sendKeys(cvv);
		cardname.sendKeys(CardName);
	}

	public void selectCountry(String Country) {
		Actions a = new Actions(driver);
		a.sendKeys(country, Country).build().perform();
		waitForElementToAppear(results);
		SelectCountry.click();

	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);

	}

}
