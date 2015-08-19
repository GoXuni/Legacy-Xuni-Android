package com.grapecity.xuni.samples.flexgrid;

import android.os.Bundle;

public class EditConfirmationActivity extends BaseChartPointActivity
{
	private EditCellFactory mEditCellFactory;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_confirm);

		mEditCellFactory = new EditCellFactory(mGrid, EditConfirmationActivity.this);
		mGrid.setCellFactory(mEditCellFactory);
	}
}
