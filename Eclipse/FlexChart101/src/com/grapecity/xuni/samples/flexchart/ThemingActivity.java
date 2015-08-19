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

public class ThemingActivity extends Activity
{

	private FlexChart mChart;
	private Spinner mPalette_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theming);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mPalette_spinner = (Spinner) findViewById(R.id.paletteSpinner);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries series_sales = new ChartSeries(mChart, "Sales", "sales");
		ChartSeries series_expenses = new ChartSeries(mChart, "Expenses", "expenses");
		ChartSeries series_downloads = new ChartSeries(mChart, "Downloads", "downloads");

		// add series to list
		mChart.getSeries().add(series_sales);
		mChart.getSeries().add(series_expenses);
		mChart.getSeries().add(series_downloads);

		// setting the source of data/items in FlexPie
		mChart.setItemsSource(ChartPoint.getList());

		// create custom adapter for spinner and initialize with string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.paletteSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mPalette_spinner.setAdapter(adapter);
		mPalette_spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set palette based on spinner value
				switch (position)
				{
					case 0:
						mChart.setPalette(Palettes.STANDARD);
						break;
					case 1:
						mChart.setPalette(Palettes.COCOA);
						break;
					case 2:
						mChart.setPalette(Palettes.CORAL);
						break;
					case 3:
						mChart.setPalette(Palettes.DARK);
						break;
					case 4:
						mChart.setPalette(Palettes.HIGHCONTRAST);
						break;
					case 5:
						mChart.setPalette(Palettes.LIGHT);
						break;
					case 6:
						mChart.setPalette(Palettes.MIDNIGHT);
						break;
					case 7:
						mChart.setPalette(Palettes.MINIMAL);
						break;
					case 8:
						mChart.setPalette(Palettes.MODERN);
						break;
					case 9:
						mChart.setPalette(Palettes.ORGANIC);
						break;
					case 10:
						mChart.setPalette(Palettes.SLATE);
						break;
					case 11:
						mChart.setPalette(Palettes.ZEN);
						break;
					case 12:
						mChart.setPalette(Palettes.CYBORG);
						break;
					case 13:
						mChart.setPalette(Palettes.SUPERHERO);
						break;
					case 14:
						mChart.setPalette(Palettes.FLATLY);
						break;
					case 15:
						mChart.setPalette(Palettes.DARKLY);
						break;
					case 16:
						mChart.setPalette(Palettes.CERULEAN);
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