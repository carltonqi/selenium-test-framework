package org.bestqa.selenium.control.html;

import java.util.ArrayList;
import java.util.List;

import org.bestqa.selenium.basic.HtmlTags;
import org.bestqa.selenium.control.Control;
import org.bestqa.selenium.control.support.ITableCell;
import org.bestqa.selenium.control.support.ITableColumn;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Table column
 *
 */
public class TableColumn extends Control implements ITableColumn {
	
	private int columnIndex;
	
	public TableColumn(WebElement tableBodyElement, int columnIndex) {
		super(tableBodyElement);

		this.columnIndex = columnIndex;
	}
	
	/**
	 * Get table cell
	 * @param rowIndex Line index, starting from 0
	 * @return Returns "&lt;td&gt;" or "&lt;th&gt;" element
	 */
	@Override
	public ITableCell getCell(int rowIndex) {
		// Xpath in the array subscript starting from 1
		int row = rowIndex + 1;
		int column = this.columnIndex + 1;
		String xpath = String.format(Table.CELL_XPATH, row, column);
		return new TableCell(this.findElement(By.xpath(xpath)));
	}

	/**
	 * Get row number
	 */
	@Override
	public int getRowsCount() {
		return this.getChildNodesCountByTagName(this, HtmlTags.TR);
	}

	/**
	 * Get all the cells
	 */
	@Override
	public List<ITableCell> getAllCells() {
		List<ITableCell> result = new ArrayList<ITableCell>();
		
		for (int i = 0; ;i++) {
			try {
				result.add(this.getCell(i));
			}
			catch (NotFoundException ex) {
				break;
			}
		}
		
		return result;
	}

	@Override
	public int getColumnIndex() {
		return this.columnIndex;
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
