package com.grapecity.xuni.samples.flexchart;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grapecity.xuni.flexchart.ChartDataPoint;
import com.grapecity.xuni.flexchart.ChartLineMarker;
import com.grapecity.xuni.flexchart.ChartMarkerView;
import com.grapecity.xuni.flexchart.FlexChart;
import com.grapecity.xuni.flexchart.IChartMarkerRendering;

public class MyMarker extends ChartMarkerView
{

	ImageView mFlag;
	TextView mTitle;
	TextView mContent;
	String mParentPackage;
	private MyChartMarkerRenderingImpl impl = new MyChartMarkerRenderingImpl();

	public MyMarker(FlexChart flexChart, Context context, ChartLineMarker marker)
	{
		super(flexChart, context, marker);
		mParentPackage = context.getPackageName();
		setRendering(impl);
		// create custom layouts
		LinearLayout customLayout = new LinearLayout(getContext());
		customLayout.setBackgroundColor(Color.parseColor("#55ffffff"));
		customLayout.setOrientation(LinearLayout.VERTICAL);

		LinearLayout childLayout = new LinearLayout(getContext());
		childLayout.setOrientation(LinearLayout.HORIZONTAL);

		// initialize layout elements
		mFlag = new ImageView(getContext());
		mTitle = new TextView(getContext());
		mContent = new TextView(getContext());

		// set element properties
		mTitle.setTextColor(Color.BLACK);
		mTitle.setTypeface(mTitle.getTypeface(), Typeface.BOLD);
		mTitle.setPadding(10, 0, 0, 0);
		mContent.setTextColor(Color.BLACK);

		// add layouts
		childLayout.addView(mFlag);
		childLayout.addView(mTitle);
		customLayout.addView(childLayout);
		customLayout.addView(mContent);
		addView(customLayout);
		flexChart.addView(this);
	}

	private class MyChartMarkerRenderingImpl implements IChartMarkerRendering
	{
		@Override
		public void renderMarker()
		{
			String strTitle = "";
			String strContent = "";
			int defType = 0;

			List<ChartDataPoint> points = marker.getDataPoints();
			if (points == null || points.size() == 0)
				return;

			strTitle = points.get(0).xValue.toString();
			defType = getResources().getIdentifier(points.get(0).xValue.toString().toLowerCase(Locale.getDefault()), "drawable", mParentPackage);
			for (int i = 0; i < points.size(); i++)
			{
				ChartDataPoint point = points.get(i);
				strContent += point.seriesName + " : " + point.yValue + "\r\n";
			}
			strContent += "---- End ----";

			if (strTitle.length() > 0 || strContent.length() > 0)
			{
				mTitle.setText(strTitle);
				mContent.setText(strContent);
				if (defType != 0)
					mFlag.setImageResource(defType);
			}
			chart.plotAreaView.invalidate();
		}
	}

}
