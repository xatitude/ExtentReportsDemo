package extent;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ExtentDemo {
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	@Test
	public void TestCase1() {
		driver.get("http://www.flipkart.com");
		if(driver.findElement(By.xpath("//img[@title='Flipkart']"))!=null) {
			test.log(LogStatus.PASS, "Flipkart logo is present");
		}else {
			test.log(LogStatus.FAIL, "Flipkart logo is NOT present");
		}
	}
	@Test
	public void TestCase2() {
		test.log(LogStatus.FAIL, "Flipkart logo is NOT present");
	}
	@BeforeMethod
	public void beforeMethod(Method method) {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Akshat\\Downloads\\NewDrivers\\geckodriver.exe");
		test = report.startTest((this.getClass().getSimpleName() + ":: " + method.getName()), method.getName());
		test.assignAuthor("QA Xperts");
		test.assignCategory("RegressionTests --INTEGRATION");
		driver = new FirefoxDriver();
		test.log(LogStatus.PASS, "Firefox browser launched successfully");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
		test.log(LogStatus.PASS, "Browser closed successfully");
		report.endTest(test);
	}

	@BeforeSuite
	public void beforeSuite() {
		report =new ExtentReports ("MyExtentReport.html", false);
		report.loadConfig(new File("C:\\Users\\Akshat\\workspace\\Selenium_WSX\\seleniumdemoproject\\extent-config.xml"));
	}

	@AfterSuite
	public void afterSuite() {
		report.flush();
		report.close();

	}

}
