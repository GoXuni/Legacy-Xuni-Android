package com.grapecity.xuni.samples.input;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.grapecity.xuni.calendar.XuniCalendar;
import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.input.dropdown.XuniDropDown;
import com.grapecity.xuni.input.mask.XuniMaskedTextView;

public class DropDownActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		final XuniDropDown dropdown = new XuniDropDown(this);
		final XuniMaskedTextView header = new XuniMaskedTextView(this);
		header.setMask("00/00/0000");

		final XuniCalendar calendar = new XuniCalendar(this);
		dropdown.setHeader(header);
		dropdown.setDropdown(calendar);

		dropdown.setAnimated(true);
		
		dropdown.setDropDownHeight(400);

		calendar.getSelectionChanged().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				dropdown.setDropDownOpen(false);
				SimpleDateFormat format = new SimpleDateFormat("MMddyyyy", Locale.getDefault());
				String date = format.format(calendar.getSelectedDates().get(0));
				header.setValue(date);
			}
		}, this);
		this.setContentView(dropdown);

	}
}
