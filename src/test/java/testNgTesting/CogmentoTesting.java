package testNgTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class CogmentoTesting {

	WebDriver driver;
	WebDriverWait wait;
	ExtentReports extentReports;
	ExtentSparkReporter extentSparkReporter;
	ExtentTest extentTest;
	SoftAssert softAssert;
	Properties prop;

	@BeforeTest
	public void initialize() {
		extentReports = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter("test-output/sparkReport.html");
		extentReports.attachReporter(extentSparkReporter);
		softAssert = new SoftAssert();

	}

	@BeforeMethod
	public void browserwithProperties() throws FileNotFoundException, IOException {
		String filePath = System.getProperty("user.dir")
				+ "//src//test//resources//ConfigurationFiles//CogmentoTesting.properties";
		prop = new Properties();
		prop.load(new FileInputStream(filePath));
		if ((prop.getProperty("browser")).equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if ((prop.getProperty("browser")).equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(0));
	}

	@Test(dataProvider = "Dataset1")
	public void loginTest(String strUser, String strPass) {
		extentTest = extentReports.createTest("loginTest cogmento");
		driver.get(prop.getProperty("url"));
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(strUser);
		driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys(strPass);
		driver.findElement(By.cssSelector("div.ui.fluid.large.blue.submit.button")).click();
		String msg = driver.findElement(By.cssSelector("span.user-display")).getText();
		System.out.println(msg);
		softAssert.assertEquals(msg, "santosh yadav1");
		softAssert.assertAll();

	}

	@DataProvider(name = "Dataset1")
	public Object[] dataProviderTesting() throws CsvValidationException, IOException {
		String filePathCsv = System.getProperty("user.dir")
				+ "//src//test//resources//testData//CogmentoTestingData.csv";
		CSVReader csvreader = new CSVReader(new FileReader(filePathCsv));
		String array[];
		ArrayList<Object> arrayList = new ArrayList<Object>();
		while ((array = csvreader.readNext()) != null) {
			Object[] object = { array[0], array[1] };
			arrayList.add(object);
		}
		return arrayList.toArray(new Object[arrayList.size()][]);
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
	public void teardown() {
		extentReports.flush();
	}
}
