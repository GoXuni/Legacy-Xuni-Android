package com.grapecity.xuni.samples.flexgrid;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexgrid.*;

public class CustomCellsActivity extends Activity
{

	private FlexGrid mGrid;
	private GridCellFactory mCellFactory;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_cells);

		// initializing flexgrid
		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		// disabling auto column generation
		// property set in XML layout
		// mGrid.setAutoGenerateColumns(false);

		// setting new cell factory
		mCellFactory = new CellFactory(mGrid, this);
		mGrid.setCellFactory(mCellFactory);

		// initializing new columns
		GridColumn columnFirst = new GridColumn(mGrid, "First", "first");
		GridColumn columnLast = new GridColumn(mGrid, "Last", "last");
		GridColumn columnWeight = new GridColumn(mGrid, "Performance", "performanceGauge");

		// adding new columns
		mGrid.getColumns().add(columnFirst);
		mGrid.getColumns().add(columnLast);
		mGrid.getColumns().add(columnWeight);
		mGrid.setItemsSource(getList());
	}

	// a method to create a list of random CustomCellPoint objects
	private static ObservableList<CustomCellPoint> getList()
	{
		ObservableList<CustomCellPoint> list = new ObservableList<CustomCellPoint>();

		int n = 100; // number of series elements
		String[] firstNames =
		{ "Paul", "Ben", "Ted", "Ed", "Dan", "Jack" };
		String[] lastNames =
		{ "Richards", "Neiman", "Evers", "Lehman", "Krause", "Stevens" };

		int m = 6;
		Random random = new Random();

		for (int i = 0; i < n; i++)
		{

			list.add(new CustomCellPoint(firstNames[random.nextInt(m)], lastNames[m - random.nextInt(m) - 1], random.nextInt(100)));
		}
		return list;
	}
}
