package org.bestqa.selenium.intercept;

import java.lang.reflect.Method;
import java.util.ArrayList;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * MethodInterceptController list
 *
 */
public class MethodInterceptControllerList extends ArrayList<MethodInterceptController> implements MethodInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7414159087216181390L;

	/**
	 * Override the add method
	 * If already in the list, do not repeat to add
	 * @param item
	 * @return
	 */
	@Override
	public boolean add(MethodInterceptController item) {
		if (null == item) {
			return false;
		}
		
		if (this.contains(item)) {
			return false;
		}
		
		boolean result = super.add(item);
		// To Identify the MethodInterceptController object as being managed by the MethodInterceptControllerList
		item.setInList(true);
		
		return result;
	}

	/**
	 * Methods to intercept
	 * Traverse the internal interceptors, it is no longer perform others if there is a set of ones
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		for (MethodInterceptController controller : this) {
			if (controller.doFilter(obj, method, args, proxy)) {
				return controller.intercept(obj, method, args, proxy);
			}
		}
		
		return proxy.invokeSuper(obj, args);
	}
}
