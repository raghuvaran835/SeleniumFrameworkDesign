package AutoSortAndPageinationConcepts;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

public class AutoSortAndPageniation{

	WebDriver driver;

	@BeforeMethod
	public void launch() throws IOException
	{
		driver = new BaseTest().initializeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

	}
	
	@Test
	public void autoSortAndPaginationDemo() throws IOException {

		
		// click on column

		driver.findElement(By.xpath("//tr/th[1]")).click();

		// capture all webelements into list

		List<WebElement> elementsList = driver.findElements(By.xpath("//tr/td[1]"));

		// capture text of all webelements into new(original) list

		List<String> originalList = elementsList.stream().map(s -> s.getText()).collect(Collectors.toList());

		// sort on the original list of step 3 -> sorted list

		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());

		// compare original list vs sorted list

		Assert.assertTrue(originalList.equals(sortedList));

		List<String> price;

		// scan the name column with getText ->Beans->print the price of the Rice

		do {
			List<WebElement> rows = driver.findElements(By.xpath("//tr/td[1]"));

			price = rows.stream().filter(s -> s.getText().contains("Rice"))

					.map(s -> getPriceVeggie(s)).collect(Collectors.toList());

			price.forEach(a -> System.out.println(a));

			if (price.size() < 1) {
				driver.findElement(By.cssSelector("[aria-label='Next']")).click();
			}
		} while (price.size() < 1);

	}

	@Test
	public void filterFunctionalityTest() throws IOException {
		
		driver.findElement(By.id("search-field")).sendKeys("Rice");

		
		List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));
		
		// 1 results

		List<WebElement> filteredList = veggies.stream().filter(veggie -> veggie.getText().contains("Rice")).
				collect(Collectors.toList());

		// 1 result
		Assert.assertEquals(veggies.size(), filteredList.size());
	}

	private static String getPriceVeggie(WebElement s) {

		// TODO Auto-generated method stub

		String pricevalue = s.findElement(By.xpath("following-sibling::td[1]")).getText();

		return pricevalue;

	}
	
	@AfterMethod
	public void closeBrwoser() throws InterruptedException
	{
		Thread.sleep(1000);
		driver.quit();
	}

}
