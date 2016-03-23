package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.core.DataType;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridDataMap;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.TimeEditorCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class ColumnDefinitionsActivity extends Activity
{
	private FlexGrid mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_column_definitions);

		// initializing grid
		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		// auto column generation disabled in activity_column_definitions.xml
		// mGrid.setAutoGenerateColumns(false);

		mGrid.setItemsSource(Customer.getList(100));

		// initialize new columns
		GridColumn idColumn = new GridColumn(mGrid, "Id", "id");
		idColumn.setReadOnly(true);

		GridColumn firstNameColumn = new GridColumn(mGrid, "First Name", "firstName");
		GridColumn lastNameColumn = new GridColumn(mGrid, "Last Name", "lastName");

		GridColumn orderTotalColumn = new GridColumn(mGrid, "Order Total", "orderTotal");
		orderTotalColumn.setFormat("$#.##");

		GridColumn countryColumn = new GridColumn(mGrid, "Country", "countryId");
		countryColumn.setDataMap(new GridDataMap(Customer.getCounties(), "countryId", "countryName"));
		countryColumn.setShowDropDown(true);

		GridColumn lastOrderDateColumn = new GridColumn(mGrid, "Last Order Time", "lastOrderDate");
		
		//use a different time format for Japanese
		if(getResources().getBoolean(R.bool.isJapanese))
		{
			lastOrderDateColumn.setFormat("k:mm");
		}
		else
		{
			lastOrderDateColumn.setFormat("hh:mm a");	
		}
			
		
		

		GridColumn activeColumn = new GridColumn(mGrid, "Active", "active");
		activeColumn.setDataType(DataType.BOOLEAN);
		
		// add new columns
		mGrid.getColumns().add(idColumn);
		mGrid.getColumns().add(firstNameColumn);
		mGrid.getColumns().add(lastNameColumn);
		mGrid.getColumns().add(orderTotalColumn);
		mGrid.getColumns().add(countryColumn);
		mGrid.getColumns().add(lastOrderDateColumn);
		mGrid.getColumns().add(activeColumn);
		
		mGrid.setCellFactory(new TimeEditorCellFactory(mGrid));
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
