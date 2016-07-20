package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexchart.*;

public class GettingStartedActivity extends Activity
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

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Sales", "sales");
		ChartSeries seriesExpenses = new ChartSeries(mChart, "Expenses", "expenses");
		ChartSeries seriesDownloads = new ChartSeries(mChart, "Downloads", "downloads");

		// add series to list
		mChart.getSeries().add(seriesSales);
		mChart.getSeries().add(seriesExpenses);
		mChart.getSeries().add(seriesDownloads);

		// setting the source of data/items in FlexChart
		mChart.setItemsSource(ChartPoint.getList());
		
		mChart.getAxisX().setMajorTickWidth(0);
		mChart.getAxisX().setMinorTickWidth(1);
	}
}
