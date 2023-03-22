package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyProductDisplay(String productname)
	{
		Boolean match=productTitles.stream().anyMatch(cp->cp.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage()
	{
		checkoutBtn.click();
		
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
	
	
	
}
