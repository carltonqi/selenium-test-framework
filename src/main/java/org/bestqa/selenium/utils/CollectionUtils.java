package org.bestqa.selenium.utils;

import java.util.List;

/**
 * Collection utilities class
 *
 */
public class CollectionUtils {
    /**
     * List converts to Object[][]
     * @param source
     */
    public static Object[][] toTwoDimensionArray(List<Object[]> source) {
        Object[][] result = new Object[source.size()][];
        
        for (int i = 0; i < source.size(); i++) {
            Object[] sourceItem = source.get(i);
            result[i] = new Object[sourceItem.length];
            for (int j = 0; j < sourceItem.length; j++) {
                result[i][j] = sourceItem[j];
            }
        }
        
        return result;
    }
}
