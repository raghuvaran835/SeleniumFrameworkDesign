package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;

	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> productNames;
	
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyOrderDisplay(String productname)
	{
		Boolean match=productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	
	
	
	
}
