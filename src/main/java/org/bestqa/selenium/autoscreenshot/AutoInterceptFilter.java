package org.bestqa.selenium.autoscreenshot;

import java.lang.reflect.Method;

import org.bestqa.selenium.intercept.IInterceptFilter;

import net.sf.cglib.proxy.MethodProxy;

/**
 * Filter based on tags AutoIntercept
 *
 */
public class AutoInterceptFilter implements IInterceptFilter {
	private InterceptLevel level = InterceptLevel.P4;
	
	public AutoInterceptFilter() {
		// empty
	}
	
	public AutoInterceptFilter(InterceptLevel level) {
		if (null != level) {
			this.level = level;
		}
	}
	
	@Override
	public boolean doFilter(Object obj, Method method, Object[] args,
			MethodProxy proxy) {
		AutoIntercept annotation = method.getAnnotation(AutoIntercept.class);
		if (null == annotation) {
			return false;
		}
		
		return annotation.injectionLevel().getValue() <= this.level.getValue();
	}
}
