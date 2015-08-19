package com.grapecity.xuni.samples.flexchart;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.flexchart.*;

public class ZoomingAndScrollingActivity extends Activity
{

	private FlexChart mChart;
	private Spinner mZoomModeSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zooming_and_scrolling);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mZoomModeSpinner = (Spinner) findViewById(R.id.zoomModeSpinner);

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Normal Distribution", "value");

		// add series to list
		mChart.getSeries().add(seriesSales);

		// setting the source of data/items and default values in FlexPie
		mChart.setItemsSource(DefaultChartPoint.getList());
		// property set in XML layout
		// mChart.setChartType(ChartType.AREA);
		mChart.getAxisY().setMajorUnit(1.0);

		// create custom adapter for spinner and initialize with string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zoomModeSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mZoomModeSpinner.setAdapter(adapter);
		mZoomModeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set the zoom mode
				switch (position)
				{
					case 0:
						mChart.setZoomMode(ZoomMode.XY);
						break;
					case 1:
						mChart.setZoomMode(ZoomMode.X);
						break;
					case 2:
						mChart.setZoomMode(ZoomMode.Y);
						break;
					case 3:
						mChart.setZoomMode(ZoomMode.DISABLED);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
	}
}

class DefaultChartPoint
{
	double value;

	public DefaultChartPoint(double value)
	{
		this.value = value;
	}

	// a method to create a list of random objects of type ChartPoint
	public static ObservableList<DefaultChartPoint> getList()
	{
		ObservableList<DefaultChartPoint> list = new ObservableList<DefaultChartPoint>();

		int n = 240; // number of series elements
		Random random = new Random();

		for (int i = 0; i < n; i++)
		{
			list.add(new DefaultChartPoint((random.nextDouble() * 6.0) - 3));
		}
		return list;
	}
}