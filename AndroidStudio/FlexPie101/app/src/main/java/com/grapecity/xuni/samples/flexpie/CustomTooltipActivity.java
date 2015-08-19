package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.core.MarginF;
import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.flexpie.FlexPie;

public class CustomTooltipActivity extends Activity
{
	private FlexPie mFlexPie;
	private MarginF mPlotMargin;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_tooltip);

		// initializing widgets
		mFlexPie = (FlexPie) findViewById(R.id.flexPie);

		// creating a list of fruit objects of type BindObject for first FlexPie
		ObservableList<Object> flexpieFruits = new ObservableList<Object>();
		flexpieFruits.add(new BindObject("Oranges", 11));
		flexpieFruits.add(new BindObject("Apples", 94));
		flexpieFruits.add(new BindObject("Pears", 93));
		flexpieFruits.add(new BindObject("Bananas", 2));
		flexpieFruits.add(new BindObject("Pineapples", 53));

		// set the binding of FlexPie to variables of BindObject
		mFlexPie.setBindingName("name");
		mFlexPie.setBinding("value");

		// setting the source of data/items and default values in FlexPie
		mFlexPie.setItemsSource(flexpieFruits);
		mPlotMargin = new MarginF(10f, 10f, 10f, 10f);
		mFlexPie.setPlotMargin(mPlotMargin);

		mFlexPie.getTooltip().setContent(new MyToolTip(mFlexPie,getApplicationContext()));
	}

}
