package org.bestqa.selenium.control.support;

import org.bestqa.selenium.control.support.ITableRow;

/**
 * The interface used to locate row in the table
 *
 */
public interface ITableRowLocator {
	/**
	 * To judge whether the line which it is looking for
	 * @param row Table row
	 * @return true, find the row; otherwise false
	 */
	boolean isTarget(ITableRow row);
}
