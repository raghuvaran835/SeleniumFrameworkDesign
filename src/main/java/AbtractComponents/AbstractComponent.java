package AbtractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.CartPage;
import pageobjects.OrderPage;

public class AbstractComponent {

	private WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartIcon;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersHeader;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public CartPage goToCartPage() {
		cartIcon.click();

		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrderPage goToOrdersPage() {
		ordersHeader.click();

		OrderPage orderPage = new OrderPage(driver);
		return orderPage;

	}

	public void waitForElementToAppear(By locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToAppear(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void waitForElementToDisappear(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementToDisappear(WebElement ele) {

		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
