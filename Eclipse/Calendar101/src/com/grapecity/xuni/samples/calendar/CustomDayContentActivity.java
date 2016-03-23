package com.grapecity.xuni.samples.calendar;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grapecity.xuni.calendar.CalendarDayOfWeekSlot;
import com.grapecity.xuni.calendar.CalendarDayOfWeekSlotLoadingEventArgs;
import com.grapecity.xuni.calendar.CalendarDaySlotBase;
import com.grapecity.xuni.calendar.CalendarDaySlotLoadingEventArgs;
import com.grapecity.xuni.calendar.CalendarDetailDaySlot;
import com.grapecity.xuni.calendar.DayOfWeek;
import com.grapecity.xuni.calendar.XuniCalendar;
import com.grapecity.xuni.core.IEventHandler;

public class CustomDayContentActivity extends Activity
{
	private XuniCalendar calendar;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);

		context = this;

		calendar = (XuniCalendar) findViewById(R.id.calendar1);

		calendar.getDayOfWeekSlotLoading().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				CalendarDayOfWeekSlotLoadingEventArgs args = (CalendarDayOfWeekSlotLoadingEventArgs) arg1;
				DayOfWeek dayOfWeek = args.getDayOfWeek();
				CalendarDayOfWeekSlot slot = (CalendarDayOfWeekSlot) args.getDayOfWeekSlot();
				if (dayOfWeek == DayOfWeek.Sunday || dayOfWeek == DayOfWeek.Saturday)
				{
					slot.setDayOfWeekTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));
				}
				else
				{
					slot.setDayOfWeekTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
				}
			}
		}, this);

		calendar.getDaySlotLoading().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				CalendarDaySlotLoadingEventArgs args = (CalendarDaySlotLoadingEventArgs) arg1;

				Date date = args.getDate();

				if (args.isAdjacentDay())
				{
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int day = cal.get(Calendar.DAY_OF_MONTH);

					CalendarDetailDaySlot view = new CalendarDetailDaySlot(context);
					view.setDayText(String.valueOf(day));
					view.setBackgroundColor(Color.WHITE);
					view.setDayTextColor(Color.GRAY);
					view.setDetailText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1));
					view.setDetailFontSize(8);
					view.setDetailTextColor(Color.GRAY);

					args.setDaySlot(view);
				}
				else
				{
					CalendarDaySlotBase layout = new CalendarDaySlotBase(context);

					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int day = cal.get(Calendar.DAY_OF_MONTH);

					TextView tv = new TextView(context);
					tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
					tv.setText(String.valueOf(day));

					ImageView iv = new ImageView(context);
					iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

					if (day >= 14 && day <= 23)
					{
						tv.setTextSize(9);
						tv.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
						tv.setGravity(Gravity.LEFT | Gravity.TOP);

						iv.setImageResource(weatherIconIds[day % 4]);
					}
					else
					{
						tv.setTextSize(18);
						tv.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
						tv.setGravity(Gravity.CENTER);

						iv.setImageResource(dotResourceIds[day % 3]);
					}

					layout.addView(tv);
					layout.addView(iv);
					layout.setGravity(Gravity.CENTER);
					layout.setOrientation(LinearLayout.VERTICAL);
					layout.setBackgroundColor(Color.WHITE);

					args.setDaySlot(layout);
				}
			}
		}, this);

		Calendar cal = Calendar.getInstance();
		cal.set(2016, 1, 1);
		calendar.setDisplayDate(cal.getTime());
	}

	String[] titles =
	{ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
	int[] dotResourceIds =
	{ R.drawable.blue, R.drawable.red, R.drawable.green };
	int[] weatherIconIds =
	{ R.drawable.sunny, R.drawable.cloudy, R.drawable.rainy, R.drawable.stomy };
}