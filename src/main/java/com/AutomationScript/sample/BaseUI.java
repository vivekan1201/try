package com.AutomationScript.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import RunTest.TestClass;

public class BaseUI {

	public static Properties prop;
	static WebDriver driver = null;
	public static String data[];
	final String baseUrl = "https://www.practo.com/";

	// Open the Practo website using specified browser
	public void openBrowserAndNavigateToPracto(String browser) {
		System.out.println("Enter Browser Name");
		Scanner sc = new Scanner(System.in);
		String browser1 = "firefox";
		if (browser1.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver","O:\\Sample\\msedgedriver.exe");
			driver = new EdgeDriver();
			if (prop == null) {
				prop = new Properties();
				try {
					FileInputStream file = new FileInputStream(System.getProperty("user.dir")
							+ "\\src\\main\\resources\\ObjectRepository\\projectConfig.properties");
					prop.load(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			driver.manage().window().maximize();
			driver.get(baseUrl);
			// Verify if correct page is loaded
			String pageTitle = driver.getTitle();
			Assert.assertEquals(pageTitle,
					"Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		}
		// Automate using firefox browser
		if (browser1.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "O:\\Sample\\geckodriver.exe");
			driver = new FirefoxDriver();
			if (prop == null) {
				prop = new Properties();
				try {
					FileInputStream file = new FileInputStream(System.getProperty("user.dir")
							+ "\\src\\main\\resources\\ObjectRepository\\projectConfig.properties");
					prop.load(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			driver.manage().window().maximize();
			driver.get(baseUrl);
			// Verify if correct page is loaded
			String pageTitle = driver.getTitle();
			Assert.assertEquals(pageTitle,
					"Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
		}
	}

	// Enter text into a field
	public void enterText(String nameKey, String text) {
		getElement(nameKey).sendKeys(text);
	}

	// Click a web element
	public void clickElement(String locatorKey) {

		getElement(locatorKey).click();

	}

	// Identify and return webelement on a page

	public WebElement getElement(String locatorKey) {
		WebElement element = null;
		

		if (locatorKey.endsWith("_xpath")) {
			element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			highLightElement(driver,element);
		} else if (locatorKey.endsWith("_partialLinkText")) {
			highLightElement(driver,element);
			element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_name")) {
			highLightElement(driver,element);
			element = driver.findElement(By.name(prop.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_id")) {
			highLightElement(driver,element);
			element = driver.findElement(By.id(prop.getProperty(locatorKey)));
		} else if (locatorKey.endsWith("_CssSelector")) {
			highLightElement(driver,element);
			element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
		}
		return element;

	}

	public List<WebElement> getElements(String locatorKey) {
		List<WebElement> elements = null;
		if (locatorKey.endsWith("_xpath")) {
			elements = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
		}
		return elements;
	}
	public static boolean isAlertPresent(){
        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (Exception e) {
            foundAlert = false;
        }
        return foundAlert;
    }
public static void ResultScreenShot(String directory) throws IOException{
		
		ExtentTest logger1 = TestClass.logger;
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		FileHandler.copy(screenshot, new File(System.getProperty("user.dir") + "\\screenshot\\"+directory));
		logger1.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\screenshot\\"+directory);
		
		
	}
public static void highLightElement(WebDriver driver, WebElement element)
{
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	try
	{
		Thread.sleep(500);
	}
	catch (InterruptedException e) {

		System.out.println(e.getMessage());
	}

	js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

}

	// Close all instances of the browser
	public void closeBrowser() {
		driver.quit();
	}

}
