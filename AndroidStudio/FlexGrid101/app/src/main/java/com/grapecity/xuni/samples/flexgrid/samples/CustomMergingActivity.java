package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridAllowMerging;
import com.grapecity.xuni.flexgrid.GridCellRangeEventArgs;
import com.grapecity.xuni.flexgrid.GridCellType;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridRow;
import com.grapecity.xuni.flexgrid.GridSelectionMode;
import com.grapecity.xuni.samples.flexgrid.R;

public class CustomMergingActivity extends Activity
{
	private FlexGrid mGrid;

	private TextView showName;
	private TextView showTimes;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_custom_merging);

		showName = (TextView) findViewById(R.id.showName);
		showTimes = (TextView) findViewById(R.id.showTimes);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setReadOnly(true);
		mGrid.setSelectionMode(GridSelectionMode.CELL);
		mGrid.setAllowMerging(GridAllowMerging.CELLS);
		mGrid.setAutoGenerateColumns(false);

		for (int i = 0; i < 7; i++)
		{
			GridRow row = new GridRow(mGrid);

			row.setAllowMerging(true);

			mGrid.getRows().add(row);
		}

		Resources res = getResources();

		dayOfWeekColumn(res, R.string.monday);
		dayOfWeekColumn(res, R.string.tuesday);
		dayOfWeekColumn(res, R.string.wednesday);
		dayOfWeekColumn(res, R.string.thursday);
		dayOfWeekColumn(res, R.string.friday);
		dayOfWeekColumn(res, R.string.saturday);
		dayOfWeekColumn(res, R.string.sunday);

		mGrid.setCellValue(GridCellType.ROW_HEADER, 0, 0, "12:00");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 1, 0, "13:00");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 2, 0, "14:00");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 3, 0, "15:00");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 4, 0, "16:00");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 5, 0, "17:00");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 6, 0, "18:00");

		mGrid.setCellValue(0, 0, "Walker");
		mGrid.setCellValue(0, 1, "Morning Show");
		mGrid.setCellValue(0, 2, "Morning Show");
		mGrid.setCellValue(0, 3, "Sports");
		mGrid.setCellValue(0, 4, "Weather");
		mGrid.setCellValue(0, 5, "N/A");
		mGrid.setCellValue(0, 6, "N/A");
		mGrid.setCellValue(1, 5, "N/A");
		mGrid.setCellValue(1, 6, "N/A");
		mGrid.setCellValue(2, 5, "N/A");
		mGrid.setCellValue(2, 6, "N/A");
		mGrid.setCellValue(3, 5, "N/A");
		mGrid.setCellValue(3, 6, "N/A");
		mGrid.setCellValue(4, 5, "N/A");
		mGrid.setCellValue(4, 6, "N/A");
		mGrid.setCellValue(1, 0, "Today Show");
		mGrid.setCellValue(1, 1, "Today Show");
		mGrid.setCellValue(2, 0, "Today Show");
		mGrid.setCellValue(2, 1, "Today Show");
		mGrid.setCellValue(1, 2, "Sesame Street");
		mGrid.setCellValue(1, 3, "Football");
		mGrid.setCellValue(2, 3, "Football");
		mGrid.setCellValue(1, 4, "Market Watch");
		mGrid.setCellValue(2, 2, "Kids Zone");
		mGrid.setCellValue(2, 4, "Soap Opera");
		mGrid.setCellValue(3, 0, "News");
		mGrid.setCellValue(3, 1, "News");
		mGrid.setCellValue(3, 2, "News");
		mGrid.setCellValue(3, 3, "News");
		mGrid.setCellValue(3, 4, "News");
		mGrid.setCellValue(4, 0, "News");
		mGrid.setCellValue(4, 1, "News");
		mGrid.setCellValue(4, 2, "News");
		mGrid.setCellValue(4, 3, "News");
		mGrid.setCellValue(4, 4, "News");
		mGrid.setCellValue(5, 0, "Weel of Fortune");
		mGrid.setCellValue(5, 1, "Weel of Fortune");
		mGrid.setCellValue(5, 2, "Weel of Fortune");
		mGrid.setCellValue(5, 3, "Jeopardy");
		mGrid.setCellValue(5, 4, "Jeopardy");
		mGrid.setCellValue(5, 5, "Movie");
		mGrid.setCellValue(6, 5, "Movie");
		mGrid.setCellValue(5, 6, "Golf");
		mGrid.setCellValue(6, 6, "Golf");
		mGrid.setCellValue(6, 0, "Night Show");
		mGrid.setCellValue(6, 1, "Night Show");
		mGrid.setCellValue(6, 2, "Sports");
		mGrid.setCellValue(6, 3, "Big Brother");
		mGrid.setCellValue(6, 4, "Big Brother");

		mGrid.getSelectionChanged().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object arg0, Object arg1)
			{
				GridCellRangeEventArgs args = (GridCellRangeEventArgs) arg1;

				String selectedText = (String) mGrid.getValue(args.range.row, args.range.col);

				showName.setText(selectedText);

				showTimes.setText("");
				for (int c = 0; c < mGrid.getColumns().size(); c++)
				{
					String dayName = (String) mGrid.getColumns().get(c).getName();

					String startTime = "";
					for (int r = 0; r < mGrid.getRows().size(); r++)
					{
						String cellValue = (String) mGrid.getValue(r, c);

						if (cellValue.equals(selectedText))
						{
							if (startTime == "")
							{
								startTime = (String) mGrid.getValue(GridCellType.ROW_HEADER, r, 0);
								showTimes.setText(showTimes.getText() + dayName + " " + startTime + "-");
							}
						}
						else if (startTime != "" && showTimes.getText().charAt(showTimes.getText().length() - 1) == '-')
						{
							String endTime = (String) mGrid.getValue(GridCellType.ROW_HEADER, r, 0);
							showTimes.setText(showTimes.getText() + endTime + "\n");
						}

						// handle last row exception
						if (r == mGrid.getRows().size() - 1 && startTime != "" && showTimes.getText().charAt(showTimes.getText().length() - 1) == '-')
						{
							showTimes.setText(showTimes.getText() + "19:00\n");
						}
					}
				}
			}
		}, mGrid);
	}

	private void dayOfWeekColumn(Resources res, int nameResourceId)
	{
		GridColumn column = new GridColumn(mGrid, res.getString(nameResourceId), "");
		column.setWidth("*");
		column.setMinWidth(120);
		column.setAllowMerging(true);

		mGrid.getColumns().add(column);
	}

	@Override
	protected void onStart()
	{
		super.onStart();

		mGrid.autoSizeColumn(0, true);
	}
}
