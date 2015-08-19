package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexpie.*;

public class GettingStartedActivity extends Activity
{
	private FlexPie mFlexPie1;
	private FlexPie mFlexPie2;
	private MarginF mPlotMargin;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// FlexPie automatically adjusts to the current theme
		setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);

		// initializing widgets
		mFlexPie1 = (FlexPie) findViewById(R.id.flexPie);
		mFlexPie2 = (FlexPie) findViewById(R.id.donutPie);

		// creating a list of fruit objects of type BindObject for first FlexPie
		ObservableList<Object> flexpieFruits = new ObservableList<Object>();
		flexpieFruits.add(new BindObject("Oranges", 11));
		flexpieFruits.add(new BindObject("Apples", 94));
		flexpieFruits.add(new BindObject("Pears", 93));
		flexpieFruits.add(new BindObject("Bananas", 2));
		flexpieFruits.add(new BindObject("Pineapples", 53));

		// set the binding of FlexPie to variables of BindObject
		mFlexPie1.setBindingName("name");
		mFlexPie1.setBinding("value");

		// setting the source of data/items and default values in FlexPie
		mFlexPie1.setItemsSource(flexpieFruits);
		mPlotMargin = new MarginF(10f, 10f, 10f, 10f);
		mFlexPie1.setPlotMargin(mPlotMargin);

		// creating a list of fruit objects of type BindObject for second FlexPie
		ObservableList<Object> flexdonutFruits = new ObservableList<Object>();
		flexdonutFruits.add(new BindObject("Oranges", 11));
		flexdonutFruits.add(new BindObject("Apples", 94));
		flexdonutFruits.add(new BindObject("Pears", 93));
		flexdonutFruits.add(new BindObject("Bananas", 2));
		flexdonutFruits.add(new BindObject("Pineapples", 53));

		// set the binding of FlexPie to variables of BindObject
		mFlexPie2.setBindingName("name");
		mFlexPie2.setBinding("value");

		// setting the source of data/items and defaulty values in FlexPie
		mFlexPie2.setItemsSource(flexdonutFruits);
		mFlexPie2.setInnerRadius(0.6f);
	}
}
