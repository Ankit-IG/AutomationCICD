package ankitsharmaacademy;

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

import ankitsharmaacademy.TestComponents.BaseTest;
import ankitsharmaacademy.pageobjects.CartPage;
import ankitsharmaacademy.pageobjects.CheckoutPage;
import ankitsharmaacademy.pageobjects.ConfimationPage;
import ankitsharmaacademy.pageobjects.LandingPage;
import ankitsharmaacademy.pageobjects.OrderPage;
import ankitsharmaacademy.pageobjects.ProductCatalogue;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	// private static final boolean Match = false;
	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// (HashMap<String, String> input)

		// TODO Auto-generated method stub

		// LandingPage landingPage= launchApplication();
		// WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.manage().window().maximize();
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// LandingPage landingPage = new LandingPage(driver);
		// landingPage.goTo();

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		// Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		checkoutPage.submitOrder();
		ConfimationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistorytest() {
		// "ZARA COAT 3"
		ProductCatalogue productCatalogue = landingPage.loginApplication("ankitsharma@gmail.com", "Aniket@1");
		OrderPage orderpage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));

	}
	

	
	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\ankitsharmaacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	// return new Object[][] {{"ankitsharma@gmail.com", "Aniket@1"},
	// {"ankitsharma1@gmail.com", "Aniket@1"}};

	// HashMap<String, String> map= new HashMap<String, String>();
	// map.put("email", "ankitsharma@gmail.com");
	// map.put("password", "Aniket@1");
	//
	// HashMap<String, String> map1= new HashMap<String, String>();
	// map1.put("email", "ankitsharma1@gmail.com");
	// map1.put("password", "Aniket@1");

}
