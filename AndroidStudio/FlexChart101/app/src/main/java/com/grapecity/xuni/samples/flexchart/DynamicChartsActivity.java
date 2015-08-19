package com.grapecity.xuni.samples.flexchart;

import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexchart.*;

public class DynamicChartsActivity extends Activity
{

	private FlexChart mChart;
	static ObservableList<ChartPoint> mList = new ObservableList<ChartPoint>();
	android.os.Handler mCustomHandler;
	private Runnable mUpdateTimerThread;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dynamic_charts);

		// initializing widget and handler
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mCustomHandler = new android.os.Handler();

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

		// setting the source of data/items and default values in FlexPie
		// property set in XML layout
		// mChart.setChartType(ChartType.LINE);
		getList();
		mChart.setItemsSource(mList);
		mChart.setAnimated(false);

		// a timer thread to update chart every 1000 msec
		mUpdateTimerThread = new Runnable()
		{
			private Random random = new Random();
			String time;

			public void run()
			{
				// add item to list
				time = android.text.format.DateFormat.format("mm:ss", new Date()).toString();

				// keep at most 10 elements on screens
				if (mList.size() > 10)
					mList.remove(0);
				int temp = random.nextInt(100);
				mList.add(new ChartPoint(time, temp, temp + 10, temp + 20));
				mCustomHandler.postDelayed(this, 1000);
			}
		};
		mCustomHandler.postDelayed(mUpdateTimerThread, 0);
	}

	@Override
	protected void onStop()
	{
		// to clear handler call back list, and destroy activity and thread
		mCustomHandler.removeCallbacks(mUpdateTimerThread);
		mList.clear();
		this.finish();
		super.onStop();
	}

	// a method to create random list of ChartPoint objects
	private static ObservableList<ChartPoint> getList()
	{

		int n = 6; // number of series elements
		String time = "";
		Random random = new Random();
		int temp;
		for (int i = 0; i < n; i++)
		{
			temp = random.nextInt(100);
			time = android.text.format.DateFormat.format("mm:ss", new Date()).toString();
			mList.add(new ChartPoint(time, temp, temp + 10, temp + 20));
		}
		return mList;
	}
}