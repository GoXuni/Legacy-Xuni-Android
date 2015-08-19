package com.grapecity.xuni.samples.flexgrid;

import android.os.Bundle;

public class GettingStartedActivity extends BaseChartPointActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// FlexGrid automatically adjusts to the current theme
		setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);
	}
}
