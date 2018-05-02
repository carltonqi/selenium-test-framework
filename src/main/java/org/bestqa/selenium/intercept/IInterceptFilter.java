package org.bestqa.selenium.intercept;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

/**
 * The dynamic injection filter
 *
 */
public interface IInterceptFilter {
	/**
	 * filter
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 * @return true: continue to inject; false: Stop injection
	 */
	public boolean doFilter(Object obj, Method method, Object[] args, MethodProxy proxy);
}
