package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grapecity.xuni.flexchart.*;

public class DataLabelsActivity extends Activity
{

	private FlexChart mChart;
	private Spinner mDataLabelSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_labels);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mDataLabelSpinner = (Spinner) findViewById(R.id.dataLabelSpinner);

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

		// setting the source of data/items in FlexPie
		mChart.setItemsSource(ChartPoint.getList());

		// create custom adapter for spinner and initialize with string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.dataLabelSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mDataLabelSpinner.setAdapter(adapter1);
		mDataLabelSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set chart label position
				switch (position)
				{
					case 0:
						mChart.getDataLabel().setPosition(ChartLabelPosition.RIGHT);
						break;
					case 1:
						mChart.getDataLabel().setPosition(ChartLabelPosition.TOP);
						break;
					case 2:
						mChart.getDataLabel().setPosition(ChartLabelPosition.LEFT);
						break;
					case 3:
						mChart.getDataLabel().setPosition(ChartLabelPosition.BOTTOM);
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
