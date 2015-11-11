package com.grapecity.xuni.samples.flexchart;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexchart.*;

public class CustomPlotElementsActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_plot_elements);

		// initializing widgets
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");
		// set no animated.
		mChart.setAnimated(false);
		
		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesdevices = new ChartSeries(mChart, "Devices sold", "devicesSold");

		// add series to list
		mChart.getSeries().add(seriesdevices);

		// setting the source of data/items in FlexChart
		mChart.setItemsSource(CustomPoint.getList(getApplicationContext()));
		
		// Set axis Y title.
		mChart.getAxisY().setTitle("Devices Sold (billions)");
		// Set axis Y line invisible.
		mChart.getAxisY().setAxisLineVisible(false);
		// Set axis Y minor tick marker invisible.
		mChart.getAxisY().setMinorTickWidth(0);
				
		// handler to override the existing call() which is called before
		// rendering each chart element and customize the required elements
		mChart.getPlotElementLoading().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				ChartPlotElementEventArgs args = (ChartPlotElementEventArgs) arg1;

				if (args.dataPoint != (null) && args.region != null)
				{
					CustomPoint customPoint = (CustomPoint) mChart.getCollectionView().getItems().get(args.dataPoint.pointIndex);

					// determine center location to render bitmap
					int top = args.region.top + args.region.height() / 2 - args.region.width() / 2;
					int bottom = args.region.top + args.region.height() / 2 + args.region.width() / 2;
					Rect imagePosition = new Rect(args.region.left, top, args.region.right, bottom);

					if ((imagePosition.bottom - imagePosition.top) > (args.region.bottom - args.region.top))
						imagePosition = args.region;

					if (customPoint != null)
					{
						// render bitmap
						args.renderEngine.canvas.drawBitmap(customPoint.logo, null, imagePosition, null);

						// render border
						args.renderEngine.setStrokeWidth(2);
						args.renderEngine.setFill(Color.GRAY);
						Rect bounds = args.region;
						args.renderEngine.drawRectEmpty(bounds.left, bounds.top, bounds.right - bounds.left, bounds.bottom - bounds.top);
					}
				}
			}
		}, this);
	}
}

class CustomPoint
{
	String name;
	int devicesSold;
	Bitmap logo;

	CustomPoint(String name, int devicesSold, Bitmap logo)
	{
		this.name = name;
		this.devicesSold = devicesSold;
		this.logo = logo;
	}

	// a method to create random values for seriesDevices and assign respective
	// bitmaps
	public static ObservableList<CustomPoint> getList(Context context)
	{
		ObservableList<CustomPoint> list = new ObservableList<CustomPoint>();
		int n = 3;
		int min = 15; // minimum value for the chart
		String[] names =
		{ "Google", "Apple", "Microsoft" };
		int[] imageResources =
		{ R.drawable.logo_google, R.drawable.logo_apple, R.drawable.logo_microsoft };
		Bitmap logo;
		Random random = new Random();

		for (int i = 0; i < n; i++)
		{
			logo = BitmapFactory.decodeResource(context.getResources(), imageResources[i]);
			list.add(new CustomPoint(names[i], random.nextInt(85) + min, logo));
		}
		return list;
	}
}
