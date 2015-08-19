package com.grapecity.xuni.samples.flexgrid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class EditRowFragment extends DialogFragment
{

	OnDataPass mDataPasser;
	private Button mButtonSave;
	private Button mButtonCancel;
	private EditText mIdValue;
	private EditText mNameValue;
	private CheckBox mActiveValue;
	private EditText mFirstValue;
	private EditText mLastValue;
	private EditText mWeightValue;
	private DatePicker mHiredValue;
	private EditText mFatherValue;
	private EditText mBrotherValue;
	private EditText mCousinValue;
	private String[] mRowData;
	private TimePicker mHiredTime;

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
		View rootView = inflater.inflate(R.layout.fragment_edit_row, container, false);
		getDialog().setTitle("Edit Row");

		// initializing widgets
		mButtonSave = (Button) rootView.findViewById(R.id.buttonSave);
		mButtonCancel = (Button) rootView.findViewById(R.id.buttonCancel);
		mIdValue = (EditText) rootView.findViewById(R.id.idValue);
		mNameValue = (EditText) rootView.findViewById(R.id.nameValue);
		mActiveValue = (CheckBox) rootView.findViewById(R.id.activeValue);
		mFirstValue = (EditText) rootView.findViewById(R.id.firstValue);
		mLastValue = (EditText) rootView.findViewById(R.id.lastValue);
		mHiredValue = (DatePicker) rootView.findViewById(R.id.hiredValue);
		mHiredTime = (TimePicker) rootView.findViewById(R.id.hiredTime);
		mWeightValue = (EditText) rootView.findViewById(R.id.weightValue);
		mFatherValue = (EditText) rootView.findViewById(R.id.fatherValue);
		mBrotherValue = (EditText) rootView.findViewById(R.id.brotherValue);
		mCousinValue = (EditText) rootView.findViewById(R.id.cousinValue);

		// displaying original row values
		mRowData = getArguments().getStringArray("data");
		mIdValue.setText(mRowData[0]);
		mNameValue.setText(mRowData[1]);
		mNameValue.setEnabled(false);
		mActiveValue.setChecked(Boolean.parseBoolean(mRowData[4]));
		mFirstValue.setText(mRowData[5]);
		mLastValue.setText(mRowData[6]);
		mHiredValue.setCalendarViewShown(false);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		try
		{
			cal1.setTime(new SimpleDateFormat("MMM dd yyyy", Locale.getDefault()).parse(mRowData[7]));
			cal2.setTime(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).parse(mRowData[7]));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		mHiredValue.updateDate(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
		mHiredTime.setCurrentHour(cal2.HOUR);
		mHiredTime.setCurrentMinute(cal2.MINUTE);
		mWeightValue.setText(mRowData[8]);
		mFatherValue.setText(mRowData[9]);
		mBrotherValue.setText(mRowData[10]);
		mCousinValue.setText(mRowData[11]);

		mButtonSave.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// setting new edited values in ChartPoint object
				ChartPoint data = new ChartPoint();
				data.setCountryId(Integer.valueOf(mRowData[2]));
				data.setCountry(mRowData[3]);
				data.setId(Integer.valueOf(mIdValue.getText().toString()));
				data.setName(mNameValue.getText().toString());
				data.setActive(mActiveValue.isChecked());
				data.setFirst(mFirstValue.getText().toString());
				data.setLast(mLastValue.getText().toString());
				data.setHiredDate(new Date(mHiredValue.getYear() - 1900, mHiredValue.getMonth(), mHiredValue.getDayOfMonth()));
				data.setHiredTime(new Date(mHiredTime.getCurrentHour(),mHiredTime.getCurrentMinute(), 0));
				data.setWeight(Float.valueOf(mWeightValue.getText().toString()));
				data.setFather(mFatherValue.getText().toString());
				data.setBrother(mBrotherValue.getText().toString());
				data.setCousin(mCousinValue.getText().toString());

				// sending data back to editing activity
				mDataPasser.saveData(data);
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

	public interface OnDataPass
	{
		public void saveData(ChartPoint data);
	}
}