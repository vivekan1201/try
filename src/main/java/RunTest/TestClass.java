package RunTest;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AutomationScript.Utils.ExtentReportManager;
import com.AutomationScript.sample.AutomateFindHospitals;
import com.AutomationScript.sample.Automatetopcities;
import com.AutomationScript.sample.BaseUI;
import com.AutomationScript.sample.corperateWellness;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestClass  {
	
	//ExtentReports report=ExtentReportManager.getReportInstance();
	//public static ExtentTest logger;
	public static ExtentReports report;
	public static ExtentTest logger;
	static WebDriver driver;
	
  
	  
	@BeforeClass
	  public void open(String browser) throws Exception 
		{
		  	BaseUI ba=new BaseUI();
			ba.openBrowserAndNavigateToPracto(browser);
		
		}
	  
	  
	
	  
	  @Parameters("browser")
	  @Test(priority=1,groups = {"smoke"})
	  public void getHospitalNames(String browser) throws Exception
	  {	
		  //FileIO.readExcelData()
		  open(browser);
		  report = ExtentReport.getReportInstance();
		  logger=report.createTest("getHospitalNames");
		  AutomateFindHospitals ah=new AutomateFindHospitals();
		  logger.log(Status.INFO, "Searching for hospitals");
		  ah.SearchHospitals();
		  logger.log(Status.INFO, "Click on 24x7 CheckBox");
		  ah.Hospitalsopen();
		  logger.log(Status.INFO, "Apply has parking filter");
		  ah.applyfilter();
		  logger.log(Status.INFO, "Get List of hospitals");
		  ah.ListHospitals();
		  logger.log(Status.PASS, "Test Executed Successfully");
		 
	  }
	  
	  @AfterTest
	  public void endReport()
	  {
		  report.flush();
		  driver.quit();
	  }
	  
	  @Test(priority = 3 , dependsOnMethods = {"getHospitalNames"}, groups = {"smoke"})
	  public void Diagnosticpage() throws IOException, InterruptedException
	  {
		  Automatetopcities ct= AutomateFindHospitals.nextPage();
		  logger=report.createTest("Diagnostic Page");
		  logger.log(Status.INFO, "Click diagnostics button");
		  ct.diagnostic();
		  logger.log(Status.INFO, "Get List of Cities");
		  ct.ListCities();
		  logger.log(Status.INFO, "Navigating to the previous page");
		  ct.back();
		  logger.log(Status.PASS, "Test Executed Successfully");
	  }
  
	  @Test(priority = 2 , dependsOnMethods = {"Diagnosticpage"}, groups = {"regression"})
	  public void CopWelleness() throws IOException, InterruptedException
	  {
		   logger=report.createTest("CopWellness");
	        corperateWellness corporatepage = Automatetopcities.nextPage();

	        logger.log(Status.INFO, "Click Provider Button");
	        corporatepage.clickProvider();

	        logger.log(Status.INFO, "Click CorporateWellness Button");
	        corporatepage.clickCorporateWellness();

	        logger.log(Status.INFO, "Enter data to the fields");
	        corporatepage.enterData();
	        
	        logger.log(Status.PASS, "Test Executed Successfully");
			 close();
	        
	        
//	        logger.log(Status.INFO, "Captured alert message and accept the alert");
//	        corporatepage.alertBox();
	        
		  
//		  ExtentTest logger=report.createTest("CopWellness");
//		  AutomateCopWellness cw= Automatetopcities.nextPage();
//		  logger.log(Status.INFO, "Click provider button");
//		  cw.provider();
//		  logger.log(Status.INFO, "Click Corporate Providers button");
//		  cw.copwell();
////		  
//		  AutomateCopWellness1 cw1= AutomateCopWellness.nextPage();
//		  //cw1.newwindow();
//		  logger.log(Status.INFO, "Entering provided details");
//		  cw1.enterdetails();
//		  logger.log(Status.INFO, "Captured alert message and accept the alert");
//		  cw1.alertBox();
//		  logger.log(Status.INFO, "Captured the screeshot");
//		  cw1.screenshoot();
//		  logger.log(Status.PASS, "Test Executed Successfully");
//		  close();
//		  
	  }
	
	  @AfterClass
	  public void close()
	  {
		    report.flush();
			BaseUI ba=new BaseUI();
			ba.closeBrowser();
	  }
}
