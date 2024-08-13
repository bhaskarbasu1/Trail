package Listner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class CustomListners implements ITestListener {

    public static WebDriver mDriver;

    public CustomListners() {

    }
    public CustomListners(WebDriver driver) {
        mDriver = driver;

        PageFactory.initElements(CustomListners.mDriver, this);
    }
    public void onStart(ITestContext arg) {
        System.out.println("Starts test execution............" + arg.getName());
    }


    public void onFinish(ITestContext arg) {
        System.out.println("Finish test execution: " + arg.getName());

    }

    public void onTestStart(ITestResult arg) {
        System.out.println("Starts test........." + arg.getName());
    }


    public void onTestSkipped(ITestResult arg) {
        System.out.println("skipped tests........" + arg.getName());
    }

    public void onTestSuccess(ITestResult arg) {
        System.out.println("passed test............" + arg.getName());
    }


    public void onTestFailure(ITestResult arg) {
        System.out.println("Failed test.............." + arg.getName());
        if (mDriver != null) {
            try {
                takeScreenshot();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("WebDriver instance is not initialized in your project.");
        }
    }

    void takeScreenshot() throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) Listner.GalvinusWebsite.mDriver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File("src/main/java/screenshot/ss.png");

        FileUtils.copyFile(src,dest);
    }
}




