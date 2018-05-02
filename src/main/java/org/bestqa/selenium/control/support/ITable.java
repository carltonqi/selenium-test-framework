package org.bestqa.selenium.control.support;

import java.util.List;

import org.bestqa.selenium.control.IControl;

/**
 * Abstract interface for Table which is general method to define Table
 *
 */
public interface ITable extends IControl {
	/**
	 * Get the count of table column
	 */
	public int getColumnsCount();
	
	/**
	 * Get the count of body row
	 */
	public int getBodyRowsCount();
	
	/**
	 * Is there a data row for body
	 * @return <b>true</b>: there is a data row, <b>false</b>: there in not a data row
	 */
	public boolean hasBodyRow();
	
	/**
	 * Get table cell
	 * @param rowIndex Row index, starting from 0
	 * @param columnIndex Column index, starting from 0
	 */
	public ITableCell getBodyCell(int rowIndex, int columnIndex);
	
	/**
	 * Get table row
	 * @param rowIndex Row index, starting from 0
	 * @return
	 */
	public ITableRow getBodyRow(int rowIndex);
	
	/**
	 * To find data that meets the conditions.
	 * @return First data rows that meet the criteria, if there are no matches returns null
	 */
	public ITableRow findBodyRow(ITableRowLocator locator);
	
	/**
	 * To find data that meets the conditions
	 * @return Data list
	 */
	public List<ITableRow> findBodyRows(ITableRowLocator locator);
	
	/**
	 * Gets the data column in the table
	 * @author
	 * @param columnIndex Column index, starting from 0
	 */
	public ITableColumn getBodyColumn(int columnIndex);
	
	/**
	 * Gets the table header row
	 */
	public ITableRow getHeaderRow();
	
	/**
	 * Gets the table header row 
	 * @param rowIndex Row index, starting from 0
	 * @return
	 */
	public ITableRow getHeaderRow(int rowIndex);
	
	/**
	 * Gets the table footer row
	 */
	public ITableRow getFooterRow();
	
	/**
	 * Gets the table footer row
	 * @param rowIndex Row index, starting from 0
	 */
	public ITableRow getFooterRow(int rowIndex);

}
