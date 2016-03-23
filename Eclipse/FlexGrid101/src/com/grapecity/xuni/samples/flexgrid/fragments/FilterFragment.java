package com.grapecity.xuni.samples.flexgrid.fragments;

import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.R.array;
import com.grapecity.xuni.samples.flexgrid.R.id;
import com.grapecity.xuni.samples.flexgrid.R.layout;
import com.grapecity.xuni.samples.flexgrid.util.FilterSelection;

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

	private EditText mFirstNameEditText;
	private EditText mLastNameEditText;
	private EditText mAddressEditText;
	private EditText mCityEditText;
	private EditText mPostalCodeEditText;
	private EditText mEmailEditText;

	private Spinner mFirstNameSpinner;
	private Spinner mLastNameSpinner;
	private Spinner mAddressSpinner;
	private Spinner mCitySpinner;
	private Spinner mPostalCodeSpinner;
	private Spinner mEmailSpinner;

	private Button mFilterButton;
	private Button mCancelButton;

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

		getDialog().setTitle(R.string.selectFilterTitle);

		// creating filter variables to hold all the filters
		mFilter = new FilterSelection[6];
		mFilterValue = new String[6];

		mFirstNameEditText = (EditText) rootView.findViewById(R.id.firstNameEditText);
		mLastNameEditText = (EditText) rootView.findViewById(R.id.lastNameEditText);
		mAddressEditText = (EditText) rootView.findViewById(R.id.addressEditText);
		mCityEditText = (EditText) rootView.findViewById(R.id.cityEditText);
		mPostalCodeEditText = (EditText) rootView.findViewById(R.id.postalCodeEditText);
		mEmailEditText = (EditText) rootView.findViewById(R.id.emailEditText);

		mFirstNameSpinner = (Spinner) rootView.findViewById(R.id.firstNameSpinner);
		mLastNameSpinner = (Spinner) rootView.findViewById(R.id.lastNameSpinner);
		mAddressSpinner = (Spinner) rootView.findViewById(R.id.addressSpinner);
		mCitySpinner = (Spinner) rootView.findViewById(R.id.citySpinner);
		mPostalCodeSpinner = (Spinner) rootView.findViewById(R.id.postalCodeSpinner);
		mEmailSpinner = (Spinner) rootView.findViewById(R.id.emailSpinner);

		mFilterButton = (Button) rootView.findViewById(R.id.filterButton);
		mCancelButton = (Button) rootView.findViewById(R.id.cancelButton);

		// initializing the adapter to string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.filterSpinnerValues,
				android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		initializeSpinner(mFirstNameSpinner, adapter, 0);
		initializeSpinner(mLastNameSpinner, adapter, 1);
		initializeSpinner(mAddressSpinner, adapter, 2);
		initializeSpinner(mCitySpinner, adapter, 3);
		initializeSpinner(mPostalCodeSpinner, adapter, 4);
		initializeSpinner(mEmailSpinner, adapter, 5);

		mFilterButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// setting up default filter value as null, only if the user
				// enters some filter value
				if (mFirstNameEditText.getText().length() != 0 && mFilter[0] == FilterSelection.NONE)
					mFilter[0] = FilterSelection.CONTAINS;
				if (mLastNameEditText.getText().length() != 0 && mFilter[1] == FilterSelection.NONE)
					mFilter[1] = FilterSelection.CONTAINS;
				if (mAddressEditText.getText().length() != 0 && mFilter[2] == FilterSelection.NONE)
					mFilter[2] = FilterSelection.CONTAINS;
				if (mCityEditText.getText().length() != 0 && mFilter[3] == FilterSelection.NONE)
					mFilter[3] = FilterSelection.CONTAINS;
				if (mPostalCodeEditText.getText().length() != 0 && mFilter[4] == FilterSelection.NONE)
					mFilter[4] = FilterSelection.CONTAINS;
				if (mEmailEditText.getText().length() != 0 && mFilter[5] == FilterSelection.NONE)
					mFilter[5] = FilterSelection.CONTAINS;

				// saving filters
				mFilterValue[0] = mFirstNameEditText.getText().toString();
				mFilterValue[1] = mLastNameEditText.getText().toString();
				mFilterValue[2] = mAddressEditText.getText().toString();
				mFilterValue[3] = mCityEditText.getText().toString();
				mFilterValue[4] = mPostalCodeEditText.getText().toString();
				mFilterValue[5] = mEmailEditText.getText().toString();

				// passing filters
				mDataPasser.saveData(mFilter, mFilterValue);
				dismiss();
			}
		});

		mCancelButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dismiss();
			}
		});

		return rootView;
	}

	private void initializeSpinner(Spinner spinner, ArrayAdapter<CharSequence> adapter, final int index)
	{
		spinner.setAdapter(adapter);

		spinner.setTag(index);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				setFilter(index, position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});
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