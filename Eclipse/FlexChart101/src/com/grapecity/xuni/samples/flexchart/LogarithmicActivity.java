package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexchart.*;

public class LogarithmicActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// FlexChart automatically adjusts to the current theme
		setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);

		// initializing widget
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");
		mChart.setChartType(ChartType.LINE);

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Sales", "sales");

		// add series to list
		mChart.getSeries().add(seriesSales);

		// setting the source of data/items in FlexChart
		mChart.setItemsSource(ChartPoint.getLogList());

		mChart.getAxisY().setLogBase(10f);

	}
}
