package com.AutomationScript.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {

	//public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	
	public static ExtentReports getReportInstance()
	{
		if(report==null)
		{
			ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter("C:\\Users\\2068130\\Downloads\\01_FindingHospitals (1)\\HospitalProject\\HospitalProject\\src\\main\\java\\testreport.html");
			report=new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			
			htmlReporter.config().setDocumentTitle("Practo Site Automation Result");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			
			
		}
		return report;
	}
}
