package ankitsharmaacademy;

import java.io.IOException;

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
import org.testng.annotations.Test;

import ankitsharmaacademy.TestComponents.BaseTest;
import ankitsharmaacademy.TestComponents.Retry;
import ankitsharmaacademy.pageobjects.CartPage;
import ankitsharmaacademy.pageobjects.CheckoutPage;
import ankitsharmaacademy.pageobjects.ConfimationPage;
import ankitsharmaacademy.pageobjects.LandingPage;
import ankitsharmaacademy.pageobjects.ProductCatalogue;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class ErrorValidationsTest extends BaseTest {

	// private static final boolean Match = false;
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {

		String productName = "ZARA COAT 3";
	

		landingPage.loginApplication("ankitsharma@gmail.com", "niket@1");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	String productName = "ZARA COAT 3";
	ProductCatalogue productCatalogue = landingPage.loginApplication("ankitsharma1@gmail.com", "Aniket@1");
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.addProductToCart(productName);
	CartPage cartPage = productCatalogue.goToCartPage();

	Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
}
}
