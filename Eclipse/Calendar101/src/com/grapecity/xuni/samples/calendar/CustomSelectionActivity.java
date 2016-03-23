package com.grapecity.xuni.samples.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.calendar.CalendarSelectionChangingEventArgs;
import com.grapecity.xuni.calendar.XuniCalendar;
import com.grapecity.xuni.core.IEventHandler;

public class CustomSelectionActivity extends Activity
{
	private XuniCalendar calendar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_selection);
		calendar = (XuniCalendar) findViewById(R.id.calendar1);
		calendar.setMaxSelectionCount(1000);

		calendar.getSelectionChanging().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				CalendarSelectionChangingEventArgs arg = (CalendarSelectionChangingEventArgs) arg1;
				List<Date> days = arg.getSelectedDates();
				List<Date> workdays = new ArrayList<Date>();
				for (Date date : days)
				{
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
					if (dayOfWeek > 0 && dayOfWeek < 6)
					{
						workdays.add(date);
					}
				}

				arg.setSelectedDates(workdays);
			}
		}, this);
	}
}
