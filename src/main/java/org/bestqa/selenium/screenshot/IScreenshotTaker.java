package org.bestqa.selenium.screenshot;

/**
 * The interface for screenshot
 * @author 
 *
 */
public interface IScreenshotTaker {
	/**
	 * Screenshots
	 * @return The binary data for screenshot
	 */
	public byte[] getScreenshot();
}
