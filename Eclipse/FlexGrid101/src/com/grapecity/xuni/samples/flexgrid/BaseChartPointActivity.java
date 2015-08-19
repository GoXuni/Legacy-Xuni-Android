package com.grapecity.xuni.samples.flexgrid;

import com.grapecity.xuni.flexgrid.FlexGrid;

import android.app.Activity;

public class BaseChartPointActivity extends Activity
{
	protected FlexGrid mGrid;
	private DefaultCellFactory mDefaultCellFactory;

	@Override
	public void setContentView(int resId)
	{
		super.setContentView(resId);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		// setting data source and default values
		mGrid.setItemsSource(ChartPoint.getList());
		mGrid.getColumns().getColumn("weight").setFormat("#.##");
		mGrid.getColumns().getColumn("hiredDate").setFormat("MMM dd yyyy");
		mGrid.getColumns().getColumn("hiredTime").setFormat("HH:mm:ss");

		mDefaultCellFactory = new DefaultCellFactory(mGrid);
		mGrid.setCellFactory(mDefaultCellFactory);
	}

}
