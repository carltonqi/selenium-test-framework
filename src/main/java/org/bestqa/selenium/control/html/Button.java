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
 * The Button, input type="button" 
 *
 */
@LazyLoadControl
public class Button extends Control {

	protected Button() {
		// empty for LazyLoad
	}
	
	public Button(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public Button(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public Button(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public String getText(){
		return wrappedElement.getAttribute(HtmlAttributes.VALUE);
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
