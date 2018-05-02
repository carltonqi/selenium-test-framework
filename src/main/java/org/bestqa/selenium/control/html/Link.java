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
 * a element
 *
 */
@LazyLoadControl
public class Link extends Control {

	protected Link() {
		// empty for LazyLoad
	}

	public Link(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public Link(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public Link(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get link address
	 */
	public String getHref() {
		return getAttribute(HtmlAttributes.HREF);
	}

	/**
	 * Gets the target window for link
	 */
	public String getTarget() {
		return getAttribute(HtmlAttributes.TARGET);
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
