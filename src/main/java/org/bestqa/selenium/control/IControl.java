package org.bestqa.selenium.control;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;

public interface IControl extends WrapsDriver, WrapsElement, WebElement, Locatable {
	/**
	 * Get DomId, the value in "&lt;tag id="value"&gt;"
	 */
	public String getId();
	
	/**
	 * Is there a dom id
	 * @return <b>true</b>: there is a dom id, <b>false</b>: there is not a dom id
	 */
	public boolean hasId();
	
	/**
	 * Get a style, the value in "<tag style="value">"
	 */
	public String getStyle();
	
	/**
	 * Get a class, the value in "<tag class="value">"
	 */
	public String getClassName();
	
	/**
	 * Get parent node
	 */
	public WebElement getParentNode();
	
	/**
	 * To set attribute with JS
	 * @param name
	 * @param value
	 */
	public void jsSetAttribute(String name, String value);
	
	/**
	 * Get the count of the direct child node according to node name
	 * @param childNodeTagName name of the child node
	 * @return 
	 */
	public int getChildNodesCountByTagName(String childNodeTagName);
	
	/**
	 * Get the count of the direct child node according to node name
	 * @param parentNode parent node
	 * @param childNodeTagName name of the child node
	 * @return 
	 */
	public int getChildNodesCountByTagName(WebElement parentNode, String childNodeTagName);
	
	/**
	 * Get the count of the direct child node according to node name
	 * @param parentNode parent node
	 * @param childNodeTagNames the name list of the child node
	 * @return 
	 */
	public int getChildNodesCountByTagName(WebElement parentNode, List<String> childNodeTagNames);
	
	/**
	 * The mouse moves over the current element
	 */
	public void mouseOver();
}
