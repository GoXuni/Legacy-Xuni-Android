package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridCellRangeEventArgs;
import com.grapecity.xuni.flexgrid.GridSelectionMode;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class SelectionModesActivity extends Activity
{
	private FlexGrid mGrid;
	private Spinner mSelectionModeSpinner;
	private TextView mCellRangeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_modes);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setItemsSource(Customer.getList(100));

		// initializing widgets
		mSelectionModeSpinner = (Spinner) findViewById(R.id.selectionModeSpinner);

		mCellRangeTextView = (TextView) findViewById(R.id.cellRangeTextView);

		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.selectionModeSpinnerValues, android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		mSelectionModeSpinner.setAdapter(adapter1);
		mSelectionModeSpinner.setOnItemSelectedListener(mSelectionModeItemSelectedListener);

		mGrid.getSelectionChanged().addHandler(mGridSelectionChangedListener, mGrid);
	}

	private IEventHandler mGridSelectionChangedListener = new IEventHandler()
	{
		@Override
		public void call(Object arg0, Object arg1)
		{
			GridCellRangeEventArgs args = (GridCellRangeEventArgs) arg1;

			if (args.range.isValid())
			{
				if (args.range.row != args.range.row2 || args.range.col != args.range.col2)
				{
					mCellRangeTextView.setText(String.format("(%d:%d)-(%d:%d)", args.range.row, args.range.col, args.range.row2, args.range.col2));
				}
				else
				{
					mCellRangeTextView.setText(String.format("(%d:%d)", args.range.row, args.range.col));
				}
			}
			else
			{
				mCellRangeTextView.setText("");
			}
		}
	};

	private OnItemSelectedListener mSelectionModeItemSelectedListener = new OnItemSelectedListener()
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
		{
			// setting selection mode
			switch (position)
			{
				case 0:
					mGrid.setSelectionMode(GridSelectionMode.ROW_RANGE);
					break;
				case 1:
					mGrid.setSelectionMode(GridSelectionMode.ROW);
					break;
				case 2:
					mGrid.setSelectionMode(GridSelectionMode.CELL_RANGE);
					break;
				case 3:
					mGrid.setSelectionMode(GridSelectionMode.CELL);
					break;
				case 4:
					mGrid.setSelectionMode(GridSelectionMode.NONE);
					break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{

		}
	};
}
