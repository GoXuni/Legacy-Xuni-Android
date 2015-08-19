package com.grapecity.xuni.samples.flexgrid;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.grapecity.xuni.core.DataType;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridCellFactory;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridPanel;

public class DefaultCellFactory extends GridCellFactory
{
	DatePickerDialog mDatePicker;
	TimePickerDialog mTimePicker;
	private ChartPoint cp;

	public DefaultCellFactory(FlexGrid flexGrid)
	{
		super(flexGrid);
	}

	// Override createCellEditor to display date and time pickers when editing date and time columns
	@Override
	public View createCellEditor(GridPanel gridPanel, GridCellRange cellRange, Rect bounds)
	{
		GridColumn gridColumn = flexGrid.getColumns().get(cellRange.col);

		// check for Date type columns
		if (gridColumn.getDataType() == DataType.DATE)
		{
			cp = (ChartPoint) flexGrid.getCollectionView().getItems().get(cellRange.row);
			Date date;
			Calendar cal = Calendar.getInstance();

			// check for Date
			if (gridColumn.getName().equals("hiredDate"))
			{
				date = cp.getHiredDate();
				cal.setTime(date);
				mDatePicker = new DatePickerDialog(flexGrid.getContext(), onDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
				mDatePicker.show();
				mDatePicker.setOnDismissListener(onDismissListener);
				// mDatePicker.setVisibility(View.INVISIBLE);
			}
			// check for Time
			else if (gridColumn.getName().equals("hiredTime"))
			{
				date = cp.getHiredTime();
				cal.setTime(date);
				mTimePicker = new TimePickerDialog(flexGrid.getContext(), onTimeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
				mTimePicker.show();
				mTimePicker.setOnDismissListener(onDismissListener);
				// mTimePicker.setVisibility(View.INVISIBLE);
			}
			return null;
		}

		// return super method for non-date columns
		return super.createCellEditor(gridPanel, cellRange, bounds);
	}

	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener()
	{
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
		{
			cp.setHiredDate(new Date(year - 1900, monthOfYear, dayOfMonth));
			flexGrid.finishEditing(false);
		}
	};

	TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
	{
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute)
		{
			cp.setHiredTime(new Date(0, 0, 0, hourOfDay, minute));
			flexGrid.finishEditing(false);
		}
	};

	DatePickerDialog.OnDismissListener onDismissListener = new DatePickerDialog.OnDismissListener()
	{
		@Override
		public void onDismiss(DialogInterface dialog)
		{
			flexGrid.finishEditing(false);
		}
	};

}
