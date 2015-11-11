package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.grapecity.xuni.flexchart.*;

public class StylingSeriesActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_styling_series);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Sales", "sales");
		ChartSeries seriesExpenses = new ChartSeries(mChart, "Expenses", "expenses");
		ChartSeries seriesDownloads = new ChartSeries(mChart, "Downloads", "downloads");

		// style series sales
		seriesSales.setColor(Color.parseColor("#009400"));
		seriesSales.setBorderColor(Color.GREEN);
		seriesSales.setBorderWidth(3);

		// style series expenses
		seriesExpenses.setColor(Color.parseColor("#C40000"));
		seriesExpenses.setBorderColor(Color.RED);
		seriesExpenses.setBorderWidth(3);

		// style series downloads
		seriesDownloads.setChartType(ChartType.LINESYMBOLS);
		seriesDownloads.setColor(Color.BLUE);
		seriesDownloads.setSymbolColor(Color.YELLOW);
		seriesDownloads.setSymbolBorderWidth(3);
		seriesDownloads.setSymbolBorderColor(Color.CYAN);
		seriesDownloads.setBorderWidth(10);

		// add series to list
		mChart.getSeries().add(seriesSales);
		mChart.getSeries().add(seriesExpenses);
		mChart.getSeries().add(seriesDownloads);

		// setting the source of data/items in FlexChart
		mChart.setItemsSource(ChartPoint.getList());
	}
}