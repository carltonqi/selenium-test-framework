package org.bestqa.selenium.utils;

import java.lang.reflect.Constructor;

/**
 * Reflection helpers
 *
 */
public class ReflectionUtils {
	/**
	 * According to parameter type, gets the appropriate constructor, 
	 * which can be used to find the parameter types do not match exactly, 
	 * but the parameter types in accordance with class inheritance.
	 * @param <T>
	 * @param targetClass
	 * @param parameters
	 * @throws NoSuchMethodException
	 */
	public static <T> Constructor<T> getMatchedConstructor(Class<T> targetClass, Object... parameters) throws NoSuchMethodException {
		if (null == targetClass) {
			throw new IllegalArgumentException("Paramter targetClass is null.");
		}
		
		for (int i = 0; i < parameters.length; i++) {
			if (null == parameters[i]) {
				throw new IllegalArgumentException("Null in parameters, index: " + i);
			}
		}
		
		Class<?>[] parameterTypes = new Class<?>[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			parameterTypes[i] = parameters[i].getClass();
		}
		
		return getMatchedConstructor(targetClass, parameterTypes);
	}
	
	/**
	 * According to parameter type, gets the appropriate constructor, 
	 * which can be used to find the parameter types do not match exactly, 
	 * but the parameter types in accordance with class inheritance.
	 * @param <T>
	 * @param targetClass
	 * @param parameterTypes
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	public static <T> Constructor<T> getMatchedConstructor(Class<T> targetClass, Class<?>...parameterTypes) throws NoSuchMethodException {
		Constructor<T>[] constructors = (Constructor<T>[]) targetClass.getConstructors();
		Constructor<T> result = null;
		for(Constructor<T> constructor : constructors) {
			boolean matched = true;
			
			Class<?>[] consParamTypes = constructor.getParameterTypes();
			if (consParamTypes.length != parameterTypes.length) {
				// Parameter length is different which can think that type does not match, 
				// Therefore, continue to the next constructor
				continue;
			}
			
			for (int i = 0; i < parameterTypes.length; i++) {
				if (!consParamTypes[i].isAssignableFrom(parameterTypes[i])) {
					// Parameter types do not match, continue to the next Constructor
					matched = false;
					break;
				}
			}
			
			if (!matched) {
				continue;
			}
			
			result = constructor;
			break;
		}
		
		if (null == result) {
			throw new NoSuchMethodException(targetClass.getName() + ".<init>" + argumentTypesToString(parameterTypes));
		}
		
		return result;
	}
	
    private static String argumentTypesToString(Class<?>[] argTypes) {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        if (argTypes != null) {
            for (int i = 0; i < argTypes.length; i++) {
                if (i > 0) {
                    buf.append(", ");
                }
		Class<?> c = argTypes[i];
		buf.append((c == null) ? "null" : c.getName());
            }
        }
        buf.append(")");
        return buf.toString();
    }
}
