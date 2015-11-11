package com.grapecity.xuni.samples.flexgrid.cellfactory;

import android.graphics.Color;
import android.graphics.Rect;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.FlexGridCanvasRenderEngine;
import com.grapecity.xuni.flexgrid.GridCellFactory;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridCellType;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

/**
 * Custom GridCellFactory to show how to perform conditional formatting on a cell by cell basis.
 * 
 * @author GrapeCity
 * 
 */
public class ConditionalFormattingCellFactory extends GridCellFactory
{
	private Customer mCustomer;

	public ConditionalFormattingCellFactory(FlexGrid flexGrid)
	{
		super(flexGrid);
	}

	@Override
	public void createCellContent(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
	{
		// condition to only perform custom formatting on cells under the column orderCount
		if (gridPanel.getCellType() == GridCellType.CELL && gridPanel.getColumns().get(cellRange.col).getName().equals("orderCount"))
		{
			// getting data object
			mCustomer = (Customer) gridPanel.getRows().get(cellRange.row).getDataItem();

			//change text color based on the order total
			if (mCustomer.getOrderCount() >= 50)
			{
				flexGrid.renderEngine.setFillColor(Color.GREEN);
				
			}
			else
			{
				flexGrid.renderEngine.setFillColor(Color.RED);
			}
		}

		// default method
		super.createCellContent(gridPanel, renderEngine, cellRange, bounds);
	}

}
