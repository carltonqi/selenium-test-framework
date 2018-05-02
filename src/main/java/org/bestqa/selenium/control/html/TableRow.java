package org.bestqa.selenium.control.html;

import java.util.ArrayList;
import java.util.List;

import org.bestqa.selenium.basic.HtmlTags;
import org.bestqa.selenium.control.Control;
import org.bestqa.selenium.control.support.ITableCell;
import org.bestqa.selenium.control.support.ITableRow;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Table row，apply to "&lt;tr&gt;" element
 *
 */
public class TableRow extends Control implements ITableRow {
	private static final String CELL_XPATH = "./*[%s]";
	private int rowIndex = 0;

	public TableRow(WebElement webElement) {
		super(webElement);
	}
	
	public TableRow(WebElement webElement, int rowIndex) {
		this(webElement);
		
		this.rowIndex = rowIndex;
	}

	/**
	 * Get table cell
	 * @param columnIndex column number，starting from 0
	 * @return Returns "&lt;td&gt;"  or "&lt;th&gt;"
	 */
	@Override
	public ITableCell getCell(int columnIndex) {
		int index = columnIndex + 1;
		String xpath = String.format(CELL_XPATH, index);
		return new TableCell(this.findElement(By.xpath(xpath)));
	}

	/**
	 * Get column count <br />
	 */
	@Override
	public int getColumnsCount() {
		List<String> tags  = new ArrayList<String>();
		tags.add(HtmlTags.TD);
		tags.add(HtmlTags.TH);
		
		return this.getChildNodesCountByTagName(this, tags);
	}

	@Override
	public int getRowIndex() {
		return this.rowIndex;
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
