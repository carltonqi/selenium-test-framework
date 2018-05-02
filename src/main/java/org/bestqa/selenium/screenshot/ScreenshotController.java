package org.bestqa.selenium.screenshot;

import java.io.File;

/**
 * Responsible for the controller of the screenshot
 *
 */
public class ScreenshotController {
	private IScreenshotTaker taker = null;
	private IScreenshotStorer storer = null;
	
	public ScreenshotController(IScreenshotTaker taker, IScreenshotStorer storer) {
		if (null == taker) {
			throw new IllegalArgumentException("Argument taker cannot be null.");
		}
		
		if (null == storer) {
			throw new IllegalArgumentException("Argument locator cannot be null.");
		}
		
		this.taker = taker;
		this.storer = storer;
	}
	
	public IScreenshotTaker getTaker() {
		return this.taker;
	}
	
	public IScreenshotStorer getLocator() {
		return this.storer;
	}

	/**
	 * Screenshot and save it
	 */
	public File takeScreenshot() {
		byte[] data = this.taker.getScreenshot();
		return this.storer.save(data);
	}
}
