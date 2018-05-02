package org.bestqa.selenium.page;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

/**
 * Responsible for the jump of the Url interface
 *
 */
public interface IUrlNavigator {
	/**
	 * Jump to targetUrl
	 * @param driver
	 * @param targetUrl
	 */
	void doNavigation(WebDriver driver, String targetUrl);
	
	/**
	 * Jump to targetUrl
	 * @param driver
	 * @param targetUrl
	 * @param paramTable
	 */
	void doNavigation(WebDriver driver, String targetUrl, Hashtable<String, String> paramTable);
}
