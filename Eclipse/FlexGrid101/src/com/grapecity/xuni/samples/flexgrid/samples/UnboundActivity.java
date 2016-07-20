package com.grapecity.xuni.samples.flexgrid.samples;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridAllowMerging;
import com.grapecity.xuni.flexgrid.GridCellType;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridRow;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.DateEditorCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Productivity;

public class UnboundActivity extends Activity
{
	private FlexGrid mGrid;

	@SuppressLint("UseSparseArrays")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_column_definitions);

		// initializing grid
		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setItemsSource(Productivity.getList(9));

		mGrid.setCellFactory(new DateEditorCellFactory(mGrid));

		// initialize new columns
		GridColumn q1Column = new GridColumn(mGrid, "Q1", "q1");
		GridColumn q2Column = new GridColumn(mGrid, "Q2", "q2");
		GridColumn q3Column = new GridColumn(mGrid, "Q3", "q3");
		GridColumn q4Column = new GridColumn(mGrid, "Q4", "q4");
		GridColumn q5Column = new GridColumn(mGrid, "Q1", "q5");
		GridColumn q6Column = new GridColumn(mGrid, "Q2", "q6");
		GridColumn q7Column = new GridColumn(mGrid, "Q3", "q7");
		GridColumn q8Column = new GridColumn(mGrid, "Q4", "q8");

		// add new columns
		mGrid.getColumns().add(q1Column);
		mGrid.getColumns().add(q2Column);
		mGrid.getColumns().add(q3Column);
		mGrid.getColumns().add(q4Column);
		mGrid.getColumns().add(q5Column);
		mGrid.getColumns().add(q6Column);
		mGrid.getColumns().add(q7Column);
		mGrid.getColumns().add(q8Column);
		mGrid.setAllowMerging(GridAllowMerging.ALL_HEADERS);

		// add additional column header
		GridRow row = new GridRow(mGrid);
		row.setAllowMerging(true);
		Map<Integer, Object> columnHeaderValues = new HashMap<Integer, Object>();
		columnHeaderValues.put(0, "2015");
		columnHeaderValues.put(1, "2015");
		columnHeaderValues.put(2, "2015");
		columnHeaderValues.put(3, "2015");
		columnHeaderValues.put(4, "2016");
		columnHeaderValues.put(5, "2016");
		columnHeaderValues.put(6, "2016");
		columnHeaderValues.put(7, "2016");
		row.setColumnHeaderValues(columnHeaderValues);
		mGrid.columnHeaders.getRows().add(0, row);

		// add additional Row header.
		GridColumn column = new GridColumn(mGrid, "", "");
		column.setAllowMerging(true);
		column.topLeftHeader = true;
		column.setAllowResizing(false);
		column.setWidth(1);
		mGrid.rowHeaders.getColumns().add(0, column);
		int size = mGrid.getRows().size();
		for (int i = 0; i < size; i++)
		{
			mGrid.setCellValue(GridCellType.ROW_HEADER, i, 1, "hdr " + i + ",1");
		}

		mGrid.setCellValue(GridCellType.ROW_HEADER, 0, 0, "hdr 1,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 1, 0, "hdr 2,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 2, 0, "hdr 2,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 3, 0, "hdr 3,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 4, 0, "hdr 3,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 5, 0, "hdr 4,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 6, 0, "hdr 5,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 7, 0, "hdr 6,0");
		mGrid.setCellValue(GridCellType.ROW_HEADER, 8, 0, "hdr 7,0");

	}

	@Override
	protected void onStart()
	{
		super.onStart();

		// auto resize all the columns so that the text from each row is analyzed and sets the
		// column width to the highest text width.
		mGrid.autoSizeColumns(0, mGrid.getColumns().size() - 1);
		mGrid.autoSizeColumns(0, mGrid.rowHeaders.getColumns().size() - 1, true);
	}
}
