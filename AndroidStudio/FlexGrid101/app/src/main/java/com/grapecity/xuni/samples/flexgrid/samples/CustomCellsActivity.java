package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.CustomPerformanceCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class CustomCellsActivity extends Activity
{
	private FlexGrid mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_cells);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setAutoGenerateColumns(false);
		
		mGrid.setItemsSource(Customer.getList(100));
		
		GridColumn firstNameColumn = new GridColumn(mGrid, "First Name", "firstName");
		firstNameColumn.setWidth("*");
		
		GridColumn lastNameColumn = new GridColumn(mGrid, "Last Name", "lastName");
		lastNameColumn.setWidth("*");
		
		GridColumn orderCountColumn = new GridColumn(mGrid, "Order Count", "orderCount");
		orderCountColumn.setWidth("*");
		orderCountColumn.setReadOnly(true);
		
		mGrid.getColumns().add(firstNameColumn);
		mGrid.getColumns().add(lastNameColumn);
		mGrid.getColumns().add(orderCountColumn);

		mGrid.setCellFactory(new CustomPerformanceCellFactory(mGrid, this));
	}
}
