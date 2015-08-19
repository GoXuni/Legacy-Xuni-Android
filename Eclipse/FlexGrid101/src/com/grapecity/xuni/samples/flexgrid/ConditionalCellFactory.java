package com.grapecity.xuni.samples.flexgrid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;

import com.grapecity.xuni.flexgrid.*;

public class ConditionalCellFactory extends GridCellFactory
{
	private ChartPoint mCellPoint;

	public ConditionalCellFactory(FlexGrid flexGrid, Context context)
	{
		super(flexGrid);
	}

	@Override
	public void createCellContent(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
	{
		// condition to check cells under column Weight
		if (gridPanel.getCellType().equals(GridCellType.CELL) && gridPanel.getColumns().get(cellRange.col).getName().equals("weight"))
		{
			// getting data object
			mCellPoint = (ChartPoint) gridPanel.getRows().get(cellRange.row).getDataItem();
			if (mCellPoint.getWeight() > 70)
				flexGrid.renderEngine.setFillColor(Color.RED);
			else
				flexGrid.renderEngine.setFillColor(Color.GREEN);
		}

		// default method
		super.createCellContent(gridPanel, renderEngine, cellRange, bounds);
	}

}
