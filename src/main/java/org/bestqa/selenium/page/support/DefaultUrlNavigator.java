package org.bestqa.selenium.page.support;

import java.util.Hashtable;

import org.apache.commons.lang.StringUtils;
import org.bestqa.selenium.page.IUrlNavigator;
import org.bestqa.selenium.utils.RequestUtils;
import org.openqa.selenium.WebDriver;

/**
 * Regular Url jump, is suitable for most pages 
 *
 */
public class DefaultUrlNavigator implements IUrlNavigator {
	/**
	 * The separator between Url and QueryString
	 */
	private static final String PARAM_SEPERATOR = "?";

	@Override
	public void doNavigation(WebDriver driver, String targetUrl) {
		this.doNavigation(driver, targetUrl, null);
	}

	@Override
	public void doNavigation(WebDriver driver, String targetUrl, Hashtable<String, String> paramTable) {
		String destinationUrl = targetUrl;
		if (null != paramTable) {
			String queryString = RequestUtils.toQueryString(paramTable);
			if (!StringUtils.isEmpty(queryString)) {
				destinationUrl = destinationUrl + PARAM_SEPERATOR + queryString;
			}
		}
		
		driver.get(destinationUrl);
	}

}
