package com.grapecity.xuni.samples.flexgrid;

import java.util.Locale;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.grapecity.xuni.core.*;

public class FullFilterActivity extends BaseChartPointActivity
{
	private EditText mFilterText;
	private FilterCellFactory mFilterCellFactory;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_full_filter);

		// initializing widgets
		mFilterText = (EditText) findViewById(R.id.filterText);

		// setting custom cell factory
		mFilterCellFactory = new FilterCellFactory(mGrid, getApplicationContext());
		mGrid.setCellFactory(mFilterCellFactory);

		// listener for text change in editText
		mFilterText.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@SuppressWarnings("unchecked")
			@Override
			public void afterTextChanged(final Editable s)
			{
				// return true for all the matches
				mGrid.getCollectionView().setFilter(new IPredicate<ChartPoint>()
				{

					@Override
					public boolean execute(ChartPoint arg0)
					{
						String[] columns = arg0.toStringArray();
						mFilterCellFactory.setFilterString(s.toString());
						for (int i = 0; i < columns.length; i++)
						{
							if (columns[i].toLowerCase(Locale.getDefault()).contains(s.toString().toLowerCase(Locale.getDefault())))
								return true;
						}
						return false;
					}
				});
			}
		});

	}
}
