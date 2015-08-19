package com.grapecity.xuni.samples.flexgrid;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class FilterFragment extends DialogFragment
{

	OnDataPass mDataPasser;
	private FilterSelection mFilter[];
	private String mFilterValue[];
	private Button mButtonFilter;
	private EditText mNameValue;
	private EditText mCountryValue;
	private EditText mFirstValue;
	private EditText mLastValue;
	private EditText mFatherValue;
	private EditText mBrotherValue;
	private EditText mCousinValue;
	private Spinner mNameSpinner;
	private Spinner mCountrySpinner;
	private Spinner mFirstSpinner;
	private Spinner mLastSpinner;
	private Spinner mFatherSpinner;
	private Spinner mBrotherSpinner;
	private Spinner mCousinSpinner;

	@Override
	public void onAttach(Activity a)
	{
		super.onAttach(a);
		mDataPasser = (OnDataPass) a;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_filter, container, false);
		getDialog().setTitle("Select Filter");

		// creating filter variables to hold all the filters
		mFilter = new FilterSelection[7];
		mFilterValue = new String[7];

		// initializing text fields
		mButtonFilter = (Button) rootView.findViewById(R.id.buttonFilter);
		mNameValue = (EditText) rootView.findViewById(R.id.nameValue);
		mCountryValue = (EditText) rootView.findViewById(R.id.countryValue);
		mFirstValue = (EditText) rootView.findViewById(R.id.firstValue);
		mLastValue = (EditText) rootView.findViewById(R.id.lastValue);
		mFatherValue = (EditText) rootView.findViewById(R.id.fatherValue);
		mBrotherValue = (EditText) rootView.findViewById(R.id.brotherValue);
		mCousinValue = (EditText) rootView.findViewById(R.id.cousinValue);

		// initializing spinners
		mNameSpinner = (Spinner) rootView.findViewById(R.id.nameSpinner);
		mCountrySpinner = (Spinner) rootView.findViewById(R.id.countrySpinner);
		mFirstSpinner = (Spinner) rootView.findViewById(R.id.firstSpinner);
		mLastSpinner = (Spinner) rootView.findViewById(R.id.lastSpinner);
		mFatherSpinner = (Spinner) rootView.findViewById(R.id.fatherSpinner);
		mBrotherSpinner = (Spinner) rootView.findViewById(R.id.brotherSpinner);
		mCousinSpinner = (Spinner) rootView.findViewById(R.id.cousinSpinner);

		// initializing the adapter to string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.filterSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinners
		mNameSpinner.setAdapter(adapter);
		mCountrySpinner.setAdapter(adapter);
		mFirstSpinner.setAdapter(adapter);
		mLastSpinner.setAdapter(adapter);
		mFatherSpinner.setAdapter(adapter);
		mBrotherSpinner.setAdapter(adapter);
		mCousinSpinner.setAdapter(adapter);

		// setting up listeners on all spinners
		mNameSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(0, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mCountrySpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(1, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mFirstSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(2, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mLastSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(3, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mFatherSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(4, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mBrotherSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(5, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mCousinSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(6, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mButtonFilter.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{

				// setting up default filter value as null, only if the user
				// enters some filter value
				if (mNameValue.getText().length() != 0 && mFilter[0] == FilterSelection.NONE)
					mFilter[0] = FilterSelection.CONTAINS;
				if (mCountryValue.getText().length() != 0 && mFilter[1] == FilterSelection.NONE)
					mFilter[1] = FilterSelection.CONTAINS;
				if (mFirstValue.getText().length() != 0 && mFilter[2] == FilterSelection.NONE)
					mFilter[2] = FilterSelection.CONTAINS;
				if (mLastValue.getText().length() != 0 && mFilter[3] == FilterSelection.NONE)
					mFilter[3] = FilterSelection.CONTAINS;
				if (mFatherValue.getText().length() != 0 && mFilter[4] == FilterSelection.NONE)
					mFilter[4] = FilterSelection.CONTAINS;
				if (mBrotherValue.getText().length() != 0 && mFilter[5] == FilterSelection.NONE)
					mFilter[5] = FilterSelection.CONTAINS;
				if (mCousinValue.getText().length() != 0 && mFilter[6] == FilterSelection.NONE)
					mFilter[6] = FilterSelection.CONTAINS;

				// saving filters
				mFilterValue[0] = mNameValue.getText().toString();
				mFilterValue[1] = mCountryValue.getText().toString();
				mFilterValue[2] = mFirstValue.getText().toString();
				mFilterValue[3] = mLastValue.getText().toString();
				mFilterValue[4] = mFatherValue.getText().toString();
				mFilterValue[5] = mBrotherValue.getText().toString();
				mFilterValue[6] = mCousinValue.getText().toString();

				// passing filters
				mDataPasser.saveData(mFilter, mFilterValue);
				dismiss();
			}
		});

		return rootView;
	}

	public void setFilter(int field, int position)
	{
		// save filter type
		switch (position)
		{
			case 0:
				mFilter[field] = FilterSelection.CONTAINS;
				break;
			case 1:
				mFilter[field] = FilterSelection.EQUALS;
				break;
			case 2:
				mFilter[field] = FilterSelection.STARTSWITH;
				break;
			case 3:
				mFilter[field] = FilterSelection.ENDSWITH;
				break;
		}
	}

	public interface OnDataPass
	{
		public void saveData(FilterSelection[] filter, String[] filterValue);
	}
}