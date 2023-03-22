package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
//	WebElement userEmail=driver.findElement(By.id("userEmail")); //for simplifying this syntax pageFactory is coming into the picture
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginError;

	public ProductCatalogue loginApplication(String username,String password)
	{
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		loginBtn.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getLoginErrorMessage()
	{
		waitForElementToAppear(loginError);
		return loginError.getText();
		
	}
}
