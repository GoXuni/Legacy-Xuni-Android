package com.grapecity.xuni.samples.flexgrid.cellfactory;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.view.View;
import android.widget.DatePicker;

import com.grapecity.xuni.core.IEditableCollectionView;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridCellFactory;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

/**
 * Custom GridCellFactory to change the default editor of the lastOrderDate column to utilize
 * the DatePickerDialog.
 * 
 * @author GrapeCity
 * 
 */
public class DateEditorCellFactory extends GridCellFactory
{
	DatePickerDialog mDatePicker;
	Customer currentEditCustomer;

	public DateEditorCellFactory(FlexGrid flexGrid)
	{
		super(flexGrid);
	}

	@Override
	public View createCellEditor(GridPanel gridPanel, GridCellRange cellRange, Rect bounds)
	{
		GridColumn column = gridPanel.getColumns().get(cellRange.col);

		if ("lastOrderDate".equals(column.getName()))
		{
			Customer customer = (Customer) gridPanel.getRows().get(cellRange.row).getDataItem();
			
			currentEditCustomer = customer;

			Calendar lastOrderDate = customer.getLastOrderDate();

			mDatePicker = new DatePickerDialog(flexGrid.getContext(), onDateSetListener, lastOrderDate.get(Calendar.YEAR),
					lastOrderDate.get(Calendar.MONTH), lastOrderDate.get(Calendar.DAY_OF_MONTH));
			mDatePicker.show();
			mDatePicker.setOnDismissListener(onDismissListener);

			return null;
		}

		return super.createCellEditor(gridPanel, cellRange, bounds);
	}

	DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener()
	{
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
		{
			@SuppressWarnings("unchecked")
			IEditableCollectionView<Customer> cv = (IEditableCollectionView<Customer>) flexGrid.getCollectionView();

			Customer customer = cv.currentEditItem();

			if(customer == null) customer = currentEditCustomer;
			
			Calendar cal = customer.getLastOrderDate();

			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, monthOfYear);
			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

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