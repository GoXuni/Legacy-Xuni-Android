package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridDataMap;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.DateEditorCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class GettingStartedActivity extends Activity
{
	private FlexGrid mGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// FlexGrid automatically adjusts to the current theme
		setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_getting_started);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setItemsSource(Customer.getList(100));

		mGrid.getColumns().getColumn("id").setReadOnly(true);
		mGrid.getColumns().getColumn("orderTotal").setFormat("$#.##");
		mGrid.getColumns().getColumn("lastOrderDate").setFormat("MM/dd/yyyy");

		GridColumn countryColumn = mGrid.getColumns().getColumn("countryId");
		countryColumn.setName("Country");
		countryColumn.setDataMap(new GridDataMap(Customer.getCounties(), "countryId", "countryName"));
		countryColumn.setShowDropDown(true);

		mGrid.setCellFactory(new DateEditorCellFactory(mGrid));
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		// auto resize all the columns so that the text from each row is analyzed and sets the
		// column width to the highest text width.
		mGrid.autoSizeColumns(0, mGrid.getColumns().size() - 1);
	}
}
