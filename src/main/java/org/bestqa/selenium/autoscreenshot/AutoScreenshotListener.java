package org.bestqa.selenium.autoscreenshot;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

import org.bestqa.selenium.common.Constants;
import org.bestqa.selenium.intercept.IInterceptListener;
import org.bestqa.selenium.intercept.MethodInterceptController;
import org.bestqa.selenium.page.factory.PageFactory;
import org.bestqa.selenium.screenshot.ScreenshotController;
import org.bestqa.selenium.screenshot.WebDriverScreenshotTaker;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listeners of automatic screenshots
 * To use this listener we need to set the global variable CONTEXT_KEY_DRIVER which is currently using
 * WebDriver to the ITestContext
 *
 */
public class AutoScreenshotListener implements IInterceptListener, ITestListener, ISuiteListener {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private MethodInterceptController interceptController = null;
	private ITestContext testContext = null;
	private AutoScreenshotStorer storer = null;
	private ScreenshotController controller = null;

	@Override
	public void onStart(ISuite suite) {
		// Sets the callback of a PageFactory when a Suite startups
		// We can use this callback during the execution of the entire Suite
		this.interceptController = new MethodInterceptController();
		this.interceptController.addFilter(new AutoInterceptFilter());
		this.interceptController.addListener(this);

		PageFactory.addInterceptor(this.interceptController);
	}

	@Override
	public void onFinish(ISuite suite) {
		// remove object when a Suite runs completed
		PageFactory.removeInterceptor(this.interceptController);
		this.interceptController = null;
	}

	@Override
	public void onStart(ITestContext testContext) {
		// Cache testContext when a <test> starts to execute
		// Essentially, this is a TestRunner instance
		// In the subsequent test execution, global variable is passed through the testContext
		this.testContext = testContext;
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// Empty the object at the end of the <test> run
		this.testContext = null;
	}
	
	@Override
	public void onAfterInvoke(Object obj, Method method, Object[] args, MethodProxy proxy) {
		// MethodInterceptor triggers after a method is invoked in order to take screenshot
		if (null == this.storer || null == this.controller) {
			return;
		}

		// Set the current object and method of intercept which is used to generate the corresponding file name
		this.storer.setInvokeTarget(obj, method);
		
		logger.debug(String.format("take screenshot to dir: %s, file: "
						, this.storer.getDir().getAbsolutePath(), this.storer.getFileName()));
		this.controller.takeScreenshot();
	}

	@Override
	public void onBeforeInvoke(Object obj, Method method, Object[] args, MethodProxy proxy) {
		// do nothing
	}

	@Override
	public void onExceptionOccurs(Object obj, Method method, Object[] args, Exception ex) {
		// do nothing
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		onTestFailure(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Not to retain capture process when case fails
		this.storer.remove();
		onEndTest(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		onEndTest(result);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTestStart(ITestResult result) {
		// Triggers this logic when an item of DataProvider is invoked of each TestMethod, namely @Test
        if (null == this.testContext) {
	        	this.logger.warn("TestContext is null.");
	        	return;
        }
        
        Object driver = this.testContext.getAttribute(Constants.CONTEXT_KEY_DRIVER);
        if (null == driver) {
	        	this.logger.warn(String.format("Can not get driver from TestContext. Attribute '%s' in TestContext is not set as a WebDriver."
	        					, Constants.CONTEXT_KEY_DRIVER));
	        	return;
        }
        else if (!(driver instanceof WebDriver)) {
	        	this.logger.warn(String.format("Attribute '%s' in TestContext is not set as a WebDriver.", Constants.CONTEXT_KEY_DRIVER));
	        	return;
        }
        else if (!(driver instanceof TakesScreenshot)) {
	        	this.logger.warn("Driver does not implement interface TakesScreenshot.");
	        	return;
        }
		
		Object testObj = result.getInstance();
		Method testMethod = result.getMethod().getMethod();
		Object[] params = result.getParameters();
		int invokeCount = result.getMethod().getCurrentInvocationCount();
		
		WebDriver webdriver = (WebDriver) driver;
		
		// To generate a new ScreenshotController when beginning to execute a @Test
		// Use this object to screenshot during the execution of the @Test
		this.storer = new AutoScreenshotStorer();
		this.storer.setTestTarget(testObj, testMethod, params, invokeCount);
		this.controller = new ScreenshotController(new WebDriverScreenshotTaker(webdriver), this.storer);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		onEndTest(result);
	}
	
	private void onEndTest(ITestResult result) {
		// Empty screenshot object when @Test runs completed
		this.controller = null;
		this.storer = null;
	}
}
