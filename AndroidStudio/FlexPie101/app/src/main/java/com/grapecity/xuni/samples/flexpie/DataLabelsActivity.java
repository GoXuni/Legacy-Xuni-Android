package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexpie.*;

public class DataLabelsActivity extends Activity
{

	private FlexPie mFlexPie;
	private Spinner mDataLabelSpinner;
	private ObservableList<Object> mFlexdonutFruits;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_labels);

		// initializing widgets
		mFlexPie = (FlexPie) findViewById(R.id.flexPie);
		mDataLabelSpinner = (Spinner) findViewById(R.id.dataLabelSpinner);

		// creating a list of fruit objects of type BindObject
		mFlexdonutFruits = new ObservableList<Object>();
		mFlexdonutFruits.add(new BindObject("Oranges", 11));
		mFlexdonutFruits.add(new BindObject("Apples", 94));
		mFlexdonutFruits.add(new BindObject("Pears", 93));
		mFlexdonutFruits.add(new BindObject("Bananas", 2));
		mFlexdonutFruits.add(new BindObject("Pineapples", 53));

		// set the binding of FlexPie to variables of BindObject
		mFlexPie.setBindingName("name");
		mFlexPie.setBinding("value");

		// setting the source of data/items in FlexPie
		mFlexPie.setItemsSource(mFlexdonutFruits);
		mFlexPie.setDataLabel(new PieDataLabel(mFlexPie));

		MarginF mPlotMargin = new MarginF(30f, 30f, 30f, 30f);
		mFlexPie.setPlotMargin(mPlotMargin);

		// create custom adapter for spinner and initialize with string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.dataLabelSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mDataLabelSpinner.setAdapter(adapter1);
		mDataLabelSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set chart label position
				switch (position)
				{
					case 0:
						mFlexPie.getDataLabel().setPosition(PieLabelPosition.NONE);
						break;
					case 1:
						mFlexPie.getDataLabel().setPosition(PieLabelPosition.CENTER);
						break;
					case 2:
						mFlexPie.getDataLabel().setPosition(PieLabelPosition.INSIDE);
						break;
					case 3:
						mFlexPie.getDataLabel().setPosition(PieLabelPosition.OUTSIDE);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
	}
}
