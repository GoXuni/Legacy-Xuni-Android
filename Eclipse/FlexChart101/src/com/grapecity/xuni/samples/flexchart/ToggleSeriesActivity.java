package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import com.grapecity.xuni.flexchart.*;

public class ToggleSeriesActivity extends Activity
{

	private FlexChart mChart;
	private Switch mSalesSwitch;
	private Switch mExpensesSwitch;
	private Switch mDownloadsSwitch;
	private ChartSeries mSeriesSales;
	private ChartSeries mSeriesExpenses;
	private ChartSeries mSeriesDownloads;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toggle_series);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mSalesSwitch = (Switch) findViewById(R.id.salesSwitch);
		mExpensesSwitch = (Switch) findViewById(R.id.expensesSwitch);
		mDownloadsSwitch = (Switch) findViewById(R.id.downloadsSwitch);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");
		
		//enable ability to toggle a series visibility by touching the series in the actual legend.
		mChart.setToggleLegend(true);

		// initialize series elements and set the binding to variables of
		// ChartPoint
		mSeriesSales = new ChartSeries(mChart, "Sales", "sales");
		mSeriesExpenses = new ChartSeries(mChart, "Expenses", "expenses");
		mSeriesDownloads = new ChartSeries(mChart, "Downloads", "downloads");

		// add series to list
		mChart.getSeries().add(mSeriesSales);
		mChart.getSeries().add(mSeriesExpenses);
		mChart.getSeries().add(mSeriesDownloads);

		// setting the source of data/items and default values in FlexPie
		mChart.setItemsSource(ChartPoint.getList());
		mSalesSwitch.setChecked(true);
		mExpensesSwitch.setChecked(true);
		mDownloadsSwitch.setChecked(true);

		mSalesSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// set element visibility for series sales
				if (isChecked)
					mSeriesSales.setVisibility(ChartSeriesVisibilityType.VISIBLE);
				else
					mSeriesSales.setVisibility(ChartSeriesVisibilityType.HIDDEN);
			}
		});

		mExpensesSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// set element visibility for series expenses
				if (isChecked)
					mSeriesExpenses.setVisibility(ChartSeriesVisibilityType.VISIBLE);
				else
					mSeriesExpenses.setVisibility(ChartSeriesVisibilityType.HIDDEN);
			}
		});

		mDownloadsSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// set element visibility for series downloads
				if (isChecked)
					mSeriesDownloads.setVisibility(ChartSeriesVisibilityType.VISIBLE);
				else
					mSeriesDownloads.setVisibility(ChartSeriesVisibilityType.HIDDEN);
			}
		});
	}
}