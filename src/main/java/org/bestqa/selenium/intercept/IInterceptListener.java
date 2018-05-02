package org.bestqa.selenium.intercept;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

/**
 * The interface of dynamic injection listener
 *
 */
public interface IInterceptListener {
	public void onBeforeInvoke(Object obj, Method method, Object[] args, MethodProxy proxy);
	
	public void onAfterInvoke(Object obj, Method method, Object[] args, MethodProxy proxy);
	
	public void onExceptionOccurs(Object obj, Method method, Object[] args, Exception ex);
}
