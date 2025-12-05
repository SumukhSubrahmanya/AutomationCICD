package rahulshettyacademy.tests;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("testinguser1212@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test123@1");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->
		product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.xpath("//div[@class='field small']/input[@class='input txt']")).sendKeys("358");
		driver.findElement(By.xpath("//div[@class='field']/input[@class='input txt']")).sendKeys("Tester testing");
		Thread.sleep(2000L);
	
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//.ta-item:nth-of-type(2) -->CSS for the below xpath
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
		List<WebElement> items = driver.findElements(By.cssSelector("td.m-3 .title"));
		Boolean itemMatch = items.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(itemMatch);
		Thread.sleep(2000);
		driver.quit();
		
	}

}
