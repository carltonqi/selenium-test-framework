package org.bestqa.selenium.utils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Web related helpers
 */
public class RequestUtils {
	public static String toQueryString(Hashtable<String, String> paramTable) {
		if (null == paramTable || 0 == paramTable.size()) {
			return "";
		}

		Iterator<Entry<String, String>> iterator = paramTable.entrySet().iterator();
		StringBuilder builder = new StringBuilder();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			builder.append(entry.getKey() + "=" + entry.getValue());
			if (iterator.hasNext()) {
				builder.append("&");
			}
		}

		return builder.toString();
	}
}
