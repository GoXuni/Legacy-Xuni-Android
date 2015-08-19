package com.grapecity.xuni.samples.flexpie;

import com.grapecity.xuni.flexpie.BasePieTooltipView;
import com.grapecity.xuni.flexpie.FlexPie;
import com.grapecity.xuni.flexpie.PieDataPoint;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A class to represent a custom layout for custom tooltips
 *
 * @author vivek.punjabi
 */
class MyToolTip extends BasePieTooltipView
{
	TextView mContent;
	String mParentPackage;

	protected MyToolTip(FlexPie flexPie, Context context)
	{
		super(flexPie);
		mParentPackage = context.getPackageName();

		// create custom layouts
		LinearLayout customLayout = new LinearLayout(getContext());
		customLayout.setBackgroundColor(Color.parseColor("#FFFFCA"));
		customLayout.setOrientation(LinearLayout.VERTICAL);

		LinearLayout childLayout = new LinearLayout(getContext());
		childLayout.setOrientation(LinearLayout.HORIZONTAL);

		mContent = new TextView(getContext());

		// set element properties
		mContent.setTextColor(Color.BLACK);
		mContent.setPadding(10, 0, 10, 0);

		// add layouts
		customLayout.addView(childLayout);
		customLayout.addView(mContent);
		addView(customLayout);
	}

	// override the render method to set the custom elements of layout
	@Override
	public void render(PieDataPoint pieDataPoint)
	{
		mContent.setText(Double.toString(pieDataPoint.value));

		// notify that chart tooltip has changed.
		requestLayout();
	}

}
