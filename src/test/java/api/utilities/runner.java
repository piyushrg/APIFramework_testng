package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class runner {


    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    String repName;

    /**
     * Always runs before the suite's.
     * Creates the extent report format and logs the status in it.Status are logged from the @tests
     * which are in maven tests
     * <li>Theme is set to be dark
     */
    @BeforeSuite(alwaysRun = true)
    public void Setup(){

        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-"+timestamp+".html";
        htmlReporter = new ExtentHtmlReporter(".\\Reports\\"+repName);

        htmlReporter.config().setDocumentTitle("Rest Assured Automation Project");
        htmlReporter.config().setReportName("Pet Store Extent Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Application","Pet Store API sample");
        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("user name",System.getProperty("user.name"));
        extent.setSystemInfo("user","Piyush");
    }

    /**
     * RUNS AFTER THE METHOD TO LOG RESULT
     * <Li> For test passed, extent report marked as GREEN
     * <Li> For test failed, extent report marked as RED
     * <Li> For test skipped, extent report marked as AMBER
     * <li> @param result
     */
    @AfterMethod(alwaysRun = true)
    public void ResultHandler(ITestResult result){

        if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
        }if(result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL , MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
        }if(result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP , MarkupHelper.createLabel(result.getName(), ExtentColor.AMBER));
        }
    }

    /**
     * Executes after suite
     * <li> Flushes the data in report
     */
    @AfterSuite
    public void rundown(){
        extent.flush();
    }

}
