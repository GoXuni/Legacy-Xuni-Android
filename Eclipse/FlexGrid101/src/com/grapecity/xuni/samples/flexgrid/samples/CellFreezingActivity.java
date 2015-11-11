package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.FlexGridCanvasRenderEngine;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridDataMap;
import com.grapecity.xuni.flexgrid.GridGroupRow;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.flexgrid.ItemFormatter;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class CellFreezingActivity extends Activity
{
	private FlexGrid mGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_cell_freezing);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);
		
		//set in xml layout
		//mGrid.setFrozenColumns(2);
		//mGrid.setFrozenRows(2);
		
		mGrid.setItemsSource(Customer.getList(100));
		
		mGrid.getColumns().getColumn("firstName").setVisible(false);
		mGrid.getColumns().getColumn("lastName").setVisible(false);
		
		mGrid.getColumns().getColumn("id").setReadOnly(true);
		mGrid.getColumns().getColumn("orderTotal").setFormat("$#.##");
		mGrid.getColumns().getColumn("lastOrderDate").setFormat("MM/dd/yyyy");

		GridColumn countryColumn = mGrid.getColumns().getColumn("countryId");
		countryColumn.setName("country");
		countryColumn.setDataMap(new GridDataMap(Customer.getCounties(), "countryId", "countryName"));
		countryColumn.setShowDropDown(true);
		
		//replace first/last name with name column
		GridColumn nameColumn = new GridColumn(mGrid, "name", "firstName");
		nameColumn.setItemFormatter(mNameColumnFormatter);
		
		mGrid.getColumns().add(0, nameColumn);
	}
	
	/**
	 * Custom formatter to generate the cell content.
	 */
	private ItemFormatter mNameColumnFormatter = new ItemFormatter()
	{
		Customer customer;

		@Override
		public String formatItem(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
		{
			//only format non group rows
			if (!(gridPanel.getRows().get(cellRange.row) instanceof GridGroupRow))
			{
				customer = (Customer) gridPanel.getRows().get(cellRange.row).getDataItem();

				return String.format("%s %s", customer.getFirstName(), customer.getLastName());
			}
			
			return "";
		}
	};
}
