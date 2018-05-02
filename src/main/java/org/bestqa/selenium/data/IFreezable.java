package org.bestqa.selenium.data;

/**
 * An interface object can be frozen
 *
 */
public interface IFreezable {
	/**
	 * Froze object
	 */
	public void freeze();
	
	/**
	 * Whether the object is frozen
	 */
	public boolean isFroze();
}
