package com.grapecity.xuni.samples.flexgrid;

public class ColumnModel
{
	private String columnName;
	private int columnWidth;

	public ColumnModel(String columnName, int columnWidths)
	{
		this.columnName = columnName;
		this.columnWidth = columnWidths;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String name)
	{
		this.columnName = name;
	}

	public int getColumnWidth()
	{
		return columnWidth;
	}

	public void setColumnWidth(int description)
	{
		this.columnWidth = description;
	}
}
