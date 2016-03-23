package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class StarSizingActivity extends Activity
{
	private FlexGrid mGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_getting_started);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setAutoGenerateColumns(false);
		
		mGrid.setItemsSource(Customer.getList(100));

		// initialize new columns
		GridColumn firstNameColumn = new GridColumn(mGrid, "First Name", "firstName");
		firstNameColumn.setWidth("*");
		
		GridColumn lastNameColumn = new GridColumn(mGrid, "Last Name", "lastName");
		lastNameColumn.setWidth("*");
		

		GridColumn lastOrderDateColumn = new GridColumn(mGrid, "Last Order Time", "lastOrderDate");
		lastOrderDateColumn.setFormat("hh:mm a");
		lastOrderDateColumn.setWidth("*");
		
		GridColumn orderTotalColumn = new GridColumn(mGrid, "Order Total", "orderTotal");
		orderTotalColumn.setFormat("$#.##");
		orderTotalColumn.setWidth("*");

		// add new columns
		mGrid.getColumns().add(firstNameColumn);
		mGrid.getColumns().add(lastNameColumn);
		mGrid.getColumns().add(lastOrderDateColumn);
		mGrid.getColumns().add(orderTotalColumn);
	}
}
