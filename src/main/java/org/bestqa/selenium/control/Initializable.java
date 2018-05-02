/**
 * 
 */
package org.bestqa.selenium.control;

/**
 * The control with a refreshed interface
 * Complex control actions needs to be initialized when the initialization, 
 * as well as provide refreshing interface
 */
public interface Initializable<T> {
	/**
	 * Refreshing interface
	 * To use when initialization and refresh
	 * Return the control itself
	 */
	public T init();
}
