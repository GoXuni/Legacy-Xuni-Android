package com.grapecity.xuni.samples.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.calendar.CalendarHeaderLoadingEventArgs;
import com.grapecity.xuni.calendar.CalendarViewMode;
import com.grapecity.xuni.calendar.XuniCalendar;
import com.grapecity.xuni.core.IEventHandler;

public class CustomHeaderTextActivity extends Activity
{
	private XuniCalendar calendar;
	private Locale locale = Locale.getDefault();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);

		calendar = (XuniCalendar) findViewById(R.id.calendar1);

		String format = locale.getLanguage().equals("ja") ? "'今は 'yyyy年 MM" : "'The current date is 'yyyyMM";
		calendar.setHeaderMonthFormat(format);

		calendar.getHeaderLoading().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				CalendarHeaderLoadingEventArgs args = (CalendarHeaderLoadingEventArgs) arg1;
				if (args.getViewMode() == CalendarViewMode.Year)
				{
					DateFormat format = new SimpleDateFormat("GGGGyyyy", locale);
					args.setHeader(format.format(args.getDate()));
				}
			}
		}, this);
		calendar.refresh();
	}
}
