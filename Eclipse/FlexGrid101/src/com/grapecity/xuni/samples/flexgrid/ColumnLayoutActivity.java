package com.grapecity.xuni.samples.flexgrid;

import java.util.List;

import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridColumnCollection;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ColumnLayoutActivity extends BaseChartPointActivity implements ColumnFragment.OnDataPass
{
	private android.app.FragmentManager mFragmentManager = getFragmentManager();
	private GridColumnCollection columnList;

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_edit, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch (item.getItemId())
		{
			case R.id.actionEdit:
				// saving column data
				Bundle bundle = new Bundle();
				bundle.putStringArray("names", getColumnNames());
				bundle.putIntArray("widths", getColumnWidths());

				// initializing and showing the fragment
				ColumnFragment columnManager = new ColumnFragment();
				columnManager.setArguments(bundle);
				columnManager.show(mFragmentManager, "Column Manager");
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// calling setContentView from parent class which initializes FlexGrid with data, column
		// formats and cell factory
		setContentView(R.layout.activity_column_layout);

		columnList = mGrid.getColumns();
	}

	protected int[] getColumnWidths()
	{
		int[] columnWidths = new int[columnList.size()];
		int i = 0;
		for (GridColumn c : columnList)
		{
			columnWidths[i] = c.getWidth();
			i++;
		}
		return columnWidths;
	}

	// get a String array of column names
	protected String[] getColumnNames()
	{
		String[] columnNames = new String[columnList.size()];
		int i = 0;
		for (GridColumn c : columnList)
		{
			columnNames[i] = c.getName();
			i++;
		}
		return columnNames;
	}

	// method to save data
	@Override
	public void saveData(List<ColumnModel> columnList)
	{
		this.columnList.beginUpdate();
		for (ColumnModel cm : columnList)
		{
			this.columnList.getColumn(cm.getColumnName()).setWidth(cm.getColumnWidth());
			GridColumn col = this.columnList.getColumn(cm.getColumnName());
			this.columnList.remove(col);
			this.columnList.add(col);
		}

		this.columnList.endUpdate();
	}
}
