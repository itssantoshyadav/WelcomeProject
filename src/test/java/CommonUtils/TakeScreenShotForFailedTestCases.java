package CommonUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShotForFailedTestCases {

	public static String takeScreenShotTesting(WebDriver driver) {

		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File file = screenShot.getScreenshotAs(OutputType.FILE);
		String fileName = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File dest = new File(fileName);
		try {
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
