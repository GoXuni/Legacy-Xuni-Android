package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.chartcore.*;
import com.grapecity.xuni.flexpie.*;

public class ThemingActivity extends Activity
{

	private Spinner mThemeSpinner;
	private FlexPie mFlexPie;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theming);

		// initializing widgets
		mFlexPie = (FlexPie) findViewById(R.id.flexPie);
		mThemeSpinner = (Spinner) findViewById(R.id.themeSpinner);

		// creating a list of fruit objects of type BindObject
		ObservableList<Object> flexpieFruits = new ObservableList<Object>();
		flexpieFruits.add(new BindObject("Oranges", 11));
		flexpieFruits.add(new BindObject("Apples", 94));
		flexpieFruits.add(new BindObject("Pears", 93));
		flexpieFruits.add(new BindObject("Bananas", 2));
		flexpieFruits.add(new BindObject("Pineapples", 53));

		// set the binding of FlexPie to variables of BindObject
		mFlexPie.setBindingName("name");
		mFlexPie.setBinding("value");

		// setting the source of data/items in FlexPie
		mFlexPie.setItemsSource(flexpieFruits);

		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.theme_spinner_values, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mThemeSpinner.setAdapter(adapter);
		mThemeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// setting palette based on user selection
				switch (position)
				{
					case 0:
						mFlexPie.setPalette(Palettes.STANDARD);
						break;
					case 1:
						mFlexPie.setPalette(Palettes.COCOA);
						break;
					case 2:
						mFlexPie.setPalette(Palettes.CORAL);
						break;
					case 3:
						mFlexPie.setPalette(Palettes.DARK);
						break;
					case 4:
						mFlexPie.setPalette(Palettes.HIGHCONTRAST);
						break;
					case 5:
						mFlexPie.setPalette(Palettes.LIGHT);
						break;
					case 6:
						mFlexPie.setPalette(Palettes.MIDNIGHT);
						break;
					case 7:
						mFlexPie.setPalette(Palettes.MINIMAL);
						break;
					case 8:
						mFlexPie.setPalette(Palettes.MODERN);
						break;
					case 9:
						mFlexPie.setPalette(Palettes.ORGANIC);
						break;
					case 10:
						mFlexPie.setPalette(Palettes.SLATE);
						break;
					case 11:
						mFlexPie.setPalette(Palettes.ZEN);
						break;
					case 12:
						mFlexPie.setPalette(Palettes.CYBORG);
						break;
					case 13:
						mFlexPie.setPalette(Palettes.SUPERHERO);
						break;
					case 14:
						mFlexPie.setPalette(Palettes.FLATLY);
						break;
					case 15:
						mFlexPie.setPalette(Palettes.DARKLY);
						break;
					case 16:
						mFlexPie.setPalette(Palettes.CERULEAN);
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
