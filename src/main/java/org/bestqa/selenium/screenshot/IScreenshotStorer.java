package org.bestqa.selenium.screenshot;

import java.io.File;

/**
 * Screenshot storage interface
 *
 */
public interface IScreenshotStorer {
	/**
	 * Path to save the screenshots
	 */
	public File getDir();
	
	/**
	 * Filename to save the screenshots
	 */
	public String getFileName();
	
	/**
	 * Save screenshot
	 * @param pic
	 */
	public File save(byte[] pic);
	
	/**
	 * Delete saved screenshots
	 */
	public void remove();
}
