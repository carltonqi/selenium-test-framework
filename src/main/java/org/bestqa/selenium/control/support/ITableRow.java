package org.bestqa.selenium.control.support;

import org.bestqa.selenium.control.IControl;

/**
 * Abstract interface of table row
 *
 */
public interface ITableRow extends IControl {
	/**
	 * Get table cell
	 * @param columnIndex The column index, starting from 0
	 */
	public ITableCell getCell(int columnIndex);
	
	/**
	 * Get the count of column
	 */
	public int getColumnsCount();
	
	/**
	 * Get the row index
	 * @return The row index which starts from 0
	 */
	public int getRowIndex();
}
