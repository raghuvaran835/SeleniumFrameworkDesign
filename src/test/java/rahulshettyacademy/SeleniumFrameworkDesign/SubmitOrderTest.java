package rahulshettyacademy.SeleniumFrameworkDesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.OrderConfirmationPage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productname = "iphone 13 pro";

	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void e2eTest(HashMap<String, String> data) throws InterruptedException, IOException {

//		LandingPage landingPage=launchApplication();
		// LandingPage
//		LandingPage landingPage=new LandingPage(driver);
//		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication(data.get("email"),data.get("password"));

		// ProductPage
//		List<WebElement> pc=productCatalogue.getProductList();

		String country = "india";
		String productname=data.get("productname");
//		WebElement product= productCatalogue.getProductByName(productname);
		productCatalogue.addProductToCart(productname);
		CartPage cartPage = productCatalogue.goToCartPage();

		// CartPage
		Assert.assertTrue(cartPage.verifyProductDisplay(productname));
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();

		// checoutPage

		checkoutPage.select_country(country);
		OrderConfirmationPage confirmPage = checkoutPage.submitOrder();

		// confirmationPage
		String confirmationMessage = confirmPage.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");

		Thread.sleep(2000);

	}

	@Test(dependsOnMethods = {"e2eTest"})
	public void orderHistoryTest() throws IOException {
		
		ProductCatalogue productCatalogue=landingPage.loginApplication("ratest1@gmail.com", "Test@1234");
		
		OrderPage op=productCatalogue.goToOrdersPage();
		
		assertTrue(op.verifyOrderDisplay(productname));
		

	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		String filePath=System.getProperty("user.dir")+"\\data-source\\purchaseOrder.json";
		List<HashMap<String, String>> data= getJsonDataToMap(filePath);
		
		return new Object[][] { {data.get(0)},{data.get(1)}};
		
		
//		HashMap<Object,Object> map=new HashMap<Object,Object>();
//		map.put("email", "ratest1@gmail.com");
//		map.put("password","Test@1234");
//		map.put("productname","ADIDAS ORIGINAL");
//		
//		HashMap<Object,Object> map1=new HashMap<Object,Object>();
//		map1.put("email", "ratest1@gmail.com");
//		map1.put("password","Test@1234");
//		map1.put("productname","ZARA COAT 3");
		
//		Object[][] data= { 
//				{map},
//				{map1}
//		};
		
		
//		Object[][] data= {
//				{"ratest1@gmail.com","Test@1234","iphone 13 pro"},
//				{"ratest1@gmail.com","Test@1234","ADIDAS ORIGINAL"},
//				{"ratest1@gmail.com","Test@1234","ZARA COAT 3"}
//				
//		};
		
//		return data;
	}

}
