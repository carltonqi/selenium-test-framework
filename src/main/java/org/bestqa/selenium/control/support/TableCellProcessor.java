package org.bestqa.selenium.control.support;

import java.lang.reflect.Constructor;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Table cell processor class
 *
 */
public abstract class TableCellProcessor {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected ITableCell cell;
	protected WebDriver driver;
	
	/**
	 * A TableCellProcessor instance obtained through reflection
	 * @param <T> TableCellProcessor type
	 * @param cell
	 * @param processorClass
	 * @return
	 */
	public static <T extends TableCellProcessor> T createInstance(ITableCell cell, Class<T> processorClass) {
		try {
			Constructor<T> constructor = processorClass.getConstructor(new Class[]{ ITableCell.class});

			return constructor.newInstance(cell);
		} catch (Exception e) {
			throw new RuntimeException("Create TableCellProcessor failed.", e);
		}
	}

	public TableCellProcessor(ITableCell cell) {
		if (null == cell) {
			throw new IllegalArgumentException("Argument cell cannot be null.");
		}

		this.cell = cell;
		this.driver = cell.getWrappedDriver();
	}
}
