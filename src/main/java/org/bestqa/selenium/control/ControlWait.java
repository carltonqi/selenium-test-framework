package org.bestqa.selenium.control;

import org.bestqa.selenium.support.ActionWait;

/**
 * Wait function of the control 
 * 
 */
public class ControlWait extends ActionWait<Control> {

	/**
	 * @param input The instance to pass to the expected conditions called
	 */
	public ControlWait(Control input) {
		super(input);
	}

	/**
	 * @param input The instance to pass to the expected conditions called
	 * @param timeOutInMillis The timeout in milliseconds when an expectation is called
	 */
	public ControlWait(Control input, long timeOutInMillis) {
		super(input, timeOutInMillis);
	}

	/**
	 * @param input The instance to pass to the expected conditions
	 * @param timeOutInMillis The timeout in milliseconds when an expectation is called
	 * @param sleepInMillis The duration in milliseconds to sleep between polls.
	 */
	public ControlWait(Control input, long timeOutInMillis, long sleepInMillis) {
		super(input, timeOutInMillis, sleepInMillis);
	}
}
