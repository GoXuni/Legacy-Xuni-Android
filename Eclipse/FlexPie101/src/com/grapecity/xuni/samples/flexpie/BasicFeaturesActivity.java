package com.grapecity.xuni.samples.flexpie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexpie.*;

public class BasicFeaturesActivity extends Activity
{
	private FlexPie mflexPie;
	private float mInnerRadius = 0.3f;
	private TextView mRadius;
	private Switch mReversedSwitch;
	private SeekBar mOffsetSeekbar;
	private SeekBar mStartAngleSeekbar;
	private Button mButtonMinus;
	private Button mButtonPlus;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_features);

		// initializing widgets
		mflexPie = (FlexPie) findViewById(R.id.donutPie);
		mRadius = (TextView) findViewById(R.id.radius);
		mReversedSwitch = (Switch) findViewById(R.id.reversedSwitch);
		mOffsetSeekbar = (SeekBar) findViewById(R.id.offsetSeekBar);
		mStartAngleSeekbar = (SeekBar) findViewById(R.id.startAngleSeekBar);
		mButtonMinus = (Button) findViewById(R.id.buttonMinus);
		mButtonPlus = (Button) findViewById(R.id.buttonPlus);

		// creating a list of fruit objects of type BindObject
		ObservableList<Object> mFlexdonutFruits = new ObservableList<Object>();
		mFlexdonutFruits.add(new BindObject("Oranges", 11));
		mFlexdonutFruits.add(new BindObject("Apples", 94));
		mFlexdonutFruits.add(new BindObject("Pears", 93));
		mFlexdonutFruits.add(new BindObject("Bananas", 2));
		mFlexdonutFruits.add(new BindObject("Pineapples", 53));

		// set the binding of FlexPie to variables of BindObject
		mflexPie.setBindingName("name");
		mflexPie.setBinding("value");

		// setting the source of data/items in FlexPie
		mflexPie.setItemsSource(mFlexdonutFruits);

		// setting default values
		mRadius.setText(Float.toString(mInnerRadius));
		mflexPie.setInnerRadius(mInnerRadius);

		mReversedSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// setting reversed flag
				mflexPie.setReversed(isChecked);
			}
		});

		mOffsetSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			float progress = 0;

			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				// calculating and setting offset from progress of seekbar
				this.progress = progress / 100f;
				mflexPie.setOffset(this.progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});

		mStartAngleSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			float progress = 0;

			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				// calculating and setting start angle from progress of seekbar
				this.progress = progress * 3.6f;
				mflexPie.setStartAngle(this.progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			public void onStopTrackingTouch(SeekBar seekBar)
			{

			}
		});
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.buttonMinus:
				// decrease inner radius
				mInnerRadius -= mInnerRadius > 0 ? 0.1 : 0;
				mInnerRadius = ((int) (mInnerRadius * 10)) / 10f;

				// enable button only for valid radius
				if (mInnerRadius == 0f)
					mButtonMinus.setEnabled(false);
				else if (!mButtonPlus.isEnabled())
					mButtonPlus.setEnabled(true);

				mRadius.setText(Float.toString(mInnerRadius));
				mflexPie.setInnerRadius(mInnerRadius);
				break;

			case R.id.buttonPlus:
				// increase inner radius
				mInnerRadius += mInnerRadius < 1 ? 0.1 : 0;
				mInnerRadius = ((int) (mInnerRadius * 10)) / 10f;

				// enable button only for valid radius
				if (mInnerRadius == 1f)
					mButtonPlus.setEnabled(false);
				else if (!mButtonMinus.isEnabled())
					mButtonMinus.setEnabled(true);

				mRadius.setText(Float.toString(mInnerRadius));
				mflexPie.setInnerRadius(mInnerRadius);
				break;
		}
	}
}
