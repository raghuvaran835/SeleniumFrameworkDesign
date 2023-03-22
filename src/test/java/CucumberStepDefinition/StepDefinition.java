package CucumberStepDefinition;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.LandingPage;
import pageobjects.OrderConfirmationPage;
import pageobjects.ProductCatalogue;

public class StepDefinition extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public OrderConfirmationPage confirmPage;
	
	// Tidy Gherkin Plugin is used to generate the stepdefinition methods
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   landingPage=launchApplication();
	}

	@Given("^Logged in with Username (.+) and Pasword (.+)$")
	public void logged_in_with_username_and_pasword(String username,String password) {
	    // Write code here that turns the phrase above into concrete actions
		productCatalogue = landingPage.loginApplication(username,password);
	}

	@When("^I add the product (.+) to Cart$")
	public void i_add_the_product_to_cart(String productname) {
	    // Write code here that turns the phrase above into concrete actions
		productCatalogue.addProductToCart(productname);
	}

	@When("^Checkout the (.+) and Submit the Order$")
	public void checkout_and_submit_the_order(String productname) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		String country="india";
		cartPage = productCatalogue.goToCartPage();

		// CartPage
		Assert.assertTrue(cartPage.verifyProductDisplay(productname));
		checkoutPage = cartPage.goToCheckoutPage();

		// checoutPage

		checkoutPage.select_country(country);
		confirmPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on confirmation Page.")
	public void message_is_displayed_on_confirmation_page(String string) {
	    // Write code here that turns the phrase above into concrete actions
		String confirmationMessage = confirmPage.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, string);
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
	    // Write code here that turns the phrase above into concrete actions
		String error=landingPage.getLoginErrorMessage();
		assertEquals("Incorrect email or password.", string);
	}
}
