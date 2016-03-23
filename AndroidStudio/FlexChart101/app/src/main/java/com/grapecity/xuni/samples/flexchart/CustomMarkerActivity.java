package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.flexchart.ChartMarkerAlignment;
import com.grapecity.xuni.flexchart.ChartMarkerInteraction;
import com.grapecity.xuni.flexchart.ChartMarkerLines;
import com.grapecity.xuni.flexchart.ChartSeries;
import com.grapecity.xuni.flexchart.ChartType;
import com.grapecity.xuni.flexchart.FlexChart;

public class CustomMarkerActivity extends Activity
{
	private FlexChart mChart;
	private Spinner mInteractiveSpinner;
	private Spinner mVPSpinner;

	Double[] positionValues = new Double[]
	{ Double.NaN, 0d, 0.25d, 0.5d, 0.75d, 1d };

	private ObservableList<ChartPoint> dataSource;
	private static final String DATA_SOURCE = "DATA_SOURCE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_marker);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mChart.setChartType(ChartType.LINE);
		mInteractiveSpinner = (Spinner) findViewById(R.id.interactiveSpinner);

		mVPSpinner = (Spinner) findViewById(R.id.verticalPositionSpinner);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries series_sales = new ChartSeries(mChart, "Sales", "sales");
		ChartSeries series_expenses = new ChartSeries(mChart, "Expenses", "expenses");

		// add series to list
		mChart.getSeries().add(series_sales);
		mChart.getSeries().add(series_expenses);
		
		if (dataSource == null && savedInstanceState != null)
		{
			dataSource = (ObservableList<ChartPoint>) savedInstanceState.getSerializable(DATA_SOURCE);
		}
		else
		{
			dataSource = ChartPoint.getList(60);
		}
		mChart.setItemsSource(dataSource);

		ArrayAdapter<CharSequence> adapterInteractive = ArrayAdapter.createFromResource(this, R.array.lineMarkerInteraction, android.R.layout.simple_spinner_item);
		
		adapterInteractive.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mInteractiveSpinner.setAdapter(adapterInteractive);
		mInteractiveSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				switch (position)
				{
					case 0:
						mChart.getMarker().setInteraction(ChartMarkerInteraction.Move);
						break;
					case 1:
						mChart.getMarker().setInteraction(ChartMarkerInteraction.Drag);
						break;
					case 2:
						mChart.getMarker().setInteraction(ChartMarkerInteraction.None);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});

		ArrayAdapter<Double> adapterVPosition = new ArrayAdapter<Double>(this, android.R.layout.simple_spinner_item, positionValues);
		adapterVPosition.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mVPSpinner.setAdapter(adapterVPosition);

		mVPSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				mChart.getMarker().setVerticalPosition(positionValues[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});

		mVPSpinner.setSelection(adapterVPosition.getPosition(0d));
//		mChart.getMarker().setVerticalPosition(0); // 0 is top position.
		
		mChart.getTooltip().setVisible(false);
		mChart.getMarker().setVisible(true);
		
		mChart.getMarker().setLines(ChartMarkerLines.VERTICAL);
		mChart.getMarker().setAlignment(ChartMarkerAlignment.Auto);
		mChart.getMarker().setContent(new MyMarker(mChart, getApplicationContext(), mChart.getMarker()));
		mChart.getMarker().setSeriesIndex(0);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		outState.putSerializable(DATA_SOURCE, dataSource);
		super.onSaveInstanceState(outState);
	}
}
