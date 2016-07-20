package com.grapecity.xuni.samples.flexchart;

import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.flexchart.ChartSeries;
import com.grapecity.xuni.flexchart.ChartType;
import com.grapecity.xuni.flexchart.FlexChart;

public class UpdateAnimationActivity extends BaseActivity
{
	private Spinner mChartTypeSpinner;
	private Spinner mUpdatePositionSpinner;
	private FlexChart mChart;
	private int mUpdatePosition;
	private Button mButtonAddPosition;
	private Button mButtonRemovePosition;
	protected int mMaxValue = 50;
	protected int mMinSeriesLimit = 1;
	private Random mRandom;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_animation);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mChartTypeSpinner = (Spinner) findViewById(R.id.chartTypeSpinner);
		mUpdatePositionSpinner = (Spinner) findViewById(R.id.animationModeSpinner);
		mButtonAddPosition = (Button) findViewById(R.id.buttonAddPoint);
		mButtonRemovePosition = (Button) findViewById(R.id.buttonRemovePoint);
		mUpdatePosition = 0;
		mRandom = new Random();

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("letter");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Value", "count");

		seriesSales.setColor(Color.BLUE);

		// add series to list
		mChart.getSeries().add(seriesSales);

		// setting the source of data/items and default values in FlexChart
		if (dataSource == null && savedInstanceState != null)
		{
			dataSource = (ObservableList<ChartPoint>) savedInstanceState.getSerializable(DATA_SOURCE);
		}
		else
		{
			dataSource = getList();
		}
		mChart.setItemsSource(dataSource);

		// property set in XML layout
		// mChart.setChartType(ChartType.COLUMN);

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
				// set the chart type
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
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.updatePositionSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mUpdatePositionSpinner.setAdapter(adapter2);
		mUpdatePositionSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set the position to add/remove element
				switch (position)
				{
					case 0:
						mUpdatePosition = 0;
						break;
					case 1:
						mUpdatePosition = 1;
						break;
					case 2:
						mUpdatePosition = 2;
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mButtonAddPosition.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// add a new point at location denoted by updatePosition
				switch (mUpdatePosition)
				{
					case 0:
						dataSource.add(0, new ChartPoint(Character.valueOf((char) (mRandom.nextInt(26) + 65)), mRandom.nextInt(mMaxValue)));
						break;
					case 1:
						dataSource.add((dataSource.size() / 2),
								new ChartPoint(Character.valueOf((char) (mRandom.nextInt(26) + 65)), mRandom.nextInt(mMaxValue)));
						break;
					case 2:
						dataSource.add(new ChartPoint(Character.valueOf((char) (mRandom.nextInt(26) + 65)), mRandom.nextInt(mMaxValue)));
						break;
				}
				if (dataSource.size() > mMinSeriesLimit)
					mButtonRemovePosition.setEnabled(true);
			}
		});

		mButtonRemovePosition.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// remove a point at location denoted by updatePosition
				switch (mUpdatePosition)
				{
					case 0:
						dataSource.remove(0);
						break;
					case 1:
						dataSource.remove((dataSource.size() / 2));
						break;
					case 2:
						dataSource.remove(dataSource.size() - 1);
						break;
				}
				if (dataSource.size() <= mMinSeriesLimit)
					mButtonRemovePosition.setEnabled(false);
			}
		});
	}

	// a method to create a list of random objects
	private ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> mList = new ObservableList<ChartPoint>();

		int n = 10; // number of series elements

		for (int i = 0; i < n; i++)
		{
			mList.add(new ChartPoint((char) (mRandom.nextInt(26) + 65), mRandom.nextInt(mMaxValue)));
		}
		return mList;
	}
}
