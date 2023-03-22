package pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-results span")
	List<WebElement> suggessionList;

	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;

	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void select_country(String countryname) throws InterruptedException {

		Actions a=new Actions(driver);
		a.sendKeys(country,countryname.substring(0, 3)).build().perform();
		Thread.sleep(1000);
		WebElement co = suggessionList.stream().filter(s -> s.getText().equalsIgnoreCase(countryname)).findFirst()
				.orElse(null);
		co.click();
	}
	
	public OrderConfirmationPage submitOrder()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", placeOrderBtn);
		
		OrderConfirmationPage orderConfirmationPage=new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}
	

}
