package rahulshettyacademy.SeleniumFrameworkDesign;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import TestComponents.BaseTest;
import pageobjects.CartPage;
import pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer = TestComponents.Retry.class)
	public void loginErrorTest() throws InterruptedException, IOException
	{
		
		 landingPage.loginApplication("ratest1@gmail.com", "Test@1234wrong");
		 String error=landingPage.getLoginErrorMessage();
		 assertEquals("Incorrect email  password.", error);
		
	}
	
	@Test
	public void productErrorValidationTest()
	{
		//Negative Test Case
		ProductCatalogue productCatalogue =landingPage.loginApplication("ratest1@gmail.com", "Test@1234");
		String productname="iphone 13 pro";
		
		productCatalogue.addProductToCart(productname);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		//CartPage
		Assert.assertFalse(cartPage.verifyProductDisplay("Baby Powder"));
		
		//This is just sample test
	}
	
	

}
