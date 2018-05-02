package org.bestqa.selenium.control;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsDriver;

/**
 * Change the control into the concept of Part, easy to mix
 *
 * @param <ParentType>
 */
public abstract class ControlPart<ParentType extends Control> implements WrapsDriver {
	protected WebDriver driver;
	protected ParentType parentControl;
	
	public ControlPart(ParentType parent) {
		if (null == parent) {
			throw new IllegalArgumentException("Arg parent can not be null.");
		}
		parentControl = parent;
		driver = parent.getWrappedDriver();
	}
	
	/**
	 * Get wrapped driver object
	 * @return
	 */
	@Override
	public WebDriver getWrappedDriver() {
		return driver;
	}

	/**
	 * Get wrapped parent control
	 * @return
	 */
	public ParentType getParentControl() {
		return parentControl;
	}
}
