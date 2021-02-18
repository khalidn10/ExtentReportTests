// Package
package extent_reports_tests;

// Imports
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Extent Report Test Class
public class ExtentReportTest {

	// Declare ExtentReports object globally so can be used in multiple methods
	ExtentReports extentReport;
	
	// Declare driver object globally so can be used in multiple methods 
	WebDriver driver;
	
	// Assign ExtentReports object value and config
	@BeforeClass
	public void configureExtentReport()
	{
		System.out.println("Running before class method...");
		
		// Configure path in which extent report should be stored
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter extentReportConfig = new ExtentSparkReporter(reportPath);
		
		// Configure extent report name
		extentReportConfig.config().setReportName("Standalone Extent Report");
		
		// Configure extent report document name
		extentReportConfig.config().setDocumentTitle("Standalone Test Report");
		
		// Instantiate ExtentReports object
		extentReport = new ExtentReports();
		
		// Assign config to extent report
		extentReport.attachReporter(extentReportConfig);
		
		// Set 'Tester' property to extent report
		extentReport.setSystemInfo("Tester", "Khalid N");
		
		// Create Chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Load BBC website
		driver.get("https://bbc.co.uk");
	}
	
	// Extent report pass test
	@Test
	public void extentReportPassTest()
	{
		extentReport.createTest("Extent Report Pass Test");
		
		// Print page title
		System.out.println("Page title is: " + driver.getTitle());
		
		//driver.close();
	}
	
	// Extent report fail test
	@Test
	public void extentReportFailTest()
	{
		// Create ExtentTest object
		ExtentTest test = extentReport.createTest("Extent Report Fail Test");
		
		// Print something
		System.out.println("Printing something for extent report fail test");
		
		// Fails test in extent report
		test.fail("This test has been set to fail");
	}
	
	// Housekeeping to close browser and stop report at end of tests
	@AfterClass
	public void houseKeeping()
	{
		System.out.println("Running after class method...");
		
		// Stop report
		extentReport.flush();
		
		// Close browser
		driver.close();
	}
}
