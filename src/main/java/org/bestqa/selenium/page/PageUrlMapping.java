package org.bestqa.selenium.page;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.bestqa.selenium.common.Constants;
import org.bestqa.selenium.exception.InitializeFailedException;
import org.bestqa.selenium.exception.NoMatchedURLException;

/**
 * Page and Url mapping class
 * Configuration contains the baseUrl and relativeUrl of the page
 * Use this class to obtain absoluteUrl of the page, baseUrl/relativeUrl
 * Can also get relativeUrl of the page alone
 *
 */
public class PageUrlMapping {
	private PageUrlMapping() {
		
	}
	
	private static Properties urlMapping = null;
	private static String baseUrl = "";
	
	static{
		try {
			urlMapping = new Properties();
			InputStream in = Constants.class.getClassLoader().getResourceAsStream(Constants.PAGE_URL_MAPPING_FILE);
			urlMapping.load(in);
		} catch (Exception e) {
			throw new InitializeFailedException("Page-URL mapping initialize failed.", e);
		}
		
		baseUrl = urlMapping.getProperty("baseUrl");
	}
	
	/**
	 * Web site address
	 * @return
	 */
	public static String getBaseUrl() {
		return baseUrl;
	}
	
	/**
	 * Gets an absolute address of the page
	 * @param <T>
	 * @param pageClass
	 * @return $baseUrl/$relativeUrl
	 */
	public static <T extends Page> String getAbsoluteUrl(Class<T> pageClass) {
		return getAbsoluteUrl(getRelativeUrl(pageClass));
	}
	
	/**
	 * Gets a relative address of the page
	 * @author 
	 * @param <T>
	 * @param pageClass
	 * @return
	 */
	public static <T extends Page> String getRelativeUrl(Class<T> pageClass) {
		boolean hasKey = false;
		String result = "";
		
		Class<?> targetClass = pageClass;
		while (!targetClass.equals(Page.class)) {
			if (urlMapping.containsKey(targetClass.getName())) {
				hasKey = true;
				result = urlMapping.getProperty(targetClass.getName()).trim();
				break;
			}
			
			targetClass = targetClass.getSuperclass();
		}
		
		if (!hasKey) {
			throw new NoMatchedURLException(pageClass);
		}
		
		return result;
	}
	
	private static String getAbsoluteUrl(String relativeUrl) {
		if (StringUtils.isEmpty(relativeUrl)) {
			return baseUrl;
		}
		if (StringUtils.isEmpty(baseUrl)) {
			return relativeUrl;
		}
		if ('/' == relativeUrl.charAt(0)) {
			return baseUrl + relativeUrl;
		}

		return baseUrl + "/" + relativeUrl;
	}
}
