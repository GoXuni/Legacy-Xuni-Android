/**
 *
 */
package com.grapecity.xuni.samples.flexchart;

import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grapecity.xuni.flexchart.*;

/**
 * A class to represent a custom layout for custom tooltips
 *
 * @author GrapeCity
 */
class MyToolTip extends BaseChartTooltipView
{

	ImageView mFlag;
	TextView mTitle;
	TextView mContent;
	String mParentPackage;

	protected MyToolTip(FlexChart flexChart, Context context)
	{
		super(flexChart);
		mParentPackage = context.getPackageName();

		// create custom layouts
		LinearLayout customLayout = new LinearLayout(getContext());
		customLayout.setBackgroundColor(Color.parseColor("#FFFFCA"));
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
	}

	// override the render method to set the custom elements of layout
	@Override
	public void render(ChartDataPoint chartDataPoint)
	{
		mFlag.setImageResource(getResources().getIdentifier(chartDataPoint.xValue.toString().toLowerCase(Locale.getDefault()), "drawable", mParentPackage));
		mTitle.setText(chartDataPoint.seriesName);
		mContent.setText(chartDataPoint.xValue.toString() + " " + chartDataPoint.yValue.toString());

		// notify that chart tooltip has changed.
		requestLayout();
	}

}
