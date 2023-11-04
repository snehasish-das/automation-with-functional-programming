package aalt.core.external.lib;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportsLib {
    private static ExtentReports extentReports;
    private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

    public static ExtentReports initReport(String reportName){
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/extent-reports/"+reportName+".html");
        extentSparkReporter.config().setReportName(reportName+" Report");
        extentSparkReporter.config().setDocumentTitle(reportName);
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("OS",System.getProperty("os.version"));
        extentReports.setSystemInfo("Java",System.getProperty("java.version"));
        extentReports.setSystemInfo("Selenium",System.getProperty("selenium.version"));
        extentReports.setSystemInfo("Testng",System.getProperty("testng.version"));
        extentReports.setSystemInfo("Chrome",System.getProperty("chrome.version"));
        extentReports.setSystemInfo("Firefox",System.getProperty("firefox.version"));
        return extentReports;
    }

    public static ExtentTest getExtentTest() {
        return extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    public static void endTest() {
        extentReports.flush();
    }

    public static ExtentTest startTest(String testDescription) {
        String testName = null;
        for(String val : testDescription.split(",")){
            if(val.contains("TestName")){
                testName = val.split("=")[1];
            }
        }
        ExtentTest test = extentReports.createTest(testName);
        extentTestMap.put((int) (Thread.currentThread().getId()), test);
        return test;
    }
}
