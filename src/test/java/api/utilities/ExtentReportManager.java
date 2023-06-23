package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {

    public  static ExtentHtmlReporter sparkReport;
    public  static ExtentReports extent;
    public  static ExtentTest test;

    String repName;

    public void onStart(ITestContext context){

        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-"+timestamp+".html";
        sparkReport = new ExtentHtmlReporter(".\\Reports\\"+repName);

        sparkReport.config().setDocumentTitle("Rest Assured Automation Project");
        sparkReport.config().setReportName("Pet Store API - Swagger sample");
        sparkReport.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReport);

        extent.setSystemInfo("Application","Pet Store API sample");
        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("user name",System.getProperty("user.name"));
        extent.setSystemInfo("user","Piyush");
    }

    public void onTestSuccess(ITestResult result){

        if(result.getStatus()==ITestResult.SUCCESS){
            test.log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
        }
    }

    public void onTestFailure(ITestResult result){

        if(result.getStatus()==ITestResult.FAILURE){
            test.log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
            test.log(Status.FAIL,result.getThrowable().getMessage());
        }
    }

    public void onTestSkipped(ITestResult result){

        if(result.getStatus()==ITestResult.SKIP){
            test.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.AMBER));
            test.log(Status.SKIP,result.getThrowable().getMessage());
        }
    }

    public void onFinish(ITestContext context){

        extent.flush();
    }

}
