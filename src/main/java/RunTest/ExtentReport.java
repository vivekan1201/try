package RunTest;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
public static ExtentReports report;
	
	public static ExtentReports getReportInstance()
	{
		if(report==null)
		{
			ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter("test.html");
			report=new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setDocumentTitle("Practo Site Automation Result");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			
			
		}
		return report;
	}

}
