package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridDataMap;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.ConditionalFormattingCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class ConditionalFormattingActivity extends Activity
{
	private FlexGrid mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conditional_formatting);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setItemsSource(Customer.getList(100));

		mGrid.getColumns().getColumn("city").setVisible(false);
		mGrid.getColumns().getColumn("email").setVisible(false);
		mGrid.getColumns().getColumn("address").setVisible(false);
		mGrid.getColumns().getColumn("postalCode").setVisible(false);
		mGrid.getColumns().getColumn("active").setVisible(false);
		
		mGrid.getColumns().getColumn("id").setReadOnly(true);
		mGrid.getColumns().getColumn("orderTotal").setFormat("$#.##");
		mGrid.getColumns().getColumn("lastOrderDate").setFormat("MM/dd/yyyy");
		
		GridColumn countryColumn = mGrid.getColumns().getColumn("countryId");
		countryColumn.setName("country");
		countryColumn.setDataMap(new GridDataMap(Customer.getCounties(), "countryId", "countryName"));
		countryColumn.setShowDropDown(true);

		mGrid.setCellFactory(new ConditionalFormattingCellFactory(mGrid));
	}
}