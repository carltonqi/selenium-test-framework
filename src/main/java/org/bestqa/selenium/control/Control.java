package org.bestqa.selenium.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bestqa.selenium.basic.HtmlAttributes;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Control is the base class for all controls which implements WebElement, WrapsDriver and Locatable interfaces
 * Therefore, WebElement of constructor must also implement WrapsDriver and Locatable 
 * which is used to get an instance of WebDriver and location information
 * Otherwise throw an IllegalArgumentException exception
 */
public abstract class Control implements IControl {
	private static final long PAUSE_PREIOD = 100;
	private static final String JS_SET_ATTRIBUTE
		= "var element = arguments[0];"
		+ "var attributeName = arguments[1];"
		+ "var attributeValue = arguments[2];"
		+ "element.setAttribute(attributeName, attributeValue);";
	
	private static final String JS_CHILDNODES_COUNT_BY_TAGNAME
		= "var container = arguments[0];"
		+ "var childNodeTags = arguments[1];"
		+ "var childNodes = container.childNodes;"
		+ "var resultNodes = new Array();"
		+ "for (var i = 0; i < childNodes.length; i++) {"
		+   "for (var j = 0; j < childNodeTags.length; j++) {"
		+     "if (childNodes[i].nodeName.toLowerCase() == childNodeTags[j].toLowerCase()) {"
		+       "resultNodes.push(0);"
		+     "}"
		+   "}"
		+ "}"
		+ "return resultNodes.length;";

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected WebElement wrappedElement = null;
	protected WebDriver driver = null;
	protected JavascriptExecutor jsExecutor = null;

	/**
	 * do nothing, for lazyload & cglib
	 */
	protected Control() {

	}

	/**
	 * Construct Control instance, set wrapped element.
	 */
	public Control(WebElement webElement) {
		if (null == webElement) {
			throw new IllegalArgumentException("Argument webElement cannot be null.");
		}

		if (!(webElement instanceof WrapsDriver) || !(webElement instanceof Locatable)) {
			throw new IllegalArgumentException("Argument webElement type error.");
		}

		this.wrappedElement = webElement;
		this.driver = ((WrapsDriver) webElement).getWrappedDriver();
		
		if (!(driver instanceof JavascriptExecutor)) {
			throw new IllegalArgumentException("Wrapped driver is not instanceof JavascriptExecutor.");
		}
		
		this.jsExecutor = (JavascriptExecutor) driver;
	}

	/**
	 * Construct object with web element find by ID.
	 */
	public Control(WebDriver driver, String id) {
		this(driver.findElement(By.id(id)));
	}

	/**
	 * Construct object with web element find by BY object.
	 */
	public Control(WebDriver driver, By by) {
		this(driver.findElement(by));
	}

	@Override
	public WebDriver getWrappedDriver() {
		return ((WrapsDriver) wrappedElement).getWrappedDriver();
	}

	@Override
	public WebElement getWrappedElement() {
		return wrappedElement;
	}

	@Override
	public Coordinates getCoordinates() {
		return ((Locatable) wrappedElement).getCoordinates();
	}
	
	/**
	 * Find the first {@link WebElement} using the given method. This method is
	 * affected by the 'implicit wait' times in force at the time of execution.
	 * The findElement(..) invocation will return a matching row, or try again
	 * repeatedly until the configured timeout is reached.
	 * 
	 * findElement should not be used to look for non-present elements, use
	 * {@link #findElements(By)} and assert zero length response instead.
	 * 
	 * @param by The locating mechanism
	 * @return The first matching element on the current page
	 * @throws NoSuchElementException If no matching elements are found
	 * @see org.openqa.selenium.By
	 * @see org.openqa.selenium.WebDriver.Timeouts
	 */	
	@Override
	public WebElement findElement(By arg0) {
		return wrappedElement.findElement(arg0);
	}

	/**
	 * Find all elements within the current page using the given mechanism. This
	 * method is affected by the 'implicit wait' times in force at the time of
	 * execution. When implicitly waiting, this method will return as soon as
	 * there are more than 0 items in the found collection, or will return an
	 * empty list if the timeout is reached.
	 * 
	 * @param by The locating mechanism to use
	 * @return A list of all {@link WebElement}s, or an empty list if nothing matches
	 * @see org.openqa.selenium.By
	 * @see org.openqa.selenium.WebDriver.Timeouts
	 */
	@Override
	public List<WebElement> findElements(By arg0) {
		return wrappedElement.findElements(arg0);
	}

	@Override
	public void click() {
		wrappedElement.click();
	}

	@Override
	public void submit() {
		wrappedElement.submit();
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
		wrappedElement.sendKeys(arg0);
	}

	@Override
	public void clear() {
		wrappedElement.clear();
	}

	@Override
	public String getTagName() {
		return wrappedElement.getTagName();
	}

	@Override
	public String getAttribute(String arg0) {
		return wrappedElement.getAttribute(arg0);
	}

	@Override
	public boolean isSelected() {
		return wrappedElement.isSelected();
	}

	/**
	 * Set selected
	 */
	public void setSelected() {
		if (!isSelectable()) {
			throw new InvalidElementStateException("You may only set selectable items selected");
		}

		if (!isSelected()) {
			click();
		}
	}
	
	/**
	 * Set to not selected 
	 */
	public void setUnSelected() {
		if (!isSelectable()) {
			throw new InvalidElementStateException("You may only set selectable items selected");
		}

		if (isSelected()) {
			click();
		}
	}

	protected boolean isSelectable() {
		String tagName = getTagName().toLowerCase();
		String type = getAttribute("type");
		type = type == null ? "" : type.toLowerCase();

		return "option".equals(tagName) ||
			("input".equals(tagName) && ("radio".equals(type) || "checkbox".equals(type)));
	}

	@Override
	public boolean isEnabled() {
		return wrappedElement.isEnabled();
	}

	@Override
	public String getText() {
		return wrappedElement.getText();
	}

	@Override
	public String getCssValue(String propertyName) {
		return wrappedElement.getCssValue(propertyName);
	}

	@Override
	public boolean isDisplayed() {
		return wrappedElement.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return wrappedElement.getLocation();
	}

	@Override
	public Dimension getSize() {
		return wrappedElement.getSize();
	}
	
	/**
	 * Get DomId, the value in "&lt;tag id="value"&gt;"
	 */
	@Override
	public String getId() {
		return getAttribute(HtmlAttributes.ID);
	}
	
	/**
	 * Is there a dom id
	 * @return <b>true</b>: there is a dom id; <b>false</b>: there is not a dom id
	 */
	@Override
	public boolean hasId() {
		return !StringUtils.isEmpty(this.getId());
	}
	
	/**
	 * Get a style, the value in "<tag style="value">"
	 */
	@Override
	public String getStyle() {
		return getAttribute(HtmlAttributes.STYLE);
	}
	
	/**
	 * Get class, the value in "<tag class="value">"
	 */
	@Override
	public String getClassName() {
		return getAttribute(HtmlAttributes.CLASS);
	}
	
	/**
	 * Get parent node
	 */
	@Override
	public WebElement getParentNode() {
		return wrappedElement.findElement(By.xpath(".."));
	}
	
	/**
	 * Wait for a short period of time, in order not to conflict with JS on the page to perform
	 */
	protected void pause() {
		this.sleep(PAUSE_PREIOD);
	}

	protected void sleep(long milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * To set attribute with JS
	 * @param name
	 * @param value
	 */
	@Override
	public void jsSetAttribute(String name, String value) {
		jsExecutor.executeScript(JS_SET_ATTRIBUTE, this, name, value);
	}
	
	/**
	 * Get the count of the direct child node according to node name
	 * @param childNodeTagName name of the child node
	 * @return 
	 */
	@Override
	public int getChildNodesCountByTagName(String childNodeTagName) {
		return this.getChildNodesCountByTagName(this, childNodeTagName);
	}
	
	/**
	 * Get the count of the direct child node according to node name
	 * @param parentNode parent node
	 * @param childNodeTagName name of the child node
	 * @return 
	 */
	@Override
	public int getChildNodesCountByTagName(WebElement parentNode, String childNodeTagName) {
		List<String> tags = new ArrayList<String>();
		tags.add(childNodeTagName);
		return this.getChildNodesCountByTagName(parentNode, tags);
	}
	
	/**
	 * Get the count of the child node according to node tag name
	 * @param parentNode parent node
	 * @param childNodeTagNames the name list of the child node
	 * @return 
	 */
	@Override
	public int getChildNodesCountByTagName(WebElement parentNode, List<String> childNodeTagNames) {
		Object result = jsExecutor.executeScript(JS_CHILDNODES_COUNT_BY_TAGNAME, parentNode, childNodeTagNames);
		return (null != result) ? Integer.valueOf(result.toString()) : 0;
	}
	
	/**
	 * The mouse moves over the current element
	 */
	@Override
	public void mouseOver() {
		Actions builder = new Actions(driver);
		Action action = builder.moveToElement(this.getWrappedElement()).build();
		action.perform();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result
				+ ((wrappedElement == null) ? 0 : wrappedElement.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Control other = (Control) obj;
		if (wrappedElement == null) {
			if (other.wrappedElement != null)
				return false;
		} else if (!wrappedElement.equals(other.wrappedElement))
			return false;
		return true;
	}

}
