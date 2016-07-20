package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Rect;
import android.os.Bundle;

import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.flexchart.ChartSeries;
import com.grapecity.xuni.flexchart.FlexChart;
import com.grapecity.xuni.flexchart.LabelLoadingEventArgs;
import com.grapecity.xuni.flexchart.TickMarkType;

public class CustomizingAxesLabelActivity extends Activity
{

	private FlexChart mChart;

	int[] imageResources =
	{ R.drawable.us, R.drawable.germany, R.drawable.uk, R.drawable.japan, R.drawable.italy, R.drawable.greece };

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

		// setting the source of data/items in FlexChart
		mChart.setItemsSource(ChartPoint.getList());

		// customize the axis title and format
		// properties set in XML layout
		mChart.getAxisX().setTitle("Country");
		mChart.getAxisY().setLineWidth(2);
		mChart.getAxisY().setMajorTickWidth(2);
		mChart.getAxisY().setMajorTickOverlap(TickMarkType.CROSS);
		mChart.getAxisY().setMajorGridColor(Color.parseColor("#C4C4C4"));
		mChart.getAxisY().setMajorGridWidth(1);
		mChart.getAxisY().setMajorGridFill(Color.parseColor("#66C4C4C4"));
		mChart.getAxisY().setMinorGridVisible(true);
		mChart.getAxisY().setMinorGridWidth(0.5f);
		float[] pathValues =
		{ 4, 4 };
		mChart.getAxisY().setMinorGridDashes(new DashPathEffect(pathValues, 4));
		mChart.getAxisY().setMinorTickWidth(1);
		mChart.getAxisY().setMajorUnit(2000d);

		mChart.getAxisX().setMajorTickWidth(0);
		mChart.getAxisX().setMinorTickWidth(1);
		
		mChart.getAxisY().getLabelLoading().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				LabelLoadingEventArgs args = (LabelLoadingEventArgs) arg1;
				args.renderEngine.setFill(Color.argb(255, 0, (int) args.value % 255, 0));
				args.renderEngine.drawString("$" + args.value / 1000 + "K", (int) args.region.left, (int) args.region.top + args.region.height() /  2);
			}
		}, this);

		mChart.getAxisX().getLabelLoading().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				LabelLoadingEventArgs args = (LabelLoadingEventArgs) arg1;

				int imgId = imageResources[(int) args.value];
				Bitmap bmp = BitmapFactory.decodeResource(getApplicationContext().getResources(), imgId);

				Rect region = args.region;
				int cx = region.left + region.width() / 2;
				int cy = region.top + region.height() / 2;
				Rect rect = new Rect(cx - 16, cy - 11, cx + 16, cy + 11);

				args.renderEngine.canvas.drawBitmap(bmp, null, rect, null);
			}
		}, this);

	}
}