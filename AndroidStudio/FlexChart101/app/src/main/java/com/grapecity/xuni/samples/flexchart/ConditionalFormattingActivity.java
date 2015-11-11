package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexchart.*;

public class ConditionalFormattingActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conditional_formatting);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		final ChartSeries seriesSine = new ChartSeries(mChart, "Sine Series", "sine");

		// add series to list
		mChart.getSeries().add(seriesSine);

		// setting the source of data/items in FlexChart
		mChart.setItemsSource(getList());

		// setting the default values in FlexChart
		// property set in XML layout
		// mChart.setChartType(ChartType.LINESYMBOLS);
		mChart.getAxisY().setMajorUnit(.20);
		// mChart.getAxisY().setFormat("#.##");

		// handler to override the existing call() which is called before
		// rendering each chart element and customize the required elements
		mChart.getPlotElementLoading().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				ChartPlotElementEventArgs args = (ChartPlotElementEventArgs) arg1;

				if (args.dataPoint != null)
				{
					double y = (Double) args.dataPoint.yValue;

					// change color values based on y-axis values
					int r = y >= 0 ? 255 : (int) (255 * (1 + y));
					int b = y < 0 ? 255 : (int) (255 * (1 - y));
					int g = (int) ((1 - Math.abs(y)) * 255);
					args.renderEngine.setFill(Color.argb(255, r, g, b));
				}

				// default function to render char elements
				args.defaultRender.execute();
			}
		}, this);

	}

	// a method to create a series of sine values for two full waves
	private static ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		int angle = 720;
		double seriesValue;
		for (int i = 0; i < angle; i += 5)
		{
			seriesValue = Math.sin(Math.toRadians(i));

			list.add(new ChartPoint(seriesValue));
		}
		return list;
	}
}
