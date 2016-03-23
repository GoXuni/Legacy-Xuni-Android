package com.grapecity.xuni.samples.calendar;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.calendar.CalendarOrientation;
import com.grapecity.xuni.calendar.XuniCalendar;

public class VerticalOrientationActivity extends Activity
{
	private XuniCalendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);

		// initializing widgets
		calendar = (XuniCalendar) findViewById(R.id.calendar1);
		calendar.setOrientation(CalendarOrientation.Vertical);
	}
}
