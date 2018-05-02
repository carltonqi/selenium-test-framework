package org.bestqa.selenium.common;

import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration information
 *
 */
public class ConfigurationSettings {

	private static Properties props = null;
	static{
		try {
			props = new Properties();
			InputStream in = ConfigurationSettings.class.getClassLoader().getResourceAsStream(Constants.SELENIUM_CONFIG);
			props.load(in);
		} catch (Exception e) {
			throw new RuntimeException("ConfigurationSettings initialize failed.", e);
		}
	}
	
	/**
	 * Gets the configuration item
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String getProperty(String propertyName){
		return props.getProperty(propertyName);
	}
	
	private ConfigurationSettings(){
		
	}
	
	/**
	 * DriverFactory Type
	 */
	public static final String WEBDRIVER_FACTORY = getProperty("webdriver.factory");
	
	/**
	 * WebDriver Type
	 */
	public static final String WEBDRIVER_TYPE = getProperty("webdriver.type");

	/**
	 * Execution path for Firefox
	 */
	public static final String WEBDRIVER_FIREFOX_BINARY_PATH = getProperty("webdriver.firefox.binary.path");

	/**
	 * Execution path for Chrome
	 */
	public static final String WEBDRIVER_CHROME_BINARY_PATH = getProperty("webdriver.chrome.binary.path");
	
	/**
	 * Execution path for Edge
	 */
	public static final String WEBDRIVER_EDGE_BINARY_PATH = getProperty("webdriver.edge.binary.path");
	
	/**
	 * Execution path for PhantomJS
	 */
	public static final String WEBDRIVER_PHANTOMJS_BINARY_PATH = getProperty("webdriver.phantomjs.binary.path");
	
	/**
	 * Whether to RemoteWebDriver to connect to a remote server to perform
	 */
	public static final Boolean WEBDRIVER_ISREMOTE = Boolean.parseBoolean(getProperty("webdriver.isremote"));
	
	/**
	 * The Server address when using RemoteWebDriver
	 */
	public static final String WEBDRIVER_REMOTE_HOST = getProperty("webdriver.remote.host");
	
	/**
	 * The server port when using remote WebDriver
	 */
	public static final String WEBDRIVER_REMOTE_PORT = getProperty("webdriver.remote.port");
	
	/**
	 * Whether to close the browser when the test class performed
	 */
	public static Boolean WEBDRIVER_CLOSE_AFTERCLASS = Boolean.parseBoolean(getProperty("webdriver.close.afterclass"));
	
	/**
	 * WebDriver default wait time (unit:ms)
	 */
	public static final Integer WEBDRIVER_IMPLICITLYWAIT = Integer.valueOf(getProperty("webdriver.implicitlywait"));
	
	/**
	 * Timeout for ActionWait (unit:ms)
	 */
	public static final Integer ACTIONWAIT_TIMEOUT = Integer.valueOf(getProperty("actionwait.timeout"));
	
	/**
	 * ActionWait polling interval of time (unit:ms)
	 */
	public static final Integer ACTIONWAIT_SLEEP_DURATION = Integer.valueOf(getProperty("actionwait.sleep.duration"));
	
	/**
	 * Timeout for page load (unit:ms)
	 */
	public static final Integer PAGELOAD_TIMEOUT = Integer.valueOf(getProperty("pageload.timeout"));
	
	/**
	 * Path of screenshot when case fail
	 */
	public static final String SCREENSHOT_ERROR_DIR = getProperty("screenshot.error.dir");
	
	/**
	 * Path of automatic screenshot
	 */
	public static final String SCREENSHOT_AUTO_DIR = getProperty("screenshot.auto.dir");
	
	/**
	 * DLL path of the AutoIt3, relative to the user.dir
	 */
	public static final String THIRDPARTY_AUTOIT3_DLL = getProperty("thirdparty.autoit3.dll");
	
	/**
	 * EXE path of the AutoIt3, relative to the user.dir
	 */
	public static final String THIRDPARTY_AUTOIT3_EXE = getProperty("thirdparty.autoit3.exe");
	
	/**
	 * The AutoIt script to handle file uploading dialogue 
	 */
	public static final String THIRDPARTY_AUTOIT3_SCRIPT_UPLOADFILE = getProperty("thirdparty.autoit3.script.uploadfile");
}
