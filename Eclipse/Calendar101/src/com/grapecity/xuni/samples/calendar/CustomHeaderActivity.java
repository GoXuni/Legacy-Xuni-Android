package com.grapecity.xuni.samples.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.grapecity.xuni.calendar.CalendarViewMode;
import com.grapecity.xuni.calendar.XuniCalendar;
import com.grapecity.xuni.core.IEventHandler;

public class CustomHeaderActivity extends Activity
{
	private XuniCalendar calendar;
	private Spinner mViewModeSpinner;
	private TextView tvHeader;
	private Button btnToday;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_header);
		calendar = (XuniCalendar) findViewById(R.id.calendar1);
		mViewModeSpinner = (Spinner) findViewById(R.id.selectViewMode);
		tvHeader = (TextView) findViewById(R.id.header);
		btnToday = (Button) findViewById(R.id.todayBtn);
		btnToday.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Date now = new Date();
				calendar.setSelectedDate(now);
				calendar.setDisplayDate(now);

			}
		});

		calendar.setShowHeader(false);
		calendar.getDisplayModeChanged().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				if (calendar.getViewMode() == CalendarViewMode.Month)
				{
					mViewModeSpinner.setSelection(0);
				}
				else
				{
					mViewModeSpinner.setSelection(1);
				}

				updateHeader();
			}
		}, this);

		calendar.getDisplayDateChanged().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				updateHeader();
			}
		}, this);

		ArrayAdapter<CharSequence> adapterViewMode = ArrayAdapter.createFromResource(this, R.array.viewModeSpinnerValues, android.R.layout.simple_spinner_item);
		adapterViewMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mViewModeSpinner.setAdapter(adapterViewMode);
		mViewModeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				switch (position)
				{
					case 0:
						calendar.setViewMode(CalendarViewMode.Month);
						break;
					case 1:
						calendar.setViewMode(CalendarViewMode.Year);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
	}

	private void updateHeader()
	{
		Locale locale = Locale.getDefault();
		String format;
		if (locale.getLanguage().equals("ja") || locale.getLanguage().equals("zh"))
		{
			format = calendar.getViewMode() == CalendarViewMode.Month ? "yyyy年M月" : "yyyy年";
		}
		else
		{
			format = calendar.getViewMode() == CalendarViewMode.Month ? "MMMM yyyy" : "yyyy";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
		tvHeader.setText(formatter.format(calendar.getDisplayDate()));
	}
}
