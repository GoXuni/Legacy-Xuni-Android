package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.PathEffect;
import android.os.Bundle;

import com.grapecity.xuni.flexchart.*;

public class CustomizingAxesActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customizing_axes);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Sales", "sales");
		ChartSeries seriesExpenses = new ChartSeries(mChart, "Expenses", "expenses");

		// add series to list
		mChart.getSeries().add(seriesSales);
		mChart.getSeries().add(seriesExpenses);

		// setting the source of data/items in FlexPie
		mChart.setItemsSource(ChartPoint.getList());

		// customize the axis title and format
		// properties set in XML layout
		mChart.getAxisX().setTitle("Country");
		// mChart.getAxisY().setFormat("$#,###");
		mChart.getAxisY().setTitle("Amount");
		mChart.getAxisY().setLineWidth(2);
		mChart.getAxisY().setMajorTickWidth(2);
		mChart.getAxisY().setMajorTickOverlap(TickMarkType.NONE);
		mChart.getAxisY().setMajorGridColor(Color.parseColor("#C4C4C4"));
		mChart.getAxisY().setMajorGridWidth(1);
		mChart.getAxisY().setMajorGridFill(Color.parseColor("#66C4C4C4"));
		mChart.getAxisY().setMinorGridVisible(true);
		mChart.getAxisY().setMinorGridWidth(0.5f);
		float[] pathValues = {4,4};
		mChart.getAxisY().setMinorGridDashes(new DashPathEffect(pathValues, 4));
		mChart.getAxisY().setMinorTickWidth(1);
		mChart.getAxisY().setMajorUnit(2000d);

	}
}