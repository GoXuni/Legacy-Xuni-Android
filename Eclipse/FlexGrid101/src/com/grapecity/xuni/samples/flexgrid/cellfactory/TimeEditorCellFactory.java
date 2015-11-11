package com.grapecity.xuni.samples.flexgrid.cellfactory;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.view.View;
import android.widget.TimePicker;

import com.grapecity.xuni.core.IEditableCollectionView;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridCellFactory;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

/**
 * Custom GridCellFactory to change the default editor of the lastOrderDate column to utilize the
 * TimePickerDialog.
 * 
 * @author GrapeCity
 * 
 */
public class TimeEditorCellFactory extends GridCellFactory
{
	TimePickerDialog mTimePicker;

	public TimeEditorCellFactory(FlexGrid flexGrid)
	{
		super(flexGrid);
	}

	@Override
	public View createCellEditor(GridPanel gridPanel, GridCellRange cellRange, Rect bounds)
	{
		GridColumn column = gridPanel.getColumns().get(cellRange.col);

		if ("Last Order Time".equals(column.getName()))
		{
			Customer customer = (Customer) gridPanel.getRows().get(cellRange.row).getDataItem();

			Calendar lastOrderDate = customer.getLastOrderDate();

			mTimePicker = new TimePickerDialog(flexGrid.getContext(), onTimeSetListener, lastOrderDate.get(Calendar.HOUR_OF_DAY),
					lastOrderDate.get(Calendar.MINUTE), false);
			mTimePicker.show();
			mTimePicker.setOnDismissListener(onDismissListener);

			return null;
		}

		return super.createCellEditor(gridPanel, cellRange, bounds);
	}

	TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
	{
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute)
		{
			@SuppressWarnings("unchecked")
			IEditableCollectionView<Customer> cv = (IEditableCollectionView<Customer>) flexGrid.getCollectionView();

			Customer customer = cv.currentEditItem();

			Calendar cal = customer.getLastOrderDate();

			cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
			cal.set(Calendar.MINUTE, minute);

			customer.setLastOrderDate(cal);

			flexGrid.finishEditing(true);
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