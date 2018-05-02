package org.bestqa.selenium.support;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * Retry mechanism of special packaging for WebDriver operation, will ignore NotFoundException and StaleElementReferenceException Retry
 *
 */
public class WebDriverRetryStrategy extends RetryStrategy {

	public WebDriverRetryStrategy() {
		super();
		addDefaultIgnoring();
	}

	public WebDriverRetryStrategy(int retryTimes) {
		super(retryTimes);
		addDefaultIgnoring();
	}

	public WebDriverRetryStrategy(int retryTimes, long sleepTimeout) {
		super(retryTimes, sleepTimeout);
		addDefaultIgnoring();
	}

	/**
	 * Add default ignore the Exception type
	 */
	private void addDefaultIgnoring() {
		ignoring(NotFoundException.class);
		ignoring(StaleElementReferenceException.class);
	}
}
