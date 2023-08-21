package testNgTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNgClass2 {
	
  @Test(groups = "Four")
  public void fudu() {
	  
	  WebDriver driver = new EdgeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  driver.get("https://www.google.co.in/");
	  WebElement element=driver.findElement(By.id("APjFqb"));
	  element.sendKeys("java");
	  element.sendKeys(Keys.ENTER);
	  Assert.assertEquals(driver.getTitle(), "java - Google Search");
	  driver.quit();
  }
}
