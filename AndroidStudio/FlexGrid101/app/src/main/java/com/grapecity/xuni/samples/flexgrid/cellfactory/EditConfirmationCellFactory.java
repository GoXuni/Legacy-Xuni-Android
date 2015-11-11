package com.grapecity.xuni.samples.flexgrid.cellfactory;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridCellFactory;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.flexgrid.GridRow;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

/**
 * Custom GridCellFactory to show a dialog forcing the user to confirm changes after editing a cell.
 * 
 * @author GrapeCity
 * 
 */
public class EditConfirmationCellFactory extends GridCellFactory
{
	private int rowIndex;
	private int colIndex;

	/**
	 * Maintain a reference to the original value so we can restore if when the user presses the
	 * cancel button on the dialog.
	 */
	private String originalValue;

	public EditConfirmationCellFactory(FlexGrid flexGrid, Context context)
	{
		super(flexGrid);
	}

	@Override
	public View createCellEditor(final GridPanel gridPanel, GridCellRange cellRange, final Rect bounds)
	{
		rowIndex = cellRange.row;
		colIndex = cellRange.col;

		GridColumn column = super.flexGrid.getColumns().get(colIndex);

		if (!"active".equals(column.getBinding()))
		{
			GridRow row = super.flexGrid.getRows().get(rowIndex);

			originalValue = column.getValue(row.getDataItem()).toString();

			EditText editText = (EditText) super.createCellEditor(gridPanel, cellRange, bounds);

			editText.setOnFocusChangeListener(new OnFocusChangeListener()
			{
				@Override
				public void onFocusChange(View v, boolean hasFocus)
				{
					if (!hasFocus)
					{
						makeDialog(((EditText) v).getText().toString());
					}
				}
			});

			return editText;
		}

		return super.createCellEditor(gridPanel, cellRange, bounds);
	}

	private void makeDialog(final String text)
	{
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(super.flexGrid.getContext());

		// set the dialog builder
		dialogBuilder.setTitle(R.string.confirmEditTitle);
		dialogBuilder.setMessage(R.string.confirmEditDesc);
		dialogBuilder.setCancelable(false);

		dialogBuilder.setPositiveButton(R.string.apply, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				setFieldValue(text);
				flexGrid.invalidate();
			}
		});

		dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				setFieldValue(originalValue);
				flexGrid.invalidate();
			}
		});

		AlertDialog confirmDialog = dialogBuilder.create();

		confirmDialog.show();
	}

	private void setFieldValue(String text)
	{
		GridColumn column = super.flexGrid.getColumns().get(colIndex);
		GridRow row = super.flexGrid.getRows().get(rowIndex);

		String propertyName = column.getBinding();

		Customer customer = (Customer) row.getDataItem();

		try
		{
			if ("active".equals(propertyName))
			{
				customer.setActive(Boolean.parseBoolean(text));
			}
			else if ("address".equals(propertyName))
			{
				customer.setAddress(text);
			}
			else if ("city".equals(propertyName))
			{
				customer.setCity(text);
			}
			else if ("countryId".equals(propertyName))
			{
				customer.setCountryId(Integer.parseInt(text));
			}
			else if ("email".equals(propertyName))
			{
				customer.setEmail(text);
			}
			else if ("firstName".equals(propertyName))
			{
				customer.setFirstName(text);
			}
			else if ("lastName".equals(propertyName))
			{
				customer.setLastName(text);
			}
			else if ("lastOrderDate".equals(propertyName))
			{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				customer.getLastOrderDate().setTime(simpleDateFormat.parse(text));
			}
			else if ("orderCount".equals(propertyName))
			{
				customer.setOrderCount(Integer.parseInt(text));
			}
			else if ("orderTotal".equals(propertyName))
			{
				customer.setOrderTotal(Double.parseDouble(text));
			}
			else if ("postalCode".equals(propertyName))
			{
				customer.setPostalCode(text);
			}
		}
		catch (Exception e)
		{
			Log.d(this.getClass().getName(), "setFieldValue: " + text);
		}
	}
}
