package testNgTesting;

import java.util.Optional;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.emulation.Emulation;
import org.testng.annotations.Test;

public class DevToolsTesting {

	@Test
	public void emulateDeviceWithSend() throws InterruptedException {

		ChromeDriver driver = new ChromeDriver(); // Create driver instance

		DevTools devTool = driver.getDevTools(); // Create devTool instance

		devTool.createSession();

		devTool.send(Emulation.setDeviceMetricsOverride(

				500,

				600,

				50,

				true,

				Optional.empty(),

				Optional.empty(),

				Optional.empty(),

				Optional.empty(),

				Optional.empty(),

				Optional.empty(),

				Optional.empty(),

				Optional.empty(),

				Optional.empty()

		));

		driver.get("https://courses.rahulshettyacademy.com/courses");

		Thread.sleep(10000);

	}

}
