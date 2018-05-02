package org.bestqa.selenium.control.support;

import java.util.List;

/**
 * Abstract interface of table column
 *
 */
public interface ITableColumn {
	/**
	 * Get table cell
	 * @param rowIndex row index, starting from 0
	 */
	public ITableCell getCell(int rowIndex);
	
	/**
	 * Get all table cells
	 */
	public List<ITableCell> getAllCells();
	
	/**
	 * Get the count of rows
	 */
	public int getRowsCount();
	
	/**
	 * Get the column index
	 * @return The index which starts from 0
	 */
	public int getColumnIndex();
}
