package org.bestqa.selenium.basic;

import java.util.Hashtable;

/**
 * Parse the CSS string
 *
 */
public class CssParser {
	/**
	 * Resolve CSS string to hash table, CSS properties as the key, value as the value
	 * @param cssString
	 * @return
	 */
	public static Hashtable<String, String> getCssTable(String cssString) {
		Hashtable<String, String> cssTable = new Hashtable<String, String>();
		
		String[] splitted = cssString.trim().split(";");
		for (String splitItem : splitted) {
			String[] subItem = splitItem.split(":");
			
			if (0 == subItem.length) {
				continue;
			}
			
			if (1 == subItem.length) {
				cssTable.put(subItem[0].toLowerCase().trim(), "");
			}
			
			if (2 == subItem.length) {
				cssTable.put(subItem[0].toLowerCase().trim(), subItem[1].toLowerCase().trim());
			}
		}
		
		return cssTable;
	}
}
