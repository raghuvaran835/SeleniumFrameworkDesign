package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter setupReport=new ExtentSparkReporter(path);
		
		setupReport.config().setDocumentTitle("Test Results");
		setupReport.config().setReportName("Web Automation Results");
		
		ExtentReports extentReport=new ExtentReports();
		extentReport.attachReporter(setupReport);
		extentReport.setSystemInfo("Tester", "Raghuvaran");
		extentReport.setSystemInfo("Environment", "Production");
		
		return extentReport;
		
	}
}
