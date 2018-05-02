package org.bestqa.selenium.control.html;

import org.bestqa.selenium.control.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * The file uploading box, input type="file"
 *
 */
public class FileUploader extends Control {

	public FileUploader() {
		// TODO Auto-generated constructor stub
	}

	public FileUploader(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public FileUploader(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public FileUploader(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}
}
