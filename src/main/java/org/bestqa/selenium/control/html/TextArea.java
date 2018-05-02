package org.bestqa.selenium.control.html;

import org.bestqa.selenium.basic.HtmlAttributes;
import org.bestqa.selenium.control.Control;
import org.bestqa.selenium.control.LazyLoadControl;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * TextArea element
 *
 */
@LazyLoadControl
public class TextArea extends Control {

	protected TextArea() {
		// empty for LazyLoad
	}

	public TextArea(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	public TextArea(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	public TextArea(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Sets the text in the input box, delete the original text
	 * @param text
	 */
	public void setText(String text) {
		this.setText(new String[] { text } );
	}
	
	/**
     * Sets the text in the input box, delete the original text
     * @param text
     */
	public void setText(String[] text) {
	    this.clear();
        if (null == text || 0 == text.length) {
            return;
        }
        
        this.sendKeys(text[0]);
        for (int i = 1; i < text.length; i++) {
            this.sendKeys(Keys.ENTER);
            this.sendKeys(text[i]);
        }
	}
	
	/**
	 * Appends additional line of text in the input box
	 * @param text
	 */
	public void appendText(String text) {
	    this.sendKeys(Keys.ENTER);
		this.sendKeys(text);
	}
	
	/**
	 * Append additional lines of text in the input box
	 * @param text
	 */
	public void appendText(String[] text) {
	    if (null == text || 0 == text.length) {
	        return;
	    }
	    for (int i = 0; i < text.length; i++) {
	        this.sendKeys(Keys.ENTER);
	        this.sendKeys(text[i]);
	    }
	}
	
	@Override
	public String getText() {
		return getAttribute(HtmlAttributes.VALUE);
	}

    /**
     * 
     * Returns a string array, the content as the contents of line segmentation result of the TextArea
     */
    public String[] getStringArray() {
        return getText().split("\r\n|\n|\n\n");
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
