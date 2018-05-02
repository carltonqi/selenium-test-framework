package org.bestqa.selenium.exception;

/**
 * The exception which does not match URL
 *
 */
public class NoMatchedURLException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1356137186291485418L;

	private static final String ERROR_MESSAGE = "No matched URL found for page: ";
	
	public NoMatchedURLException(Class<?> clazz) {
		super(ERROR_MESSAGE + clazz.getName());
	}
}
