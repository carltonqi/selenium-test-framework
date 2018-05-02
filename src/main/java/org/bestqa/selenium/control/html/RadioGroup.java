/**
 * 
 */
package org.bestqa.selenium.control.html;

import java.util.ArrayList;
import java.util.List;

import org.bestqa.selenium.control.Control;
import org.bestqa.selenium.control.LazyLoadControl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
/**
 * The Radio group
 * 
 */
@LazyLoadControl
public class RadioGroup extends Control {

	/**
	 * 
	 */
	protected RadioGroup() {
		// empty for LazyLoad
	}

	/**
	 * @param webElement
	 */
	public RadioGroup(WebElement webElement) {
		super(webElement);
		init();
	}

	/**
	 * @param driver
	 * @param id
	 */
	public RadioGroup(WebDriver driver, String id) {
		super(driver, id);
		init();
	}

	/**
	 * @param driver
	 * @param by
	 */
	public RadioGroup(WebDriver driver, By by) {
		super(driver, by);
		init();
	}

	private List<String> nameList = new ArrayList<String>();
	private List<WebElement> elementList = new ArrayList<WebElement>();

	/**
	 * Initialization, please call when control is refreshed
	 */
	public RadioGroup init() {
		nameList.clear();
		elementList.clear();
		elementList = getList();
		for (WebElement element : elementList) {
			nameList.add(getVisibleTextByElement(element));
		}
		return this;
	}

	/**
	 * Returns a list of all the options
	 */
	public List<WebElement> getList() {
		try {
			return wrappedElement.findElements(By
					.xpath(".//input[@type='radio']"));
		} catch (Exception e) {
			return new ArrayList<WebElement>();
		}
	}

	/**
	 * Get the displayed text 
	 */
	protected String getVisibleTextByElement(WebElement element) {
		String id = element.getAttribute("id");
		return wrappedElement.findElement(
				By.xpath(".//label[@for='" + id + "']")).getText();
	}

	/**
	 * Get list of all radio options
	 */
	public List<String> getTextlist() {
		return nameList;
	}


	/**
	 * Choose radio button according to displayed text
	 */
	public void selectByVisibleText(String text) {
		boolean result = false;
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).equals(text)) {
				selectByIndex(i);
				result = true;
				break;
			}
		}
		if (!result)
			throw new NoSuchElementException(
					"Cannot locate an element in RadioGroup-selectByVisibleText "
							+ text);
	}

	/**
	 * Choose radio button according to index
	 */
	public void selectByIndex(int index) {
		if (index < 0 || index > elementList.size() - 1)
			throw new NoSuchElementException(
					"Cannot locate an element in CheckBoxGroup-selectByIndex "
							+ index);
		WebElement element = elementList.get(index);
		if (!element.isSelected()) {
			element.click();
		}
	}

	/**
	 * Get selected text
	 */
	public String getSelectedVisibleText() {
		for (int i = 0; i < elementList.size(); i++) {
			if (elementList.get(i).isSelected()) {
				return nameList.get(i);
			}
		}
		return null;
	}

	/**
	 * Get the selected radio button
	 */
	public WebElement getSelectedElements() {
		for (WebElement element : elementList) {
			if (element.isSelected())
				return element;
		}
		return null;
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
