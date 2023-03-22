package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.LandingPage;

public class BaseTest {
	
	public static WebDriver driver;
	protected LandingPage landingPage;

	
	public WebDriver initializeDriver() throws IOException
	{ 
		Properties prop=new Properties();
		
		FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties"));
		prop.load(fis);
		
		String browsername=System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		System.out.println(browsername);
		
		if(browsername.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver=new ChromeDriver(options);
		}	
		
		else if(browsername.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		else
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
//		Convert the Json content into string
		
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		// Convert String to list of HashMaps
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String, String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File source=ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\Screenshots\\ss_" + testCaseName + ".png";
		FileUtils.copyFile(source,new File(path));
		
		return path;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
	
	

}
