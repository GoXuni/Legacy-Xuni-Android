package com.grapecity.xuni.samples.flexchart;

import com.grapecity.xuni.core.ObservableList;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity
{
	protected ObservableList<ChartPoint> dataSource;
	protected static final String DATA_SOURCE = "DATA_SOURCE";

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		outState.putSerializable(DATA_SOURCE, dataSource);
		super.onSaveInstanceState(outState);
	}
}
