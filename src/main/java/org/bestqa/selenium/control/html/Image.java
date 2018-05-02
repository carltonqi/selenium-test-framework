package org.bestqa.selenium.control.html;

import org.bestqa.selenium.basic.HtmlAttributes;
import org.bestqa.selenium.control.Control;
import org.bestqa.selenium.control.LazyLoadControl;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * img element
 *
 */
@LazyLoadControl
public class Image extends Control {

	protected Image() {
		// empty for LazyLoad
	}

	public Image(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public Image(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public Image(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Get image's address
	 */
	public String getSrc() {
		return this.getAttribute(HtmlAttributes.SRC);
	}

	/**
	 * Get image's text
	 */
	public String getAlt() {
		return this.getAttribute(HtmlAttributes.ALT);
	}
	
	/**
	 * Get image's width
	 */
	public String getWidth() {
		return this.getAttribute(HtmlAttributes.WIDTH);
	}
	
	/**
	 * Get image's height
	 */
	public String getHeight() {
		return this.getAttribute(HtmlAttributes.HEIGHT);
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
