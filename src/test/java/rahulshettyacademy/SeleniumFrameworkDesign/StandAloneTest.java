package rahulshettyacademy.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	@Test
	public void e2eTest() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("ratest1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
		driver.findElement(By.id("login")).click();
		
		
		List<WebElement> products=driver.findElements(By.cssSelector(".card-body"));
		//driver.close();
		
		System.out.println(products.size());
		String productname="iphone 13 pro";
		
//		for(WebElement p:products)
//		{
//			System.out.println(p.findElement(By.cssSelector("b")).getText());
//		}
		
		WebElement product= products.stream().filter(p-> 
		p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)
		).findFirst().orElse(null);
		
		product.findElement(By.cssSelector("button:last-of-type")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[role='alertdialog']"))));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[role='alertdialog']"))));
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match=cartProducts.stream().anyMatch(cp->cp.getText().equalsIgnoreCase(productname));
		
		Assert.assertTrue(match);
		
//		driver.findElement(By.xpath("//div[@class='infoWrap']/div/h3[contains(text(),'iphone')]//parent::div//following-sibling::div[contains(@class,'removeWrap')]/button[contains(text(),'Buy Now')]"))
//				.click();
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"ind").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		List<WebElement> suggesionList=driver.findElements(By.cssSelector(".ta-results span"));
		
		WebElement india=suggesionList.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		india.click();
		Thread.sleep(1000);
		WebElement place_order=driver.findElement(By.cssSelector(".action__submit"));
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", place_order);
		
		Thread.sleep(2000);
		driver.quit();
		
	}

}
