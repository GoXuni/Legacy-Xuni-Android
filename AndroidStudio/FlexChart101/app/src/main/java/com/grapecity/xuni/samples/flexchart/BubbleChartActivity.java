package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexchart.*;

public class BubbleChartActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bubble_chart);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Sales", "sales,downloads");
		ChartSeries seriesExpenses = new ChartSeries(mChart, "Expenses", "expenses,downloads");

		// add series to list
		mChart.getSeries().add(seriesSales);
		mChart.getSeries().add(seriesExpenses);

		// setting the source of data/items and default values in FlexPie
		mChart.setItemsSource(ChartPoint.getList());
		// property set in XML layout
		// mChart.setChartType(ChartType.BUBBLE);
	}
}
