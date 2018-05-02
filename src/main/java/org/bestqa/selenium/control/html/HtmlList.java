/**
 * 
 */
package org.bestqa.selenium.control.html;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.bestqa.selenium.control.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * The html list, have no fixed tagName, so there is no inheritance HtmlBase implementation
 * 
 * @see http://www.w3school.com.cn/html/html_lists.asp
 * 
 */
public class HtmlList extends Control {

	protected HtmlList() {
		// empty for LazyLoad
	}

	/**
	 * @param webElement
	 */
	public HtmlList(WebElement webElement) {
		super(webElement);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param driver
	 * @param id
	 */
	public HtmlList(WebDriver driver, String id) {
		super(driver, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param driver
	 * @param by
	 */
	public HtmlList(WebDriver driver, By by) {
		super(driver, by);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the li node list of the next level
	 * @return The List of nodes
	 */
	public List<WebElement> getList() {
		try {
			return wrappedElement.findElements(By.xpath("./li"));
		} catch (Exception e) {
			return new ArrayList<WebElement>();
		}
	}
	
	/**
	 * Get literal text of the li node list of the next level
	 * @return		Content of a node list
	 */
	public List<String> getTextList() {
		ArrayList<String> listText = new ArrayList<String>();
		for (WebElement li : getList()) {
			listText.add(li.getText());
		}
		return listText;
	}
	
	/**
	 * According to the text that is displayed to choose
	 * @param text
	 */
	public void clickLiByText(String text) {
		for (WebElement op : getList()) {
			if (op.getText().equals(text)) {
				op.click();
				return;
			}
		}
		throw new NoSuchElementException(
				"Cannot locate an element in HtmlList-clickLiByText ");
	}
	
	/**
	 * According to the index number to obtain HtmlListItem
	 * @param index start from 0
	 * @return 
	 */
	public HtmlListItem getItemByIndex(Integer index) {
		return new HtmlListItem(wrappedElement.findElement(By.xpath(String.format("./li[%s]", index + 1))));
	}
	
	/**
	 * Click on a list item
	 * @param index start from 0
	 */
	public void clickByIndex(Integer index) {
		this.getItemByIndex(index).click();
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
