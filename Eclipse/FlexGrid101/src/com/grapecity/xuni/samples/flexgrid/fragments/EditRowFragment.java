package com.grapecity.xuni.samples.flexgrid.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.R.id;
import com.grapecity.xuni.samples.flexgrid.R.layout;
import com.grapecity.xuni.samples.flexgrid.R.string;
import com.grapecity.xuni.samples.flexgrid.data.Customer;
import com.grapecity.xuni.samples.flexgrid.samples.EditingActivity;

public class EditRowFragment extends DialogFragment
{
	private FlexGrid mGrid;

	private Button mButtonSave;
	private Button mButtonCancel;
	private TextView mIdValue;
	private EditText mNameValue;
	private EditText mAddressValue;
	private EditText mCityValue;
	private Spinner mCountryValue;
	private EditText mPostalCodeValue;
	private CheckBox mActiveValue;

	@Override
	public void onAttach(Activity a)
	{
		super.onAttach(a);

		mGrid = ((EditingActivity) a).mGrid;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View rootView = inflater.inflate(R.layout.fragment_edit_row, container, false);
		getDialog().setTitle(R.string.editRow);

		// initializing widgets
		mButtonSave = (Button) rootView.findViewById(R.id.buttonSave);
		mButtonCancel = (Button) rootView.findViewById(R.id.buttonCancel);
		mIdValue = (TextView) rootView.findViewById(R.id.idValue);
		mNameValue = (EditText) rootView.findViewById(R.id.nameValue);
		mAddressValue = (EditText) rootView.findViewById(R.id.addressValue);
		mCityValue = (EditText) rootView.findViewById(R.id.cityValue);
		mCountryValue = (Spinner) rootView.findViewById(R.id.countryValue);
		mPostalCodeValue = (EditText) rootView.findViewById(R.id.postalCodeValue);
		mActiveValue = (CheckBox) rootView.findViewById(R.id.activeValue);

		String[] countryNames = new String[Customer.COUNTRIES.size()];
		
		Customer.COUNTRIES.keySet().toArray(countryNames);
		
		// initializing adapter to string array
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, countryNames);

		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		mCountryValue.setAdapter(adapter1);
		
		GridCellRange selectedRow = mGrid.getSelection();
		final Customer customer = (Customer) mGrid.getCollectionView().getItems().get(selectedRow.row);

		mIdValue.setText("" + customer.getId());
		mNameValue.setText(customer.getName());
		mAddressValue.setText(customer.getAddress());
		mCityValue.setText(customer.getCity());
		mCountryValue.setSelection(customer.getCountryId());
		mPostalCodeValue.setText(customer.getPostalCode());
		mActiveValue.setChecked(customer.isActive());

		mButtonSave.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				String[] names = mNameValue.getText().toString().split(" ");
				
				if(names.length > 0)
				{
					customer.setFirstName(names[0]);
				}
				
				if(names.length > 1)
				{
					customer.setLastName(names[1]);
				}
				
				customer.setAddress(mAddressValue.getText().toString());
				customer.setCity(mCityValue.getText().toString());
				customer.setCountryId(mCountryValue.getSelectedItemPosition());
				customer.setPostalCode(mPostalCodeValue.getText().toString());
				
				customer.setActive(mActiveValue.isChecked());
			
				dismiss();
			}
		});

		mButtonCancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// dismissing the fragment
				dismiss();
			}
		});

		return rootView;
	}
}