package org.bestqa.selenium.control.html;

import org.bestqa.selenium.control.Control;
import org.bestqa.selenium.control.LazyLoadControl;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * HTML list items which is used to encapsulate the li elements
 * 
 * @see http://www.w3school.com.cn/html/html_lists.asp 
 * 
 */
@LazyLoadControl
public class HtmlListItem extends Control {

	protected HtmlListItem() {
		// empty for LazyLoad
	}

	public HtmlListItem(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public HtmlListItem(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public HtmlListItem(WebDriver driver, By by) {
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
