package com.grapecity.xuni.samples.flexgrid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;

import com.grapecity.xuni.flexgrid.*;
import com.grapecity.xuni.gauge.*;

public class CellFactory extends GridCellFactory
{

	private XuniGauge mPerformanceGauge;
	private CustomCellPoint mCellPoint;

	private int mCellWidth = 0;
	private int mCellHeight = 0;

	private int mPaddingLeft;
	private int mPaddingTop;

	public CellFactory(FlexGrid flexGrid, Context context)
	{
		super(flexGrid);

		// initializing radial gauge
		mPerformanceGauge = new XuniRadialGauge(context);
		mPerformanceGauge.setIsAnimated(false);
		mPerformanceGauge.setMin(50);
		mPerformanceGauge.setMax(100);
		mPerformanceGauge.setShowTextType(GaugeShowText.NONE);
		mPerformanceGauge.setShowRanges(false);

		// initializing ranges
		GaugeRange rangeGreen = new GaugeRange();
		GaugeRange rangeYellow = new GaugeRange();
		GaugeRange rangeRed = new GaugeRange();

		rangeGreen.setMin(50);
		rangeGreen.setMax(80);
		rangeGreen.setColor(Color.GREEN);

		rangeYellow.setMin(80);
		rangeYellow.setMax(90);
		rangeYellow.setColor(Color.YELLOW);

		rangeRed.setMin(90);
		rangeRed.setMax(100);
		rangeRed.setColor(Color.RED);

		// adding ranges to the gauge
		mPerformanceGauge.getRanges().add(rangeGreen);
		mPerformanceGauge.getRanges().add(rangeYellow);
		mPerformanceGauge.getRanges().add(rangeRed);

		// setting visibility and layout for the gauge
		mPerformanceGauge.setVisibility(View.INVISIBLE);
		mPerformanceGauge.setLayoutParams(new LayoutParams(0, 0));

		// adding gauge to the view
		flexGrid.addView(mPerformanceGauge);

		// initializing padding for the gauge
		mPaddingLeft = Math.round(FlexGridCanvasRenderEngine.getDimensionSize(flexGrid.getCellPaddingLeft()));
		mPaddingTop = Math.round(FlexGridCanvasRenderEngine.getDimensionSize(flexGrid.getCellPaddingTop()));
	}

	@Override
	public void createCellContent(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
	{

		// condition to check cells under column Performance
		if (gridPanel.getCellType().equals(GridCellType.CELL) && gridPanel.getColumns().get(cellRange.col).getName().equals("Performance"))
		{
			// getting data object
			mCellPoint = (CustomCellPoint) gridPanel.getRows().get(cellRange.row).getDataItem();

			// setting the height and width for the gauge
			if (bounds.width() != mCellWidth && bounds.height() != mCellHeight)
			{
				this.mCellWidth = bounds.width();
				this.mCellHeight = bounds.height();
				mPerformanceGauge.setLayoutParams(new LayoutParams(mCellWidth - (mPaddingLeft * 2), mCellHeight - (mPaddingTop * 2)));
			}

			mPerformanceGauge.setValue(mCellPoint.getPerformanceGauge());

			// rendering the gauge on the canvas, saving its previous state
			renderEngine.canvas.save();
			renderEngine.canvas.translate(bounds.left + renderEngine.panX + mPaddingLeft, bounds.top + renderEngine.panY + mPaddingTop);

			mPerformanceGauge.draw(renderEngine.canvas);
			mPerformanceGauge.plotAreaView.draw(renderEngine.canvas);

			renderEngine.canvas.restore();
		}
		else
			// default method
			super.createCellContent(gridPanel, renderEngine, cellRange, bounds);
	}

}
