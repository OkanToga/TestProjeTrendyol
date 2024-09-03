package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;



public class ExtentReportManager {

    public static ExtentSparkReporter sparkReporter;

    public static ExtentReports extent;

    public static ExtentTest extentTest;

    public static void beforeAll() throws IOException {
        WebDriverManager.chromedriver().setup();
        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"test-output/testReport.html");
        extent.attachReporter(sparkReporter);

        sparkReporter.config().setOfflineMode(true);
        sparkReporter.config().setDocumentTitle("Simple Automation Report");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");

    }

    public static void flushReports(){
        extent.flush();
    }

    /*public static String captureScreenshot(WebDriver driver) throws IOException{
        String FileSeperator = System.getProperty("file.separator");
        String Extrent_report_path = "."+FileSeperator+"Reports";
        String ScreenshotPath = Extrent_report_path+FileSeperator+"Screenshots";
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = "Screenshot"+Math.random()+".png";
        String screenshotPath = ScreenshotPath+FileSeperator+screenshotName;
        FileUtils.copyFile(src,new File(screenshotPath));
        return "."+FileSeperator+"Screenshots"+FileSeperator+screenshotName;

    }*/
}
