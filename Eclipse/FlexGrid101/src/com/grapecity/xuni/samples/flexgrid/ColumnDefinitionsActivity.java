package com.grapecity.xuni.samples.flexgrid;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexgrid.*;

public class ColumnDefinitionsActivity extends Activity
{

	private FlexGrid mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_column_definitions);

		// initializing grid
		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		// disable auto column generation
		// property set in XML layout
		// mGrid.setAutoGenerateColumns(false);

		// initialize new columns
		GridColumn columnId = new GridColumn(mGrid, "ID", "id");
		GridColumn columnFirst = new GridColumn(mGrid, "First", "first");
		GridColumn columnLast = new GridColumn(mGrid, "Last", "last");
		GridColumn columnWeight = new GridColumn(mGrid, "Weight", "weight");

		// add new columns
		mGrid.getColumns().add(columnId);
		mGrid.getColumns().add(columnFirst);
		mGrid.getColumns().add(columnLast);
		mGrid.getColumns().add(columnWeight);
		mGrid.setItemsSource(getList());

		// set column format
		mGrid.getColumns().getColumn("Weight").setFormat("#.##");
	}

	// a method to create a list of random ChartPoint objects
	private static ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		int n = 100; // number of series elements
		String[] firstNames =
		{ "Paul", "Ben", "Ted", "Ed", "Dan", "Jack" };
		String[] lastNames =
		{ "Richards", "Neiman", "Evers", "Lehman", "Krause", "Stevens" };
		int m = 6;
		Random random = new Random();
		for (int i = 0; i < n; i++)
		{
			list.add(new ChartPoint(i, firstNames[random.nextInt(m)], lastNames[m - random.nextInt(m) - 1], random.nextFloat() * 200));
		}
		return list;
	}
}
