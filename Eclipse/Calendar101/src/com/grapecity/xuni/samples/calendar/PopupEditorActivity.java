package com.grapecity.xuni.samples.calendar;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.grapecity.xuni.calendar.XuniCalendar;
import com.grapecity.xuni.core.IEventHandler;

public class PopupEditorActivity extends Activity
{
	PopupWindow popUp;
	LinearLayout calendarLayout;
	LinearLayout mainLayout;
	TextView tv;
	Button btn;
	boolean hidden = true;
	XuniCalendar calendar;

	int width = 600;
	int height = 400;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		
		calendar = new XuniCalendar(this);
		calendar.setLayoutParams(new LayoutParams(width - 100, height - 200));
		calendar.getSelectionChanged().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				popUp.dismiss();
				hidden = true;

				tv.setText(String.format(getString(R.string.showSelectedDate), calendar.getSelectedDates().get(0)));
			}
		}, this);

		popUp = new PopupWindow(this);
		calendarLayout = new LinearLayout(this);
		mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		tv = new TextView(this);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(16);
		btn = new Button(this);
		btn.setText(R.string.selectDate);
		btn.setGravity(Gravity.CENTER);
		btn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				if (hidden)
				{
					popUp.showAtLocation(mainLayout, Gravity.CENTER, 0, 0);
					popUp.update(0, 200, width - 100, height - 200);
					hidden = false;
				}
				else
				{
					popUp.dismiss();
					hidden = true;
				}
			}
		});
		calendarLayout.addView(calendar);
		popUp.setContentView(calendarLayout);
		mainLayout.addView(btn);
		mainLayout.addView(tv);
		setContentView(mainLayout);
	}
}