package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbtractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(css=".card-body")
	List<WebElement> products;
	
	@FindBy(css="button:last-of-type")
	WebElement addToCart;
	
	@FindBy(css="div[role='alertdialog']")
	WebElement toastMessage;
	
	By productsBy= By.cssSelector(".card-body");
	
	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productname)
	{
		WebElement prod=getProductList().stream().filter(p-> 
		p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)
		).findFirst().orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String productname)
	{
		getProductByName(productname).findElement(By.cssSelector("button:last-of-type")).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(toastMessage);
	}
	
	
	
}
