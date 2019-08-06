package com.vsysq.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestListener extends TestBase implements ITestListener {


	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().log(Status.INFO, "*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().log(Status.INFO, (result.getMethod().getMethodName() + " failed!"));

		String targetLocation = null;

		String testClassName = result.getInstanceName().trim();
		System.out.println(testClassName);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()); // get timestamp
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + timeStamp + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
		ExtentTestManager.getTest().log(Status.INFO, "Screen shots reports path - " + reportsPath);
		try {
			File file = new File(reportsPath + fileSeperator + testClassName); // Set
			// screenshots
			// folder
			if (!file.exists()) {
				if (file.mkdirs()) {
					ExtentTestManager.getTest().log(Status.INFO, "Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					ExtentTestManager.getTest().log(Status.INFO, "Failed to create directory: " + file.getAbsolutePath());
				}

			}
			
			targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;

			String screenshotFile = GetScreenshot.capture(result.getName(), targetLocation);
			
			ExtentTestManager.getTest().log(Status.INFO, "Screen shot file location - " + screenshotFile);
			ExtentTestManager.getTest().log(Status.INFO, "Target File location - " + targetLocation);

		} catch (FileNotFoundException e) {
			ExtentTestManager.getTest().log(Status.INFO, "File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			ExtentTestManager.getTest().log(Status.INFO, "An exception occurred while taking screenshot " + e.getCause());
		}

		// attach screenshots to report
		try {
			ExtentTestManager.getTest().fail("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
		} catch (IOException e) {
			ExtentTestManager.getTest().log(Status.INFO, "An exception occured while taking screenshot " + e.getCause());
		}
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	}


	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}