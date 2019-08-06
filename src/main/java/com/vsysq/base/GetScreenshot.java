package com.vsysq.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class GetScreenshot extends TestBase {
	
	public static String capture(String screenshotName, String dest) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return source.getAbsolutePath();
    }

}
