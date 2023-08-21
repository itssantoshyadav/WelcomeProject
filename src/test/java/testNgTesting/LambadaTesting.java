package testNgTesting;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class LambadaTesting {
	
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	ExtentReports extentReports;
	ExtentSparkReporter extentSparkReporter;
	ExtentTest extentTest;

	@BeforeTest
	public void objectCreationExtent() {
		extentReports = new ExtentReports() ;
		extentSparkReporter= new ExtentSparkReporter("test-output/sparkReport.html");
		extentReports.attachReporter(extentSparkReporter);
		
	}

	@BeforeClass
	@Parameters("browser")
	public void beforesuittesting(String browser) {
		if(browser.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
			
		}
		action = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		System.out.println("BeforeSuit");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(enabled = true, groups = "One")
	public void testingxpath() {
		extentReports.createTest("testingxpath");
		driver.get("https://www.lambdatest.com");
		action.moveToElement(
				driver.findElement(By.xpath("//div[@class='w-7/12 desktop:w-full header__menu__items']/div[3]")))
				.perform();
		driver.findElement(By.xpath("//a[@href=\"https://www.lambdatest.com/blog/\"]/div[2]/h3")).click();
		List<WebElement> elementList = driver.findElements(By.xpath("//ul[@id=\"menu-side-menu\"]/li"));
		System.out.println("The element Size is " + elementList.size());
		List<WebElement> elementList2 = driver
				.findElements(By.xpath("//ul[@id=\"menu-side-menu\"]//li[starts-with(@id,'menu-item-')]"));
		System.out.println("The element Size is " + elementList2.size());
	}

	@Test(enabled = true,groups = "Two")
	public void dynamicXpathTesting() {
		extentReports.createTest("dynamicXpathTesting");
		driver.get("https://accounts.lambdatest.com/login?_gl=1*ag1vc0*_gcl_au*MzcyOTg0OTczLjE2OTI1MTk0OTA.");
		driver.findElement(By.id("login-button")).click();
		String msg1 = driver.findElement(By.xpath("//p[contains(text(),'Email is a required')]")).getText();
		String msg2 = driver.findElement(By.xpath("//p[text()='Password is a required field']")).getText();
		System.out.println(msg1 + "" + msg2);
	}

	@Test(enabled = true ,groups = "Two")
	public void uploadingDcos() {
		extentReports.createTest("uploadingDcos");
		driver.get("https://www.globalsqa.com/samplepagetest/");
		driver.findElement(By.xpath("//input[@type=\"file\"]"))
				.sendKeys("C:\\Users\\Santosh yadav\\OneDrive\\Pictures\\Screenshots\\2021-01-21 (2).png");

	}

	@Test(enabled = true ,groups = "Three")
	public void listOfElement() {
		extentReports.createTest("listOfElement");
		driver.get("https://www.google.co.in/");
		driver.findElement(By.id("APjFqb")).sendKeys("Java tutorial");
		List<WebElement> list=driver.findElements(By.xpath("//ul[@class=\"G43f7e\"]/li"));
		for(WebElement str : list) {
			if((str.getText()).equalsIgnoreCase("Java Tutorial pdf")) {
				str.click();
				System.out.println(driver.getTitle());
				break;
				}
		}
		System.out.println("The size of list is "+list.size());

	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	@AfterTest
	public void finishExtent() {
		extentReports.flush();
	}
}