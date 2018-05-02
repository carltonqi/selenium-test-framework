package org.bestqa.selenium.control.support;

import java.util.Hashtable;

/**
 * Table column processor class
 * @param <T>
 */
public class TableColumnProcessor<T extends TableCellProcessor> {
	private Class<T> cellProcessorClass;
	private ITableColumn column;
	private Hashtable<Integer, T> cache;
	
	public TableColumnProcessor(ITableColumn column, Class<T> cellProcessorClass) {
		if (null == column) {
			throw new IllegalArgumentException("Arg column can not be null.");
		}

		if (null == cellProcessorClass) {
			throw new IllegalArgumentException("Arg cellProcessorClass can not be null.");
		}

		this.cellProcessorClass = cellProcessorClass;
		this.column = column;
		this.cache = new Hashtable<Integer, T>();
	}
	
	/**
	 * Get table row according to row index
	 * @param rowIndex The row index
	 */
	public T getRow(int rowIndex) {
		if (!cache.containsKey(rowIndex)) {
			cache.put(rowIndex, TableCellProcessor.createInstance(column.getCell(rowIndex), cellProcessorClass));
		}
		
		return cache.get(rowIndex);
	}
	
	/**
	 * Get the count of row
	 */
	public int getRowsCount() {
		return column.getRowsCount();
	}
}
