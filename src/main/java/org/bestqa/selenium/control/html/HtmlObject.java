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
 * object element
 *
 */
@LazyLoadControl
public class HtmlObject extends Control {

	protected HtmlObject() {
		// empty for LazyLoad
	}

	public HtmlObject(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public HtmlObject(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public HtmlObject(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get width
	 */
	public String getObjectWidth(){
		return this.getAttribute(HtmlAttributes.WIDTH);
	}
	
	/**
	 * Get height
	 */
	public String getObjectHeight(){
		return this.getAttribute(HtmlAttributes.HEIGHT);
	}
	
	/**
	 * Get alignment
	 */
	public String getObjectAlign(){
		return this.getAttribute(HtmlAttributes.ALIGN);
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
