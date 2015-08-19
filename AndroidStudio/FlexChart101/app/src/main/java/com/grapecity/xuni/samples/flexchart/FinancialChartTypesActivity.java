package com.grapecity.xuni.samples.flexchart;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.flexchart.ChartSeries;
import com.grapecity.xuni.flexchart.ChartType;
import com.grapecity.xuni.flexchart.FlexChart;

public class FinancialChartTypesActivity extends Activity
{

	private Spinner mFinancialTypeSpinner;
	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financial_chart);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mFinancialTypeSpinner = (Spinner) findViewById(R.id.financialTypeSpinner);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("date");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries series = new ChartSeries(mChart, "Series1", "high,low,open,close");

		// add series to list
		mChart.getSeries().add(series);

		// setting the source of data/items and default values in FlexPie
		// properties set in XML layout
		// mChart.getAxisX().setFormat("MM/dd/yyyy");
		// mChart.setChartType(ChartType.CANDLE);
		mChart.setItemsSource(getList());
		mChart.getAxisY().setMajorGridFill(Color.argb(20, 50, 50, 50));

		// create custom adapter for spinner and initialize with string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.financialTypeSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mFinancialTypeSpinner.setAdapter(adapter1);
		mFinancialTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set chart type based on user selection
				switch (position)
				{
					case 0:
						mChart.setChartType(ChartType.CANDLE);
						break;
					case 1:
						mChart.setChartType(ChartType.HLOC);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
	}

	// a method to create a list of random objects
	private ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		int n = 8; // number of series elements
		int high, low, open, close;
		Calendar date = Calendar.getInstance();
		Random random = new Random();

		for (int i = 0; i < n; i++)
		{

			date.add(Calendar.DATE, 1);

			if (i > 0)
				open = list.get(i - 1).getClose();
			else
				open = 1000;

			high = open + random.nextInt(20);
			low = open - random.nextInt(20);

			do
			{
				close = random.nextInt(high);
			}
			while (close < low);

			list.add(new ChartPoint(high, low, open, close, date.getTime()));
		}

		return list;
	}
}