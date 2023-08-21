package testNgTesting;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class SeleniumEasy {
	WebDriver driver = new EdgeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	Actions action = new Actions(driver);

	@BeforeSuite
	public void beforesuittesting() {

		driver.manage().window().maximize();
		System.out.println("BeforeSuit");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(groups = "Two")
	public void SearchDataInTable() {
		driver.get("http://demo.seleniumeasy.com/table-sort-search-demo.html");
		String employeeName = driver.findElement(By.xpath("//td[contains(text(),'A. Ramos')]")).getText();
		String salary = driver.findElement(By.xpath("//td[contains(text(),'A. Ramos')]//following-sibling::td[5]"))
				.getText();
		System.out.println(employeeName + " " + salary);
	}

	@Test(dependsOnMethods = "SearchDataInTable" ,groups = "Two")
	public void staticMethodsOfSelenium() {
		String employeeName = driver
				.findElement(with(By.tagName("td")).toRightOf(By.xpath("//td[contains(text(),'New York')]"))).getText();
		System.out.println(employeeName);
	}

	@Test(dependsOnMethods = "staticMethodsOfSelenium",groups = "Two")
	public void findEmployees() {
		WebElement search=driver.findElement(By.cssSelector("input[type=\"search\"]"));
		search.sendKeys("San Francisco");
		search.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//td[contains(text(),'A. Cox')]"));
		List<WebElement> employeeList = driver.findElements(By.xpath("//tbody//tr/td[1]"));
		System.out.println("Employee which are working in San Francisco");
		for (WebElement employee : employeeList) {
			System.out.println(employee.getText());
		}
	}

	@AfterSuite
	public void teardown() throws InterruptedException {
		driver.quit();
	}

}
