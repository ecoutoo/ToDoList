package test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import utils.ScreenShot;
import pages.HomePage;
import pages.ListPageButtons;
import pages.ListPageForms;

public class CreateListTest {
	
	private static Logger LOGGER = Logger.getGlobal();
    private static RemoteWebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;
	
    @BeforeAll
    public static void init() {
    	
		extent = new ExtentReports("src/test/resources/reports/report1.html", true);
		test = extent.startTest("ExtentDemo");
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/chromedriver.exe");
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setHeadless(false);
		driver = new ChromeDriver(cOptions);
    	cOptions.setCapability("profile.default_content_setting_values.cookies", 2);
    	cOptions.setCapability("network.cookie.cookieBehavior", 2);
    	cOptions.setCapability("profile.block_third_party_cookies", true);
		driver.manage().window().setSize(new Dimension(1366, 768));
    	
    }
    
    @BeforeEach
    public void setup() {      
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		try {
			driver.get(HomePage.URL);
		} catch (TimeoutException e) {
			System.out.println("Page: " + HomePage.URL + " did not load within 30 seconds!");
		}
    }
    
    @AfterAll
    public static void finalTearDown() {
		LOGGER.warning("Closing webdriver instance!");
		driver.quit();
		LOGGER.info("Webdriver closed successfully!");
		extent.endTest(test);
		extent.flush();
		extent.close();
    }
    
 	@Test
 	public void newTest() throws InterruptedException {
 		
		LOGGER.warning("Connecting to To Do List....");
		new WebDriverWait(driver, 5);

		String tNum = "1";
		String tText = "test";
	
		ListPageButtons buttons = PageFactory.initElements(driver, ListPageButtons.class);
		ListPageForms forms = PageFactory.initElements(driver, ListPageForms.class);

		forms.NumInfo(tNum);
		forms.TextInfo(tText);
		buttons.clickReadAll();

		driver.getPageSource().contains("QUERY SUCCESS");
 		
 	}

}
