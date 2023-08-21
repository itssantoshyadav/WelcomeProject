package testNgTesting;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import CommonUtils.TakeScreenShotForFailedTestCases;

public class LoginTesting {

	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter extentSparkReporter;
	ExtentTest extentTest;
	SoftAssert softAssert;
	Properties prop;

	@BeforeTest
	public void objectCreationExtent() throws IOException {
		extentReports = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter("test-output/sparkReport.html");
		extentReports.attachReporter(extentSparkReporter);
		softAssert = new SoftAssert();
		String filePath = System.getProperty("user.dir")
				+ "//src//test//resources//ConfigurationFiles//ConfProperties.properties";
		prop = new Properties();
		FileInputStream readFile = new FileInputStream(filePath);
		prop.load(readFile);
	}

	@BeforeMethod
	public void beforeTestingethod() {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		System.out.println("BeforeSuit");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(dataProvider = "testdata1")
	public void loginPageTesting(String strUser, String strPass) {
		extentTest = extentReports.createTest("loginPageTesting");
		driver.get(prop.getProperty("url"));
		driver.findElement(By.id("username")).sendKeys(strUser);
		driver.findElement(By.id("password")).sendKeys(strPass);
		driver.findElement(By.id("password")).submit();
		Boolean msg = driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		softAssert.assertTrue(msg);
		softAssert.assertAll();

	}

	@DataProvider(name = "testdata2")
	public Object[][] TestDataFeedUserDefined() throws IOException, CsvValidationException {

		String filePathCsv = System.getProperty("user.dir") + "//src//test//resources//testData//login.csv";
		CSVReader reader = new CSVReader(new FileReader(filePathCsv));
		String array[];
		ArrayList<Object> arrayList = new ArrayList<Object>();
		while ((array = reader.readNext()) != null) {
			Object[] records = { array[0], array[1] };
			arrayList.add(records);
		}
		return arrayList.toArray(new Object[arrayList.size()][]);
	}

	@DataProvider(name = "testdata1")
	public Object[][] TestDataFeedPreDefined() {

		Object[][] twitterdata = new Object[2][2];

		twitterdata[0][0] = "username1@gmail.com";
		twitterdata[0][1] = "Password1";
		twitterdata[1][0] = "username2@gmail.com";
		twitterdata[1][1] = "Password2";
		return twitterdata;
	}

	@AfterMethod
	public void afterMethodTesting(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			extentTest.fail(result.getThrowable().getMessage());
			String filePath = TakeScreenShotForFailedTestCases.takeScreenShotTesting(driver);
			extentTest.addScreenCaptureFromPath(filePath);

		}
		driver.quit();
	}

	@AfterTest
	public void finishExtent() {
		extentReports.flush();
	}
}
