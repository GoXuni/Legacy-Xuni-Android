package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grapecity.xuni.chartcore.*;
import com.grapecity.xuni.flexchart.*;

public class AnimationOptionsActivity extends Activity
{

	private FlexChart mChart;
	private Spinner mChartTypeSpinner;
	private Spinner mAnimationModeSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_options);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mChartTypeSpinner = (Spinner) findViewById(R.id.chartTypeSpinner);
		mAnimationModeSpinner = (Spinner) findViewById(R.id.animationModeSpinner);

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
		mChart.setPalette(Palettes.MODERN);

		// create custom adapter for first spinner and initialize with string
		// array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.chartTypeSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mChartTypeSpinner.setAdapter(adapter1);
		mChartTypeSpinner.setSelection(8);
		mChartTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set chartType based on user selection
				switch (position)
				{
					case 0:
						mChart.setChartType(ChartType.AREA);
						break;
					case 1:
						mChart.setChartType(ChartType.SPLINEAREA);
						break;
					case 2:
						mChart.setChartType(ChartType.SPLINESYMBOLS);
						break;
					case 3:
						mChart.setChartType(ChartType.SPLINE);
						break;
					case 4:
						mChart.setChartType(ChartType.LINESYMBOLS);
						break;
					case 5:
						mChart.setChartType(ChartType.LINE);
						break;
					case 6:
						mChart.setChartType(ChartType.SCATTER);
						break;
					case 7:
						mChart.setChartType(ChartType.BAR);
						break;
					case 8:
						mChart.setChartType(ChartType.COLUMN);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		// create custom adapter for second spinner and initialize with string
		// array
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.animationModeSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mAnimationModeSpinner.setAdapter(adapter2);
		mAnimationModeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set animation mode
				switch (position)
				{
					case 0:
						mChart.getLoadAnimation().setAnimationMode(AnimationMode.POINT);
						break;
					case 1:
						mChart.getLoadAnimation().setAnimationMode(AnimationMode.SERIES);
						break;
					case 2:
						mChart.getLoadAnimation().setAnimationMode(AnimationMode.ALL);
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
