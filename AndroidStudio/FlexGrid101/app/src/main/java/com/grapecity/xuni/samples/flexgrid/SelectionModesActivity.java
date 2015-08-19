package com.grapecity.xuni.samples.flexgrid;

import com.grapecity.xuni.flexgrid.GridSelectionMode;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SelectionModesActivity extends BaseChartPointActivity
{
	private Spinner mSelectionModeSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_modes);

		// initializing widgets
		mSelectionModeSpinner = (Spinner) findViewById(R.id.selectionModeSpinner);

		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.selectionModeSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mSelectionModeSpinner.setAdapter(adapter1);
		mSelectionModeSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
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
		});
	}
}
