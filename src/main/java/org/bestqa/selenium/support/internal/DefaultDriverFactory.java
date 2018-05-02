package org.bestqa.selenium.support.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.bestqa.selenium.common.ConfigurationSettings;
import org.bestqa.selenium.exception.UnsupportedDriverException;
import org.bestqa.selenium.support.DriverFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default WebDriver factory class
 *
 */
public class DefaultDriverFactory extends DriverFactory {
	private static DriverTypeEnum DEFAULT_DRIVER_TYPE = DriverTypeEnum.FirefoxDriver;
	
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public WebDriver getDriver() {
    	WebDriver driver = null;
    	if (ConfigurationSettings.WEBDRIVER_ISREMOTE) {
    		driver = getRemoteDriver();
    	}
    	else {
    		driver = getLocalDriver();
    	}
    	
    	setDefaultOptions(driver);
    	return driver;
    }
    
	private WebDriver getLocalDriver() {
	    	DriverTypeEnum driverType = this.getDriverType();
	    	WebDriver driver = null;
	    	
	    	switch(driverType){
	        case InternetExplorerDriver:
	        	driver = new InternetExplorerDriver();
	            break;
	        case FirefoxDriver:
	        	if (!StringUtils.isEmpty(ConfigurationSettings.WEBDRIVER_FIREFOX_BINARY_PATH.trim())) {
	        		System.setProperty("webdriver.firefox.bin", ConfigurationSettings.WEBDRIVER_FIREFOX_BINARY_PATH.trim());
	        	}
	        	driver = new FirefoxDriver();
	            break;
	        case ChromeDriver:
	        	if (!StringUtils.isEmpty(ConfigurationSettings.WEBDRIVER_CHROME_BINARY_PATH.trim())) {
	        		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, ConfigurationSettings.WEBDRIVER_CHROME_BINARY_PATH.trim());
	        	}
	        	driver = new ChromeDriver();
	            break;
	        case SafariDriver:
	        	driver = new SafariDriver();
	        		break;
	        case EdgeDriver:
	        	if (!StringUtils.isEmpty(ConfigurationSettings.WEBDRIVER_EDGE_BINARY_PATH.trim())) {
	        		System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, ConfigurationSettings.WEBDRIVER_EDGE_BINARY_PATH.trim());
	        	}
	        	driver = new EdgeDriver();
	        		break;
	        case PhantomJSDriver:
	        	DesiredCapabilities phantomCapabilites = new DesiredCapabilities();     
	        phantomCapabilites.setJavascriptEnabled(true); 
	        if (!StringUtils.isEmpty(ConfigurationSettings.WEBDRIVER_PHANTOMJS_BINARY_PATH.trim())) {
	        		phantomCapabilites.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, ConfigurationSettings.WEBDRIVER_PHANTOMJS_BINARY_PATH.trim());
	        }
	        	driver = new PhantomJSDriver(phantomCapabilites);
	        		break;
	        default:
	        	throw new UnsupportedDriverException("Unsupported Driver: " + driverType.name());
	    	}
	    	
	    	return driver;
    }
    
    private WebDriver getRemoteDriver() {
	    	DriverTypeEnum driverType = this.getDriverType();
	    	WebDriver driver = null;
	    	DesiredCapabilities dc = null;
	    	try {
				URL remoteAddress = new URL(ConfigurationSettings.WEBDRIVER_REMOTE_HOST + ":" + ConfigurationSettings.WEBDRIVER_REMOTE_PORT  + "/wd/hub");
		    	switch(driverType){
		        case InternetExplorerDriver:
		        	dc = DesiredCapabilities.internetExplorer();
		            break;
		        case FirefoxDriver:
		        	dc = DesiredCapabilities.firefox();
		            break;
		        case ChromeDriver:
		        	dc = DesiredCapabilities.chrome();
		            break;
		        case SafariDriver:
		        	dc = DesiredCapabilities.safari();
		        		break;
		        case EdgeDriver:
		        	dc = DesiredCapabilities.edge();
		        	 	break;
		        case PhantomJSDriver:
		        	dc = DesiredCapabilities.phantomjs();
		        		break;
		        default:
		        	throw new UnsupportedDriverException("Unsupported Driver: " + driverType.name());
		    	}
		    	
				dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
				driver = new RemoteScreenShotWebdriver(remoteAddress, dc);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
	
	    	return driver;
    }
    
    /**
     * Depending on the type of WebDriver configuration, converted to the corresponding enumeration object
     */
    private DriverTypeEnum getDriverType() {
	    	DriverTypeEnum type = DEFAULT_DRIVER_TYPE;
	    	try {
	    		type = Enum.valueOf(DriverTypeEnum.class, ConfigurationSettings.WEBDRIVER_TYPE);
	    	}
	    	catch (Exception ex) {
	    		this.logger.warn("Parse WebDriver.Type failed, use default driver type: " + DEFAULT_DRIVER_TYPE.name());
	    		this.logger.debug(ex.getMessage(), ex);
	    	}
	    	return type;
    }
    
    /**
     * Set the implicitlyWait time, maximizing the browser
     */
    private void setDefaultOptions(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(ConfigurationSettings.WEBDRIVER_IMPLICITLYWAIT, TimeUnit.MICROSECONDS);
        try {
        	driver.manage().window().maximize();
        }
        catch (Exception ex) {
        	try {
    			// In some cases, the browser does not support the driver.manage().window().maximize()
	        	// Chrome:17, ChromeDriver:20, Selenium:2.21.0
    			driver.manage().window().setPosition(new Point(0, 0));
	        	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	        	Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
	        	driver.manage().window().setSize(dim);
        	}
        	catch (Exception exception) {
        		logger.warn("Maximize browser failed.");
        		logger.warn(exception.getMessage(), exception);
        	}
        }
    }
}
