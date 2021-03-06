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
 * TextBox, input type="text"
 *
 */
@LazyLoadControl
public class TextBox extends Control {

	protected TextBox() {
		// empty for LazyLoad
	}

	public TextBox(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public TextBox(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public TextBox(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Sets the text in the input box, delete the original text
	 * @param text
	 */
	public void setText(String text) {
		this.clear();
		this.pause();
		this.sendKeys(text);
	}
	
	/**
	 * Appends additional line of text in the input box
	 * @param text
	 */
	public void appendText(String text) {
		this.sendKeys(text);
	}
	
	@Override
	public String getText() {
		return getAttribute(HtmlAttributes.VALUE);
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
