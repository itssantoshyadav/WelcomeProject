package testNgTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class NewTest {

	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter extentSparkReporter;
	ExtentTest extentTest;
	SoftAssert softAssert;
	

	@BeforeTest
	public void objectCreationExtent() {
		extentReports = new ExtentReports() ;
		extentSparkReporter= new ExtentSparkReporter("test-output/sparkReport.html");
		extentReports.attachReporter(extentSparkReporter);
		driver =new EdgeDriver();
		softAssert= new SoftAssert();
		
	}
	@BeforeMethod
	public void beforeTestingethod() {
		driver.manage().window().maximize();
		System.out.println("BeforeSuit");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(groups = "One")
	public void test1() {
		extentTest=extentReports.createTest("test1");
		driver.get("https://www.google.co.in/");
		WebElement element = driver.findElement(By.id("APjFqb"));
		element.sendKeys("q");
		element.sendKeys(Keys.ENTER);
		softAssert.assertEquals(driver.getTitle(), "q - Google Search");

	}

	@Test(groups = "One")
	public void test12() {
		extentTest=extentReports.createTest("test12");
		driver.get("https://www.google.co.in/");
		WebElement element = driver.findElement(By.id("APjFqb"));
		element.sendKeys("Java");
		element.sendKeys(Keys.ENTER);
		softAssert.assertEquals(driver.getTitle(), "Java - Google Search");

	}
	
	@Test(groups = "One")
	public void sampleTestingMethod3() {
		extentTest=extentReports.createTest("sampleTestingMethod3");
		driver.get("https://www.google.co.in/");
		WebElement element = driver.findElement(By.id("APjFqb"));
		element.sendKeys("santosh");
		element.sendKeys(Keys.ENTER);
		softAssert.assertEquals(driver.getTitle(), "santosh - Google Search");

	}
	
	@Test(groups = "One" )
	public void sampleTestingMethod4() {
		extentTest=extentReports.createTest("sampleTestingMethod4");
		driver.get("https://www.google.co.in/");
		WebElement element = driver.findElement(By.id("APjFqb"));
		element.sendKeys("pure");
		element.sendKeys(Keys.ENTER);
		softAssert.assertEquals(driver.getTitle(), "pure23243 - Google Search");
		softAssert.assertAll();

	}

	@AfterMethod
	public void afterMethodTesting(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus()) {
			extentTest.fail(result.getThrowable().getMessage());
			
		}
	}
	
	@AfterTest
	public void finishExtent() {
		driver.quit();
		extentReports.flush();
	}
}
