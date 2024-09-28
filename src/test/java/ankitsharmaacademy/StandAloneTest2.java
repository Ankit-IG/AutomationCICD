package ankitsharmaacademy;

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

import ankitsharmaacademy.pageobjects.LandingPage;
import ankitsharmaacademy.pageobjects.ProductCatalogue;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class StandAloneTest2 {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage= new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplcation("ankitsharma@gmail.com", "Aniket@1");
		
		ProductCatalogue productCatalogue =  new ProductCatalogue(driver);
		List<WebElement>products=productCatalogue.getProductList();
		//driver.findElement(By.id("userEmail")).sendKeys("ankitsharma@gmail.com");
		//driver.findElement(By.id("userPassword")).sendKeys("Aniket@1");
		//driver.findElement(By.id("login")).click();
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// Listing out the all products
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		

	//	WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// wait for the cart adding message
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// clicking on cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSelection h3"));
		
		//Boolean match= cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		//Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
         
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Done");
	}

}
