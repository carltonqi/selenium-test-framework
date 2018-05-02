package org.bestqa.selenium.intercept;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Dynamically injected entry classes in the framework <br />
 * Provides a unified dynamic injection logic
 *
 */
public class MethodInterceptController implements MethodInterceptor {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * The patterns of listener implementation
	 *
	 */
	public enum InvokeMode {
		/**
		 * In the form of a stack of execution, listenerA -> listenerB -> method -> listenerB -> listenerA
		 */
		Stack,
		/**
		 * In the form of a queue of execution, listenerA -> listenerB -> method -> listenerA -> listenerB
		 */
		Queue,
	}

	private InvokeMode mode = InvokeMode.Queue;
	private List<IInterceptFilter> filterList = new ArrayList<IInterceptFilter>();
	private List<IInterceptListener> listenerList = new ArrayList<IInterceptListener>();
	private boolean isInList = false;
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object result = null;
		// First filter, if false is not dynamically injection
		// Determine whether it is managed by MethodInterceptControllerList, reduce duplication of doFilter action
		if (!this.isInList && !this.doFilter(obj, method, args, proxy)) {
			// Calls the actual method
			result = proxy.invokeSuper(obj, args);
		}
		else {
			IInterceptListener[] listeners = new IInterceptListener[this.listenerList.size()];
			listeners = this.listenerList.toArray(listeners);
			
			// Trigger onBeforeInvoke
			this.fireOnBeforeEvent(obj, method, args, proxy);
			
			// Calls the actual method
			try {
				result = proxy.invokeSuper(obj, args);
			}
			catch (Exception ex) {
				this.fireOnExceptionOccurs(obj, method, args, ex);
				throw ex;
			}
			
			// Trigger onAfterInvoke
			this.fireOnAfterEvent(obj, method, args, proxy);
		}

		return result;
	}
	
	/**
	 * The patterns of listener implementation
	 */
	public InvokeMode getInvokeMode() {
		return this.mode;
	}
	
	/**
	 * Set the invoking mode which is executed for listeners
	 * @param mode
	 */
	public void setInvokeMode(InvokeMode mode) {
		if (null == mode) {
			throw new IllegalArgumentException("InvokeMode could not be null.");
		}
		
		this.mode = mode;
	}
	
	/**
	 * Add a filter
	 * @param filter
	 */
	public void addFilter(IInterceptFilter filter) {
		if (null == filter) {
			throw new IllegalArgumentException("IInjectionFilter could not be null.");
		}
		
		this.filterList.add(filter);
	}
	
	/**
	 * Add a listener
	 * @param listener
	 */
	public void addListener(IInterceptListener listener) {
		if (null == listener) {
			throw new IllegalArgumentException("IInjectionListener could not be null.");
		}
		
		this.listenerList.add(listener);
	}

	/**
	 * Execute a filter
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 */
	public boolean doFilter(Object obj, Method method, Object[] args, MethodProxy proxy) {
		for (IInterceptFilter filter : this.filterList) {
			try {
				if (!filter.doFilter(obj, method, args, proxy)) {
					return false;
				}
			}
			catch (Exception ex) {
				this.logger.error("Error occurs when doFilter event is triggered.");
				this.logger.error(ex.getMessage(), ex);
			}
		}
		return true;
	}
	
	/**
	 * Trigger onBeforeInvoke events
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 */
	private void fireOnBeforeEvent(Object obj, Method method, Object[] args, MethodProxy proxy) {
		IInterceptListener[] listeners = new IInterceptListener[this.listenerList.size()];
		listeners = this.listenerList.toArray(listeners);
		
		for (int i = 0; i < listeners.length; i++) {
			try {
				listeners[i].onBeforeInvoke(obj, method, args, proxy);
			}
			catch (Exception ex) {
				this.logger.error("Error occurs when onBeforeInvoke event is triggered.");
				this.logger.error(ex.getMessage(), ex);
			}
		}
	}
	
	/**
	 * Trigger onAfterInvoke events
	 * @param obj
	 * @param method
	 * @param args
	 * @param proxy
	 */
	private void fireOnAfterEvent(Object obj, Method method, Object[] args, MethodProxy proxy) {
		IInterceptListener[] listeners = new IInterceptListener[this.listenerList.size()];
		listeners = this.listenerList.toArray(listeners);

		// Trigger onAfterInvoke
		if (InvokeMode.Queue.equals(this.mode)) {
			// Execute listener according to FIFO
			for (int i = 0; i < listeners.length; i++) {
				try {
					listeners[i].onAfterInvoke(obj, method, args, proxy);
				}
				catch (Exception ex) {
					this.logger.error("Error occurs when onAfterInvoke event is triggered.");
					this.logger.error(ex.getMessage(), ex);
				}
			}
		}
		else {
			// Execute listener according to LIFO
			for (int i = listeners.length; i > 0; i--) {
				try {
					listeners[i - 1].onAfterInvoke(obj, method, args, proxy);
				}
				catch (Exception ex) {
					this.logger.error("Error occurs when onAfterInvoke event is triggered.");
					this.logger.error(ex.getMessage(), ex);
				}
			}
		}
	}
	
	/**
	 * Trigger onExceptionOccurs events
	 * @param obj
	 * @param method
	 * @param args
	 * @param ex
	 */
	private void fireOnExceptionOccurs(Object obj, Method method, Object[] args, Exception ex) {
		IInterceptListener[] listeners = new IInterceptListener[this.listenerList.size()];
		listeners = this.listenerList.toArray(listeners);

		for (int i = 0; i < listeners.length; i++) {
			try {
				listeners[i].onExceptionOccurs(obj, method, args, ex);
			}
			catch (Exception innerEx) {
				this.logger.error("Error occurs when onBeforeInvoke event is triggered.");
				this.logger.error(innerEx.getMessage(), innerEx);
			}
		}
	}
	
	/**
	 * Whether it is placed into MethodInterceptControllerList or not
	 * @param value
	 */
	protected void setInList(boolean value) {
		this.isInList = value;
	}
}
