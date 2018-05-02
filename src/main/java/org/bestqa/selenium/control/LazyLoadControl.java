package org.bestqa.selenium.control;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Control with delay loading
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LazyLoadControl {

	/**
	 * With the control of this statement must have a no arguments constructor
	 */
	public final String _must_have_a_null_argument_constructor = "_must_have_a_null_argument_constructor";
}
