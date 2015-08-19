package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.chartcore.*;
import com.grapecity.xuni.flexpie.*;

public class LegendAndTitlesActivity extends Activity
{
	private FlexPie mFlexPie;
	private EditText mHeaderValue;
	private EditText mFooterValue;
	private Spinner mLegendSpinner;
	private ChartLegend mLegend;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_legend_and_titles);

		// initializing widgets
		mFlexPie = (FlexPie) findViewById(R.id.flexPie);
		mHeaderValue = (EditText) findViewById(R.id.headerValue);
		mFooterValue = (EditText) findViewById(R.id.footerValue);
		mLegendSpinner = (Spinner) findViewById(R.id.legendSpinner);

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
		mLegend = mFlexPie.getLegend();
		mLegend.setPosition(ChartPositionType.NONE);

		// setting the source of data/items and default values in FlexPie
		mFlexPie.setItemsSource(flexpieFruits);
		mFlexPie.setHeader(mHeaderValue.getText().toString());
		mFlexPie.setFooter(mFooterValue.getText().toString());

		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.legend_spinner_values, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mLegendSpinner.setAdapter(adapter);
		mLegendSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// setting position of legend based on user selection
				switch (position)
				{
					case 0:
						mLegend.setPosition(ChartPositionType.NONE);
						break;
					case 1:
						mLegend.setPosition(ChartPositionType.LEFT);
						break;
					case 2:
						mLegend.setPosition(ChartPositionType.TOP);
						break;
					case 3:
						mLegend.setPosition(ChartPositionType.RIGHT);
						break;
					case 4:
						mLegend.setPosition(ChartPositionType.BOTTOM);
						break;
					case 5:
						mLegend.setPosition(ChartPositionType.AUTO);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mHeaderValue.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{

			}

			@Override
			public void afterTextChanged(Editable s)
			{
				// setting header value as editText value
				mFlexPie.setHeader(mHeaderValue.getText().toString());
			}
		});

		mFooterValue.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{

			}

			@Override
			public void afterTextChanged(Editable s)
			{
				// setting footer value as editText value
				mFlexPie.setFooter(mFooterValue.getText().toString());
			}
		});
	}
}