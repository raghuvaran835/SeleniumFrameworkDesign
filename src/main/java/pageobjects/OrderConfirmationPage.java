package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;

	public OrderConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
	
	
}
