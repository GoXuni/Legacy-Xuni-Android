package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;

import com.grapecity.xuni.core.Aggregate;
import com.grapecity.xuni.core.DataType;
import com.grapecity.xuni.core.PropertyGroupDescription;
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

public class GroupingActivity extends Activity
{
	private FlexGrid mGrid;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grouping);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		// set in xml file
		// mGrid.setShowGroups(true);

		mGrid.setAutoGenerateColumns(false);

		mGrid.setItemsSource(Customer.getList(100));

		// group on the country column
		mGrid.getCollectionView().getGroupDescriptions().add(new PropertyGroupDescription("countryId"));
		mGrid.setGroupHeaderFormat(getResources().getString(R.string.groupHeaderFormat));

		GridColumn activeColumn = new GridColumn(mGrid, "Active", "active");
		activeColumn.setDataType(DataType.BOOLEAN);

		GridColumn nameColumn = new GridColumn(mGrid, "Name", "firstName");
		nameColumn.setItemFormatter(mNameColumnFormatter);

		GridColumn orderTotalColumn = new GridColumn(mGrid, "Order Total", "orderTotal");
		orderTotalColumn.setFormat("$#.##");

		// sum the order total for each country
		orderTotalColumn.setAggregate(Aggregate.SUM);

		GridColumn countryColumn = new GridColumn(mGrid, "Country", "countryId");
		countryColumn.setMinWidth(100);
		countryColumn.setWidth("*");
		countryColumn.setDataMap(new GridDataMap(Customer.getCounties(), "countryId", "countryName"));
		countryColumn.setShowDropDown(true);

		mGrid.getColumns().add(activeColumn);
		mGrid.getColumns().add(nameColumn);
		mGrid.getColumns().add(orderTotalColumn);
		mGrid.getColumns().add(countryColumn);
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
