package com.grapecity.xuni.samples.flexgrid;

import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.grapecity.xuni.flexgrid.*;

public class EditCellFactory extends GridCellFactory
{
	private CustomEditText mEditText;
	private Context mContext;
	private GridColumn gridColumn;
	private GridRow gridRow;
	private OnFocusChangeListener mFocusListener = new OnFocusChangeListener()
	{

		@Override
		public void onFocusChange(View v, boolean hasFocus)
		{
			if (!hasFocus)
			{
				makeDialog(((EditText) v).getText().toString());
			}
		}
	};
	private OnEditTextImeBackListener mBackListener = new OnEditTextImeBackListener()
	{
		@Override
		public void onImeBack(CustomEditText ctrl, String text)
		{
			InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(ctrl.getWindowToken(), 0);

			//makeDialog(text);
		}
	};

	public EditCellFactory(FlexGrid flexGrid, Context context)
	{
		super(flexGrid);

		mContext = context;

		// initializing new EditText to display
		mEditText = new CustomEditText(context);
		flexGrid.addView(mEditText);
		mEditText.setVisibility(View.INVISIBLE);
		mEditText.setTextColor(flexGrid.getFontColor());

	}

	@Override
	public View createCellEditor(final GridPanel gridPanel, GridCellRange cellRange, final Rect bounds)
	{
		gridRow = flexGrid.getRows().get(cellRange.row);
		gridColumn = flexGrid.getColumns().get(cellRange.col);

		mEditText.setTextColor(flexGrid.getFontColor());
		mEditText.setTextSize(flexGrid.getFontSize() * flexGrid.renderEngine.scale);

		mEditText.setPadding(Math.round(bounds.height() * .05f), Math.round(bounds.height() * .01f), 0, 0);

		setLayoutParams(mEditText, bounds);

		mEditText.setInputType(gridColumn.getInputType());

		mEditText.setText(getEditingItemStringValue(gridColumn));

		mEditText.setVisibility(View.VISIBLE);
		mEditText.requestFocus();
		mEditText.setSelection(mEditText.getText().length());
		mEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

		flexGrid.toggleKeyboard(true);

		mEditText.setOnFocusChangeListener(mFocusListener);
		mEditText.setOnEditTextImeBackListener(mBackListener);
		return mEditText;
	}

	protected void makeDialog(final String text)
	{
		final ChartPoint cp = (ChartPoint) gridRow.getDataItem();

		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);

		// set the dialog builder
		dialogBuilder.setTitle(R.string.confirmEditTitle);
		dialogBuilder.setMessage("Do you want to commit the edit?");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton(R.string.apply, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				setFieldValue(cp, gridColumn.index,text);
				flexGrid.invalidate();
			}
		});

		dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
			}
		});

		AlertDialog confirmDialog = dialogBuilder.create();
		confirmDialog.show();
	}

	private void setFieldValue(ChartPoint cp, int i, String text)
	{
		// set the required field of ChartPoint
		switch (i)
		{
			case 9:
				cp.setId(Integer.parseInt(text));
				break;
			case 0:
				cp.setName(text);
				break;
			case 10:
				cp.setCountryId(Integer.parseInt(text));
				break;
			case 2:
				cp.setCountry(text);
				break;
			case 11:
				break;
			case 6:
				cp.setFirst(text);
				break;
			case 3:
				cp.setLast(text);
				break;
			case 7:
				cp.setHiredDate(new Date(text));
				break;
			case 8:
				cp.setHiredTime(new Date(text));
				break;
			case 12:
				cp.setWeight(Float.parseFloat(text));
				break;
			case 5:
				cp.setFather(text);
				break;
			case 1:
				cp.setBrother(text);
				break;
			case 4:
				cp.setCousin(text);
				break;
		}
	}
}
