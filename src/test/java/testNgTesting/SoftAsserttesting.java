package testNgTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserttesting {

	WebDriver driver;
	SoftAssert softAssert;

	@BeforeSuite

	public void beforeTestingethod() {
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		softAssert= new SoftAssert();
	}

	@Test(groups = "One")
	public void sampleTestingMethodSoft() {
		driver.get("https://www.google.co.in/");
		WebElement element = driver.findElement(By.id("APjFqb"));
		element.sendKeys("q");
		element.sendKeys(Keys.ENTER);
		softAssert.assertEquals(driver.getTitle(), "q - Google Search");
		driver.get("https://www.google.co.in/");
		WebElement element2 = driver.findElement(By.id("APjFqb"));
		element2.sendKeys("Java");
		element2.sendKeys(Keys.ENTER);
		softAssert.assertEquals(driver.getTitle(), "Java - Google Search");
		softAssert.assertAll();

	}

	@AfterSuite
	public void afterMethodTesting() {
		driver.quit();
	}
}
