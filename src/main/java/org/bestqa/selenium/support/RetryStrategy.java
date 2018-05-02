package org.bestqa.selenium.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encapsulates basic retry mechanism
 *
 */
public class RetryStrategy {
	public final static int DEFAULT_RETRY_TIMES = 10;
	public final static long DEFAULT_SLEEP_TIMEOUT = 500;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Number of retries
	 */
	protected int retryTimes;
	
	/**
	 * Wait for the interval during each retry
	 */
	protected long sleepTimeout;
	
	/**
	 * Ignorable Exception type list
	 */
	protected List<Class<? extends RuntimeException>> _ignoredExceptions = new ArrayList<Class<? extends RuntimeException>>();

	public RetryStrategy() {
		this(DEFAULT_RETRY_TIMES, DEFAULT_SLEEP_TIMEOUT);
	}

	public RetryStrategy(int retryTimes) {
		this(retryTimes, DEFAULT_SLEEP_TIMEOUT);
	}

	public RetryStrategy(int retryTimes, long sleepTimeout) {
		this.retryTimes = retryTimes;
		this.sleepTimeout = sleepTimeout;
		ignoring(NotFoundException.class);
	}

	/**
	 * Add a negligible Exception
	 * @param exceptionType
	 */
	public void ignoring(Class<? extends RuntimeException> exceptionType) {
		_ignoredExceptions.add(exceptionType);
	}

	/**
	 * Add a negligible Exception
	 * @param exceptionTypeList
	 */
	public void ignoring(Collection<Class<? extends RuntimeException>> exceptionTypeList) {
		_ignoredExceptions.addAll(exceptionTypeList);
	}

	/**
	 * Try again and check the result
	 * When retry has still not achieve expected results, throws RetryException
	 * @param executor
	 * @param checker
	 */
	public void retryAndUntil(IOperationExecutor executor, IOperationChecker checker) {
		RuntimeException lastException = null;
		for (int i = 0; i < retryTimes; i++) {
			logger.info(String.format("round %d", i));
			try {
				// Perform actions
				executor.execute();
				
				// Check result
				if (checker.check()) {
					// To achieve its objectives, exit retry
					return;
				}
				sleep();
			} catch (RuntimeException e) {
				if (!isIgnored(e)) {
					throw e;
				}
				
				lastException = e;
				sleep();
			}
		}
		
		if (null == lastException) {
			throw new RetryException(retryTimes);
		}
		else {
			throw new RetryException(retryTimes, lastException);
		}
	}

	private boolean isIgnored(RuntimeException e) {
		boolean result = false;
		for (Class<? extends RuntimeException> ignoredException : _ignoredExceptions) {
			if (ignoredException.isInstance(e)) {
				result = true;
				break;
			}
		}
		
		return result;
	}

	private void sleep() {
		try {
			Thread.sleep(sleepTimeout);
		} catch (InterruptedException e) {
			// do nothing
		}
	}
}
