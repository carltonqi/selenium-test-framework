package org.bestqa.selenium.screenshot;

import java.io.File;

import org.bestqa.selenium.utils.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;

public class ScreenshotUtils {
	
	/**
	 * Cut the whole browser
	 * @param driver
	 * @param target
	 */
	public static <X> X captureScreen(WebDriver driver, OutputType<X> target) {
		checkDriver(driver);
		TakesScreenshot screenshoter = (TakesScreenshot) driver;
		return screenshoter.getScreenshotAs(target);
	}
	
	/**
	 * Give a WebElement screenshots
	 * @param driver
	 * @param element
	 * @param target
	 */
	public static <X> X captureElement(WebDriver driver, WebElement element, OutputType<X> target) {
		checkDriver(driver);
		TakesScreenshot screenshoter = (TakesScreenshot) driver;
		File screenFile = screenshoter.getScreenshotAs(OutputType.FILE);
		Point point = element.getLocation();
		Dimension dimension = element.getSize();
		byte[] elementByte = FileUtils.cutFromPng(screenFile, point, dimension);
		
		return target.convertFromPngBytes(elementByte);
	}

	private static void checkDriver(WebDriver driver) {
		Assert.isInstanceOf(TakesScreenshot.class, driver, "Driver is not instance of TakesScreenshot.");
	}
}
