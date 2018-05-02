package org.bestqa.selenium.control.support;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Table row processor class<br />
 *
 */
public abstract class TableRowProcessor {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected ITableRow row;
	protected WebDriver driver;
	private Hashtable<Integer, TableCellProcessor> cache;

	public TableRowProcessor(ITableRow row) {
		if (null == row) {
			throw new IllegalArgumentException("Arg row can not be null.");
		}
		
		this.row = row;
		this.driver = row.getWrappedDriver();
		cache = new Hashtable<Integer, TableCellProcessor>();
	}

	/**
	 * 根据columnIndex查找匹配的单元格
	 * Get table cell according to column index
	 * @param columnIndex The column index
	 * @param processorClass The processor class
	 */
	@SuppressWarnings("unchecked")
	protected <T extends TableCellProcessor> T getCell(int columnIndex, Class<T> processorClass) {
		if (!cache.containsKey(columnIndex)) {
			cache.put(columnIndex, TableCellProcessor.createInstance(row.getCell(columnIndex), processorClass));
		}
		
		return (T) cache.get(columnIndex);
	}
}
