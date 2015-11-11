package com.grapecity.xuni.samples.flexgrid.samples;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.grapecity.xuni.core.IPredicate;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridDataMap;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.FilterCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class FullFilterActivity extends Activity
{
	private FlexGrid mGrid;
	private EditText mFilterText;
	private FilterCellFactory mFilterCellFactory;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_full_filter);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setAutoGenerateColumns(false);
		
		mGrid.setItemsSource(Customer.getList(100));
		
		GridColumn idColumn = new GridColumn(mGrid, "Id", "id");
		idColumn.setReadOnly(true);

		GridColumn firstNameColumn = new GridColumn(mGrid, "First Name", "firstName");
		GridColumn lastNameColumn = new GridColumn(mGrid, "Last Name", "lastName");
		GridColumn addressColumn = new GridColumn(mGrid, "Address", "address");
		GridColumn cityColumn = new GridColumn(mGrid, "City", "city");

		GridColumn countryColumn = new GridColumn(mGrid, "Country", "countryId");
		countryColumn.setName("country");
		countryColumn.setDataMap(new GridDataMap(Customer.getCounties(), "countryId", "countryName"));
		countryColumn.setShowDropDown(true);
		
		GridColumn emailColumn = new GridColumn(mGrid, "Email", "email");
		
		mGrid.getColumns().add(idColumn);
		mGrid.getColumns().add(firstNameColumn);
		mGrid.getColumns().add(lastNameColumn);
		mGrid.getColumns().add(addressColumn);
		mGrid.getColumns().add(cityColumn);
		mGrid.getColumns().add(countryColumn);
		mGrid.getColumns().add(emailColumn);
		
		// initializing widgets
		mFilterText = (EditText) findViewById(R.id.filterText);

		// setting custom cell factory
		mFilterCellFactory = new FilterCellFactory(mGrid, this);
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
				mGrid.getCollectionView().setFilter(new IPredicate<Customer>()
				{
					@Override
					public boolean execute(Customer arg0)
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
