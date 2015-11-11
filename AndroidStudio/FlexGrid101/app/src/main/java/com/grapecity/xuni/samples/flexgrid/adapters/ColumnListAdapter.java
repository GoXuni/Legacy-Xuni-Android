package com.grapecity.xuni.samples.flexgrid.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.ColumnModel;

public class ColumnListAdapter extends BaseAdapter
{
	private List<ColumnModel> mList;
	private LayoutInflater mInflater;

	private OnClickListener mButtonUpOnClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			updateColumnPosition(v, false);
		}
	};

	private OnClickListener mButtonDownOnClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			updateColumnPosition(v, true);
		}
	};
	
	private OnCheckedChangeListener mVisibleCheckboxChanged =  new OnCheckedChangeListener()
	{
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		{
			ColumnModel cm = (ColumnModel) ((ViewGroup) buttonView.getParent()).getTag();
			
			cm.setVisible(isChecked);
		}
	};

	public ColumnListAdapter(Context context, String[] columnNames, boolean[] columnVisible)
	{
		mList = new ArrayList<ColumnModel>();
		this.mInflater = LayoutInflater.from(context);

		// initializing SampleModel objects for each sample
		for (int i = 0; i < columnNames.length; i++)
		{
			mList.add(new ColumnModel(columnNames[i], columnVisible[i]));
		}
	}

	@Override
	public int getCount()
	{
		return mList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	public List<ColumnModel> getmList()
	{
		return mList;
	}

	public void setmList(List<ColumnModel> mList)
	{
		this.mList = mList;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = this.mInflater.inflate(R.layout.view_edit_column_list, parent, false);

			// attach listeners if views inflated for the 1st time.
			((Button) convertView.findViewById(R.id.buttonMoveUp)).setOnClickListener(mButtonUpOnClickListener);
			((Button) convertView.findViewById(R.id.buttonMoveDown)).setOnClickListener(mButtonDownOnClickListener);
			((CheckBox) convertView.findViewById(R.id.checkboxVisible)).setOnCheckedChangeListener(mVisibleCheckboxChanged);
		}

		ColumnModel sample = mList.get(position);

		// update the tag so listeners are tied to correct object
		convertView.setTag(sample);

		((TextView) convertView.findViewById(R.id.columnName)).setText(sample.getColumnName());
		((CheckBox) convertView.findViewById(R.id.checkboxVisible)).setChecked(sample.isVisible());
		
		return convertView;
	}

	private void updateColumnPosition(View v, boolean increment)
	{
		ColumnModel cm = (ColumnModel) ((ViewGroup) v.getParent()).getTag();

		int position = mList.indexOf(cm);

		if ((increment && position < mList.size() - 1) || (!increment && position > 0))
		{
			mList.remove(position);
			mList.add(position + ((increment) ? 1 : -1), cm);
		}

		notifyDataSetChanged();

		InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
}

abstract class MyTextWatcher implements TextWatcher
{
	private EditText editText;

	public MyTextWatcher(EditText editText)
	{
		this.editText = editText;
	}

	public EditText getEditTextView()
	{
		return editText;
	}

	public ViewParent getParentView()
	{
		return editText.getParent();
	}

	public View getView()
	{
		return (View) editText;
	}

	public String getText()
	{
		return editText.getText().toString();
	}
}
