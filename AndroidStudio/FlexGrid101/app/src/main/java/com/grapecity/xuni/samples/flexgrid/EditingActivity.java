package com.grapecity.xuni.samples.flexgrid;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexgrid.*;

public class EditingActivity extends FragmentActivity implements EditRowFragment.OnDataPass
{

	private FlexGrid mGrid;
	private android.app.FragmentManager mFragmentManager = getFragmentManager();
	private ChartPoint mEditingObject;
	protected GridCellRange mSelectedRow;

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_edit, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if (mGrid.getSelection().isValid())
		{
			switch (item.getItemId())
			{
				case R.id.actionEdit:
					// getting selected row data
					mSelectedRow = mGrid.getSelection();
					mEditingObject = (ChartPoint) mGrid.getCollectionView().getItems().get(mSelectedRow.row);

					// passing the row data to editing fragment as string array
					// through bundle
					Bundle bundle = new Bundle();
					bundle.putStringArray("data", mEditingObject.toStringArray());

					// initializing and displaying the editing fragment
					EditRowFragment dialog = new EditRowFragment();
					dialog.setArguments(bundle);
					dialog.show(mFragmentManager, "Edit Row Dialog");
					return true;
				default:
					return super.onOptionsItemSelected(item);
			}
		}
		else
			Toast.makeText(this, "Select a row to edit", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editing);

		// initializing widgets
		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		// setting data source and default values
		mGrid.setItemsSource(ChartPoint.getList());
		mGrid.getColumns().getColumn("weight").setFormat("#.##");
		// property set in XML layout
		// mGrid.setSelectionMode(GridSelectionMode.ROW);

		// setting the itemFormatter for column 'name' by combining first and
		// last name
		mGrid.getColumns().getColumn("name").setItemFormatter(new ItemFormatter()
		{

			private ChartPoint editingName;

			@Override
			public String formatItem(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
			{

				// get data item at rowindex, cast to chartpoint and
				// concatenate names
				editingName = (ChartPoint) mGrid.getCollectionView().getItems().get(cellRange.row);

				return editingName.getFirst() + " " + editingName.getLast();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveData(ChartPoint data)
	{
		mEditingObject = data;

		// setting the row data with the new edited data
		((IEditableCollectionView<ChartPoint>) mGrid.getCollectionView()).set(mSelectedRow.row, mEditingObject);

	}
}
