package org.bestqa.selenium.test;

import org.bestqa.selenium.common.ConfigurationSettings;
import org.bestqa.selenium.common.Constants;
import org.bestqa.selenium.support.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class AbstractTestWithSpringSupported extends AbstractTestNGSpringContextTests {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected WebDriver driver = null;
	protected ITestContext testContext;
	
	@BeforeClass(alwaysRun = true)
	public void setUpTestBase(ITestContext testContext) {
		try{
			this.testContext = testContext;
			quitDriver();
            resetDriver();
		 } catch(Exception e ) {
			 quitDriver();
			 Assert.fail("SetUp failed.", e);
		 }
	}

	@AfterClass(alwaysRun = true)
	public void tearDownTestBase() {
		quitDriver();
		this.testContext = null;
	}
	
	protected void quitDriver() {
		if(null != driver && ConfigurationSettings.WEBDRIVER_CLOSE_AFTERCLASS) {
			driver.quit();
			driver = null;
		}
        if (null != testContext && null != testContext.getAttribute(Constants.CONTEXT_KEY_DRIVER)) {
        	testContext.removeAttribute(Constants.CONTEXT_KEY_DRIVER);
        }
	}
    
    protected void resetDriver() {
    	driver = DriverFactory.getInstance().getDriver();
    	testContext.setAttribute(Constants.CONTEXT_KEY_DRIVER, driver);
    }
}
