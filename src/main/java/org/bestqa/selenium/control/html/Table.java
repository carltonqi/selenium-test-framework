package org.bestqa.selenium.control.html;

import java.util.ArrayList;
import java.util.List;

import org.bestqa.selenium.basic.HtmlTags;
import org.bestqa.selenium.control.Control;
import org.bestqa.selenium.control.LazyLoadControl;
import org.bestqa.selenium.control.support.ITable;
import org.bestqa.selenium.control.support.ITableCell;
import org.bestqa.selenium.control.support.ITableColumn;
import org.bestqa.selenium.control.support.ITableRow;
import org.bestqa.selenium.control.support.ITableRowLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

@LazyLoadControl
public class Table extends Control implements ITable {
	/**
	 * Xpath of table row
	 */
	public static final String ROW_XPATH = "./tr[%s]";
	/**
	 * Xpath of table cell
	 */
	public static final String CELL_XPATH = "./tr[%s]/*[%s]";
	
	private WebElement tbody;
	private WebElement thead;
	private WebElement tfoot;
	
	protected Table() {
		// do nothing, for lazyload & cglib
	}

	public Table(WebElement webElement) {
		super(webElement);
	}

	public Table(WebDriver driver, String id) {
		super(driver, id);
	}

	public Table(WebDriver driver, By by) {
		super(driver, by);
	}
	
	@Override
	public ITableRow findBodyRow(ITableRowLocator locator) {
		ITableRow result = null;
		
		for (int i = 0; ; i++) {
			ITableRow row = null;
			try {
				row = this.getBodyRow(i);
			}
			catch (NotFoundException ex) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ex.getMessage(), ex);
				}
				// More than the number of rows
				break;
			}
			
			if (locator.isTarget(row)) {
				result = row;
				break;
			}
		}
		
		return result;
	}

	@Override
	public List<ITableRow> findBodyRows(ITableRowLocator locator) {
		List<ITableRow> result = new ArrayList<ITableRow>();
		
		for (int i = 0; ; i++) {
			ITableRow row = null;
			try {
				row = this.getBodyRow(i);
			}
			catch (NotFoundException ex) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ex.getMessage(), ex);
				}
				// More than the number of rows
				break;
			}
			
			if (locator.isTarget(row)) {
				result.add(row);
			}
		}
		
		return result;
	}

	@Override
	public ITableCell getBodyCell(int rowIndex, int columnIndex) {
		// Xpath in the array subscript starting from 1
		int row = rowIndex + 1;
		int column = columnIndex + 1;
		String xpath = String.format(CELL_XPATH, row, column);
		return new TableCell(this.getBodyContainer().findElement(By.xpath(xpath)));
	}
	
	@Override
	public ITableColumn getBodyColumn(int columnIndex) {
		return new TableColumn(this.getBodyContainer(), columnIndex);
	}

	@Override
	public ITableRow getBodyRow(int rowIndex) {
		// Xpath in the array subscript starting from 1
		String xpath = String.format(ROW_XPATH, rowIndex + 1);
		return new TableRow(this.getBodyContainer().findElement(By.xpath(xpath)), rowIndex);
	}
	
	@Override
	public int getBodyRowsCount() {
		return this.getChildNodesCountByTagName(this.getBodyContainer(), HtmlTags.TR);
	}

	@Override
	public boolean hasBodyRow() {
		return 0 < this.getBodyRowsCount();
	}

	/**
	 * Gets the current column in the table. <br />
	 * It is usually consistent that both the columns of table header and table cell. <br />
	 * If there is a table header, returns the number of columns in the header; <br />
	 * If there is no header, gets the first data row, returns the number of columns. 
	 */
	@Override
	public int getColumnsCount() {
		int result = 0;
		ITableRow headerRow = this.getHeaderRow();
		if (null != headerRow) {
			// It is usually consistent that both the columns of table header and table cell
			result = headerRow.getColumnsCount();
		}
		else if (this.hasBodyRow()) {
			// Gets the first line, with the number of columns of the first row as number of columns of the table
			ITableRow row = this.getBodyRow(0);
			result = row.getColumnsCount();
		}
		
		return result;
	}

	@Override
	public ITableRow getFooterRow() {
		return getFooterRow(0);
	}

	/**
	 * Get the row of the footer
	 * @param rowIndex Line number, starting from 0
	 * @return Returns <b>null</b> if there is not "&lt;tfoot&gt;"
	 */
	@Override
	public ITableRow getFooterRow(int rowIndex) {
		ITableRow result = null;
		
		if (this.hasTFoot()) {
			// Xpath in the array subscript starting from 1
			String xpath = String.format(ROW_XPATH, rowIndex + 1);
			result = new TableRow(this.getTFoot().findElement(By.xpath(xpath)), rowIndex);
		}

		return result;
	}

	@Override
	public ITableRow getHeaderRow() {
		return getHeaderRow(0);
	}

	@Override
	public ITableRow getHeaderRow(int rowIndex) {
		ITableRow result = null;
		
		if (this.hasTHead()) {
			// Xpath in the array subscript starting from 1
			String xpath = String.format(ROW_XPATH, rowIndex + 1);
			result = new TableRow(this.getTHead().findElement(By.xpath(xpath)), rowIndex);
		}

		return result;
	}

	/**
	 * Get "&lt;tbody&gt;" element
	 */
	private WebElement getTBody() {
		if (null == this.tbody) {
			try {
				this.tbody = this.findElement(By.xpath(HtmlTags.TBODY));
			}
			catch (NotFoundException ex) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ex.getMessage(), ex);
				}
			}
		}
		
		return this.tbody;
	}
	
	/**
	 * Is there a "&lt;tbody&gt;" element
	 */
	private boolean hasTBody() {
		return null != this.getTBody();
	}
	
	/**
	 * Get the "&lt;thead&gt;" element
	 */
	private WebElement getTHead() {
		if (null == this.thead) {
			try {
				this.thead = this.findElement(By.xpath(HtmlTags.THEAD));
			}
			catch (NotFoundException ex) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ex.getMessage(), ex);
				}
			}
		}
		
		return this.thead;
	}
	
	/**
	 * Is there a "&lt;thead&gt;" element
	 */
	private boolean hasTHead() {
		return null != this.getTHead();
	}
	
	/**
	 * Get the "&lt;tfoot&gt;" element
	 */
	private WebElement getTFoot() {
		if (null == this.tfoot) {
			try {
				this.tfoot = this.findElement(By.xpath(HtmlTags.TFOOT));
			}
			catch (NotFoundException ex) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug(ex.getMessage(), ex);
				}
			}
		}
		
		return this.tfoot;
	}
	
	/**
	 * Is there a "&lt;tfoot&gt;" element 
	 */
	private boolean hasTFoot() {
		return null != this.getTFoot();
	}
	
	/**
	 * 获取包含表格主体的容器元素，如果没有&lt;tbody&gt;元素，就是&lt;table&gt;本身。
	 * Get container element which contains body of table <br />
	 * If there is no <tbody> element, then it is <table> itself.
	 */
	private WebElement getBodyContainer() {
		return this.hasTBody() ? this.getTBody() : this;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

}
