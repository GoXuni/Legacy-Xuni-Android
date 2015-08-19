package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.chartcore.*;
import com.grapecity.xuni.flexpie.*;

public class UpdateAnimationActivity extends Activity
{
	private Spinner mAnimationSpinner;
	private FlexPie mFlexPie;
	private ObservableList<Object> mFlexdonutFruits;
	private static Button mButton2015;
	private static Button mButton2014;
	private static Button mButton2013;
	private static Button mButton2012;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_animation);

		// initializing widgets
		mFlexPie = (FlexPie) findViewById(R.id.flexPie);
		mAnimationSpinner = (Spinner) findViewById(R.id.animationSpinner);
		mButton2015 = (Button) findViewById(R.id.button2015);
		mButton2014 = (Button) findViewById(R.id.button2014);
		mButton2013 = (Button) findViewById(R.id.button2013);
		mButton2012 = (Button) findViewById(R.id.button2012);

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
		mFlexPie.setAnimated(true);
		mFlexPie.getLoadAnimation().setAnimationMode(AnimationMode.ALL);

		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.animation_spinner_values, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mAnimationSpinner.setAdapter(adapter);
		mAnimationSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// setting animation mode based on user selection
				switch (position)
				{
					case 0:
						mFlexPie.getLoadAnimation().setAnimationMode(AnimationMode.ALL);
						break;
					case 1:
						mFlexPie.getLoadAnimation().setAnimationMode(AnimationMode.POINT);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mButton2015.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				mFlexdonutFruits.set(0, new BindObject("Oranges", 21));
				mFlexdonutFruits.set(1, new BindObject("Apples", 64));
				mFlexdonutFruits.set(2, new BindObject("Pears", 83));
				mFlexdonutFruits.set(3, new BindObject("Bananas", 12));
				mFlexdonutFruits.set(4, new BindObject("Pineapples", 63));

				setButtonDefaults();
				mButton2015.setTextColor(Color.RED);
			}
		});

		mButton2014.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				mFlexdonutFruits.set(0, new BindObject("Oranges", 31));
				mFlexdonutFruits.set(1, new BindObject("Apples", 54));
				mFlexdonutFruits.set(2, new BindObject("Pears", 53));
				mFlexdonutFruits.set(3, new BindObject("Bananas", 32));
				mFlexdonutFruits.set(4, new BindObject("Pineapples", 73));

				setButtonDefaults();
				mButton2014.setTextColor(Color.RED);
			}
		});

		mButton2013.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				mFlexdonutFruits.set(0, new BindObject("Oranges", 6));
				mFlexdonutFruits.set(1, new BindObject("Apples", 60));
				mFlexdonutFruits.set(2, new BindObject("Pears", 100));
				mFlexdonutFruits.set(3, new BindObject("Bananas", 32));
				mFlexdonutFruits.set(4, new BindObject("Pineapples", 73));

				setButtonDefaults();
				mButton2013.setTextColor(Color.RED);
			}
		});

		mButton2012.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				mFlexdonutFruits.set(0, new BindObject("Oranges", 46));
				mFlexdonutFruits.set(1, new BindObject("Apples", 74));
				mFlexdonutFruits.set(2, new BindObject("Pears", 43));
				mFlexdonutFruits.set(3, new BindObject("Bananas", 32));
				mFlexdonutFruits.set(4, new BindObject("Pineapples", 53));

				setButtonDefaults();
				mButton2012.setTextColor(Color.RED);
			}
		});
	}

	public static void setButtonDefaults()
	{
		mButton2012.setTextColor(Color.BLACK);
		mButton2013.setTextColor(Color.BLACK);
		mButton2014.setTextColor(Color.BLACK);
		mButton2015.setTextColor(Color.BLACK);
	}
}
