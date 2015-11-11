package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.grapecity.xuni.chartcore.Palettes;
import com.grapecity.xuni.flexchart.ChartSeries;
import com.grapecity.xuni.flexchart.ChartStackingType;
import com.grapecity.xuni.flexchart.FlexChart;

public class CustomTooltipsActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_tooltips);

		// initializing widgets
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

		mChart.setStackingType(ChartStackingType.STACKED);
		mChart.setPalette(Palettes.COCOA);

		// setting the source of data/items in FlexChart
		mChart.setItemsSource(ChartPoint.getList());

		// set custom tooltip
		mChart.getTooltip().setContent(new MyToolTip(mChart, getApplicationContext()));
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		// Checks the orientation of the screen
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE || newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			mChart.getTooltip().getContent().hide(mChart);
		}
	}
}
