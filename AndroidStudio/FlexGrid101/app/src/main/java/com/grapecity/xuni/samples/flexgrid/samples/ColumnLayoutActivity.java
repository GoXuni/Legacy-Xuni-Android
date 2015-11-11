package com.grapecity.xuni.samples.flexgrid.samples;

import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridColumnCollection;
import com.grapecity.xuni.flexgrid.GridDataMap;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.ColumnModel;
import com.grapecity.xuni.samples.flexgrid.data.Customer;
import com.grapecity.xuni.samples.flexgrid.fragments.ColumnFragment;

public class ColumnLayoutActivity extends Activity implements ColumnFragment.OnDataPass
{
	private FlexGrid mGrid;

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
				bundle.putBooleanArray("visibles", getColumnVisibles());

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

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setItemsSource(Customer.getList(100));

		mGrid.getColumns().getColumn("id").setReadOnly(true);
		mGrid.getColumns().getColumn("orderTotal").setFormat("$#.##");
		mGrid.getColumns().getColumn("lastOrderDate").setFormat("MM/dd/yyyy");

		GridColumn countryColumn = mGrid.getColumns().getColumn("countryId");
		countryColumn.setName("country");
		countryColumn.setDataMap(new GridDataMap(Customer.getCounties(), "countryId", "countryName"));
		countryColumn.setShowDropDown(true);

		List<ColumnModel> columnList = null;

		try
		{
			columnList = ColumnModel.getFromPreferences(this);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		this.columnList = mGrid.getColumns();
		
		saveData(columnList);
	}

	protected boolean[] getColumnVisibles()
	{
		boolean[] columnVisibles = new boolean[columnList.size()];
		int i = 0;
		for (GridColumn c : columnList)
		{
			columnVisibles[i] = c.isVisible();
			i++;
		}
		return columnVisibles;
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
		if (columnList != null)
		{
			this.columnList.beginUpdate();

			for (ColumnModel cm : columnList)
			{
				this.columnList.getColumn(cm.getColumnName()).setVisible(cm.isVisible());
				GridColumn col = this.columnList.getColumn(cm.getColumnName());
				this.columnList.remove(col);
				this.columnList.add(col);
			}

			this.columnList.endUpdate();

			try
			{
				ColumnModel.saveToPreferences(this, columnList);
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}
}
