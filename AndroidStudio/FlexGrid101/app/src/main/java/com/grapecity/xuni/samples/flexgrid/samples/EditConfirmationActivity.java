package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.EditConfirmationCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class EditConfirmationActivity extends Activity
{
	private FlexGrid mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_edit_confirm);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setItemsSource(Customer.getList(100));

		mGrid.getColumns().getColumn("id").setReadOnly(true);
		
		mGrid.getColumns().getColumn("email").setVisible(false);
		mGrid.getColumns().getColumn("countryId").setVisible(false);
		mGrid.getColumns().getColumn("lastOrderDate").setVisible(false);
		mGrid.getColumns().getColumn("orderCount").setVisible(false);
		mGrid.getColumns().getColumn("orderTotal").setVisible(false);

		mGrid.setCellFactory(new EditConfirmationCellFactory(mGrid, EditConfirmationActivity.this));
	}
}
