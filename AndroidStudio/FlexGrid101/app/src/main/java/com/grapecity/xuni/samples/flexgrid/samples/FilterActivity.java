package com.grapecity.xuni.samples.flexgrid.samples;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.grapecity.xuni.core.IPredicate;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.cellfactory.DateEditorCellFactory;
import com.grapecity.xuni.samples.flexgrid.data.Customer;
import com.grapecity.xuni.samples.flexgrid.fragments.FilterFragment;
import com.grapecity.xuni.samples.flexgrid.util.FilterSelection;

public class FilterActivity extends Activity implements FilterFragment.OnDataPass
{
	private FlexGrid mGrid;

	private android.app.FragmentManager mFragmentManager = getFragmentManager();

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_filter, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.actionFilter:
				// filter
				FilterFragment filter = new FilterFragment();
				filter.show(mFragmentManager, "Select Filter");
				return true;
			case R.id.actionFilterRemove:
				mGrid.getCollectionView().setFilter(null);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_filter);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setAutoGenerateColumns(false);
		
		mGrid.setItemsSource(Customer.getList(100));

		GridColumn idColumn = new GridColumn(mGrid, "Id", "id");
		GridColumn firstNameColumn = new GridColumn(mGrid, "First Name", "firstName");
		GridColumn lastNameColumn = new GridColumn(mGrid, "Last Name", "lastName");
		GridColumn addressColumn = new GridColumn(mGrid, "Address", "address");
		GridColumn cityColumn = new GridColumn(mGrid, "City", "city");
		GridColumn orderCountColumn = new GridColumn(mGrid, "Order Count", "orderCount");

		GridColumn orderTotalColumn = new GridColumn(mGrid, "Order Total", "orderTotal");
		orderTotalColumn.setFormat("$#.##");

		GridColumn lastOrderDateColumn = new GridColumn(mGrid, "Last Order Date", "lastOrderDate");
		lastOrderDateColumn.setFormat("MM/dd/yyyy");

		mGrid.getColumns().add(idColumn);
		mGrid.getColumns().add(firstNameColumn);
		mGrid.getColumns().add(lastNameColumn);
		mGrid.getColumns().add(addressColumn);
		mGrid.getColumns().add(cityColumn);
		mGrid.getColumns().add(orderCountColumn);
		mGrid.getColumns().add(orderTotalColumn);
		mGrid.getColumns().add(lastOrderDateColumn);

		mGrid.setCellFactory(new DateEditorCellFactory(mGrid));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveData(final FilterSelection[] filter, final String[] filterValue)
	{
		mGrid.getCollectionView().setFilter(new IPredicate<Customer>()
		{
			@Override
			public boolean execute(Customer arg0)
			{
				boolean filterCheck = true;
				// pass through all the received data filters and checks if it
				// applies to the current row
				for (int i = 0; i < filter.length && filterCheck == true; i++)
				{
					switch (filter[i])
					{
						case NONE:
							break;

						case CONTAINS:
							if (!getFilterVariable(arg0, i).contains(filterValue[i].toLowerCase(Locale.getDefault())))
								filterCheck = false;
							break;

						case EQUALS:
							if (!getFilterVariable(arg0, i).equals(filterValue[i].toLowerCase(Locale.getDefault())) && !filterValue.equals(""))
								filterCheck = false;
							break;

						case STARTSWITH:
							if (!getFilterVariable(arg0, i).startsWith(filterValue[i].toLowerCase(Locale.getDefault())))
								filterCheck = false;
							break;

						case ENDSWITH:
							if (!getFilterVariable(arg0, i).endsWith(filterValue[i].toLowerCase(Locale.getDefault())))
								filterCheck = false;
							break;

					}
				}
				// return true if all the filters were applicable, else false
				return filterCheck;
			}

			private String getFilterVariable(Customer arg0, int i)
			{
				//mapping an interger to a specific field in the Customer object.
				 switch (i)
				 {
				 case 0:
				 return arg0.getFirstName().toLowerCase(Locale.getDefault());
				 case 1:
				 return arg0.getLastName().toLowerCase(Locale.getDefault());
				 case 2:
				 return arg0.getAddress().toLowerCase(Locale.getDefault());
				 case 3:
				 return arg0.getCity().toLowerCase(Locale.getDefault());
				 case 4:
				 return arg0.getPostalCode().toLowerCase(Locale.getDefault());
				 case 5:
				 return arg0.getEmail().toLowerCase(Locale.getDefault());
				 }
				return null;
			}
		});

	}
}
