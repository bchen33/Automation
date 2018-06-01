package Utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

import org.openqa.selenium.WebDriver;

import Utility.Constant;

public class Screenshot{
	/*
	 * Method: takeDriverSnapShot
	 * Desc: Call this function to take a snapshot of the UI
	 */
	public static void takeDriverSnapShot(WebDriver driver, String screenSnapshotName, String sessionID) {
        File browserFile = new File(Constant.strShotLocation + File.separator + sessionID + "_" + screenSnapshotName + ".png");
        snapshotBrowser((TakesScreenshot) driver, screenSnapshotName, browserFile);
    }
	/*
	 * Method: snapshotBrowser
	 * Desc: Creates snap and moves file to location (this value is passed from takeDriverSnapShot)
	 */
	private static void snapshotBrowser(TakesScreenshot driver, String screenSnapshotName, File browserFile) {
        File scrFile;
		try {
            scrFile = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.moveFile(scrFile, browserFile);
        } catch (Exception e) {
            System.out.println("Error: Could not create browser snapshot: " + screenSnapshotName);
            e.printStackTrace();
        }
    }
}