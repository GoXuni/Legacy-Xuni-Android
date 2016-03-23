package com.grapecity.xuni.samples.calendar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.grapecity.xuni.calendar.XuniCalendar;

public class CustomAppearanceActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_appearance);

		XuniCalendar calendar = (XuniCalendar) findViewById(R.id.calendar1);

		calendar.setHeaderBackgroundColor(Color.rgb(61, 131, 75));
		calendar.setHeaderTextColor(Color.WHITE);
		calendar.setHeaderFontSize(36);
		calendar.setHeaderFontTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));

		calendar.setDayOfWeekFontSize(28);
		calendar.setDayOfWeekBackgroundColor(Color.rgb(99, 166, 70));
		calendar.setDayOfWeekTextColor(Color.WHITE);
		calendar.setDayOfWeekFormat("d");

		calendar.setBorderWidth(10);
		calendar.setBorderColor(Color.BLACK);
		calendar.setDayBorderWidth(2);
		calendar.setDayBorderColor(Color.LTGRAY);

		calendar.setAdjacentDayTextColor(Color.LTGRAY);
		calendar.setFontSize(16);
		calendar.setTodayFontTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));

	}
}
