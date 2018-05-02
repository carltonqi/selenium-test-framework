package org.bestqa.selenium.autoscreenshot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * As the need for automatic injection
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AutoIntercept {
	/**
	 * Injection level, the default is Debug
	 * @return
	 */
	public abstract InterceptLevel injectionLevel() default InterceptLevel.P4;
}
