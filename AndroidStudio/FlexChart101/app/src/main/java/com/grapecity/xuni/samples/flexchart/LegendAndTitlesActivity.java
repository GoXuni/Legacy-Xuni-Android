package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexchart.*;

public class LegendAndTitlesActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_legend_and_titles);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mChart.setChartType(ChartType.SCATTER);

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

		// setting the default values for header and footer in FlexChart
		// properties if it is not set in XML layout
		// mChart.setHeader("Sample Chart");
		// mChart.setHeaderFontColor(Color.RED);
		// mChart.setHeaderFontGravity(1);
		// mChart.setHeaderFontSize(20);
		// mChart.setFooter("2014 GrapeCity, Inc.");
		// mChart.setFooterFontColor(Color.RED);
		// mChart.setFooterFontGravity(4);
		// mChart.setFooterFontSize(10);
		mChart.getAxisX().setTitle("Country");
		mChart.getAxisX().setMajorGridVisible(true);
		// mChart.getAxisX().setTitleFontTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
		// mChart.getAxisY().setTitleFontTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
		mChart.getAxisY().setTitle("Amount");
		// mChart.getAxisY().setFormat("$#,###");
	}
}