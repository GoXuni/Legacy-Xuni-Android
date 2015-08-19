package com.grapecity.xuni.samples.flexchart;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.chartcore.*;
import com.grapecity.xuni.flexchart.*;

public class MultipleAxesActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_axes);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("month");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesPrecipitation = new ChartSeries(mChart, "Precipitation", "precipitation");
		ChartSeries seriesTemperature = new ChartSeries(mChart, "Temperature", "temperature");

		// add series to list
		mChart.getSeries().add(seriesPrecipitation);
		mChart.getSeries().add(seriesTemperature);

		// setting the source of data/items and default values in FlexPie
		mChart.setItemsSource(getList());
		seriesTemperature.setChartType(ChartType.SPLINESYMBOLS);

		// format y-axis
		// properties set in XMl layout
		mChart.getAxisY().setTitle("Precipitation (in)");
		// mChart.getAxisY().setTitleFontSize(15);
		mChart.getAxisY().setMajorUnit(2d);
		mChart.getAxisY().setTitleFontColor(Color.parseColor("#88bde6"));

		// create a new axis and customize it
		ChartAxis axisYRight = new ChartAxis(mChart, ChartPositionType.RIGHT);
		axisYRight.setTitle("Avg. Temperature (F)");
		axisYRight.setTitleFontSize(15);
		axisYRight.setName("RIGHT");
		axisYRight.setAxisLineVisible(false);
		axisYRight.setMajorGridVisible(false);
		axisYRight.setMinorGridVisible(false);
		axisYRight.setMajorUnit(10d);
		axisYRight.setMajorTickWidth(0);
		axisYRight.setMinorTickWidth(0);
		axisYRight.setMajorGridWidth(0);
		axisYRight.setTitleFontColor(Color.parseColor("#fbb258"));

		mChart.getAxisX().setMajorTickWidth(2);
		mChart.getAxisX().setLabelAngle(90);

		//add axis to the chart axes observable list
		mChart.getAxes().add(axisYRight);

		// tie the series to the axis
		seriesTemperature.setAxisY("RIGHT");
		mChart.getLegend().setPosition(ChartPositionType.NONE);
	}

	// a method to create a list of random objects of type ChartPoint
	private static ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		int n = 12; // number of series elements
		String[] month =
		{ "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		Random random = new Random();
		for (int i = 0; i < n; i++)
		{
			list.add(new ChartPoint(month[i], random.nextFloat() * 20, (int) Math.tan(random.nextInt(80)) + 70));
		}
		return list;
	}
}