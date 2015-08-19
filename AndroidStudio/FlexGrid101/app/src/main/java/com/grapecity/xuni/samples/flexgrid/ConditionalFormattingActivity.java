package com.grapecity.xuni.samples.flexgrid;

import android.os.Bundle;

import com.grapecity.xuni.flexgrid.*;

public class ConditionalFormattingActivity extends BaseChartPointActivity
{
	private GridCellFactory mCellFactory;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conditional_formatting);

		// disabling auto column generation
		// property set in XML layout
		// mGrid.setAutoGenerateColumns(false);

		// setting new cell factory
		mCellFactory = new ConditionalCellFactory(mGrid, this);
		mGrid.setCellFactory(mCellFactory);
	}
}