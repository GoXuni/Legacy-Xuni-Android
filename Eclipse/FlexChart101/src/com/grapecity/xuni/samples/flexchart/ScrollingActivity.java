package com.grapecity.xuni.samples.flexchart;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.chartcore.*;
import com.grapecity.xuni.flexchart.*;

public class ScrollingActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scrolling);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("month");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesVolume = new ChartSeries(mChart, "Volume", "sales");
		ChartSeries seriesLow = new ChartSeries(mChart, "Low", "low");
		ChartSeries seriesHigh = new ChartSeries(mChart, "High", "high");

		// add series to list
		mChart.getSeries().add(seriesVolume);
		mChart.getSeries().add(seriesLow);
		mChart.getSeries().add(seriesHigh);

		// setting the source of data/items and default values in FlexChart
		mChart.setItemsSource(getList());
		seriesLow.setChartType(ChartType.SPLINE);
		seriesLow.setColor(Color.GREEN);
		seriesHigh.setChartType(ChartType.SPLINE);
		seriesHigh.setColor(Color.RED);

		// format y-axis
		// properties set in XMl layout
		mChart.getAxisY().setTitle("Precipitation (in)");
		// mChart.getAxisY().setTitleFontSize(15);
		mChart.getAxisY().setTitleFontColor(Color.parseColor("#88bde6"));
		mChart.getAxisX().setDisplayedRange(10d);
		mChart.getAxisY().setMinorGridVisible(false);
		mChart.getAxisY().setMajorGridVisible(true);
		mChart.getAxisY().setAxisLineVisible(false);
		
		// Set zoom mode X
		mChart.setZoomMode(ZoomMode.X);

		// create a new axis and customize it
		ChartAxis axisYRight = new ChartAxis(mChart, ChartPositionType.RIGHT);
		axisYRight.setTitle("Drag to scroll/Pinch to zoom");
		axisYRight.setTitleFontSize(15);
		axisYRight.setName("RIGHT");
		axisYRight.setAxisLineVisible(false);
		axisYRight.setMajorGridVisible(false);
		axisYRight.setMajorTickWidth(1);
		axisYRight.setMinorTickWidth(0);
		axisYRight.setAxisLineVisible(false);

		axisYRight.setTitleFontColor(Color.parseColor("#fbb258"));

		// add axis to the chart axes observable list
		mChart.getAxes().add(axisYRight);

		// tie the series to the axis
		seriesLow.setAxisY("RIGHT");
		seriesHigh.setAxisY("RIGHT");
		mChart.getLegend().setPosition(ChartPositionType.NONE);
	}

	// a method to create a list of random objects of type ChartPoint
	private static ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		int n = 25; // number of series elements

		Random random = new Random();
		for (int i = 0; i < n; i++)
		{
			list.add(new ChartPoint(random.nextInt(1000), (int) Math.tan(random.nextFloat() * 10) + 80, (int) Math.tan(random.nextFloat() * 10) + 90));
		}
		return list;
	}
}