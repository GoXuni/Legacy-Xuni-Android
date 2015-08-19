package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.chartcore.*;
import com.grapecity.xuni.flexpie.*;

public class SelectionActivity extends Activity
{

	private FlexPie mFlexPie;
	private TextView mOffset;
	private Switch mAnimatedSwitch;
	private Button mButtonMinus;
	private Button mButtonPlus;
	private float mOffsetValue = 0.2f;
	private Spinner mSelectionSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection);

		// initializing widgets
		mFlexPie = (FlexPie) findViewById(R.id.flexPie);
		mOffset = (TextView) findViewById(R.id.offset);
		mAnimatedSwitch = (Switch) findViewById(R.id.animatedSwitch);
		mButtonMinus = (Button) findViewById(R.id.buttonMinus);
		mButtonPlus = (Button) findViewById(R.id.buttonPlus);
		mSelectionSpinner = (Spinner) findViewById(R.id.selectionSpinner);

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

		// setting the source of data/items and default values in FlexPie
		mFlexPie.setItemsSource(flexpieFruits);
		mOffset.setText(Float.toString(mOffsetValue));
		mFlexPie.setSelectedItemOffset(mOffsetValue);
		mFlexPie.setSelectionMode(ChartSelectionModeType.POINT);
		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.selection_spinner_values, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mSelectionSpinner.setAdapter(adapter);
		mSelectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// set the position of selected item
				switch (position)
				{
					case 0:
						mFlexPie.setSelectedItemPosition(ChartPositionType.NONE);
						break;
					case 1:
						mFlexPie.setSelectedItemPosition(ChartPositionType.LEFT);
						break;
					case 2:
						mFlexPie.setSelectedItemPosition(ChartPositionType.TOP);
						break;
					case 3:
						mFlexPie.setSelectedItemPosition(ChartPositionType.RIGHT);
						break;
					case 4:
						mFlexPie.setSelectedItemPosition(ChartPositionType.BOTTOM);
						break;

				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mSelectionSpinner.setSelection(1);

		mAnimatedSwitch.setChecked(true);
		mAnimatedSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// set animation flag
				mFlexPie.setAnimated(isChecked);
			}
		});
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.buttonMinus:
				// decrease offset value
				mOffsetValue -= mOffsetValue > 0 ? 0.1 : 0;
				mOffsetValue = ((int) (mOffsetValue * 10)) / 10f;

				if (mOffsetValue == 0f)
					mButtonMinus.setEnabled(false);
				else if (!mButtonPlus.isEnabled())
					mButtonPlus.setEnabled(true);

				mOffset.setText(Float.toString(mOffsetValue));
				mFlexPie.setSelectedItemOffset(mOffsetValue);
				break;

			case R.id.buttonPlus:
				// increase offset value
				mOffsetValue += mOffsetValue < 1 ? 0.1 : 0;
				mOffsetValue = ((int) (mOffsetValue * 10)) / 10f;

				if (mOffsetValue == 1f)
					mButtonPlus.setEnabled(false);
				else if (!mButtonMinus.isEnabled())
					mButtonMinus.setEnabled(true);

				mOffset.setText(Float.toString(mOffsetValue));
				mFlexPie.setSelectedItemOffset(mOffsetValue);
				break;
		}
	}

}
