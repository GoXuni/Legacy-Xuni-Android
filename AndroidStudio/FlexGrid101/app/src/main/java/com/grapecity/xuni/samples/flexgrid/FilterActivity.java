package com.grapecity.xuni.samples.flexgrid;

import java.util.Locale;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.grapecity.xuni.core.*;

public class FilterActivity extends BaseChartPointActivity implements FilterFragment.OnDataPass
{
	private android.app.FragmentManager mFragmentManager = getFragmentManager();

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_filter, menu);
		return super.onCreateOptionsMenu(menu);
	}

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
				// removeFilter();
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
	}

	@SuppressWarnings("unchecked")
	@Override
	public void saveData(final FilterSelection[] filter, final String[] filterValue)
	{

		mGrid.getCollectionView().setFilter(new IPredicate<ChartPoint>()
		{

			@Override
			public boolean execute(ChartPoint arg0)
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

			private String getFilterVariable(ChartPoint arg0, int i)
			{
				// get the particular variable from ChartPoint object
				switch (i)
				{
					case 0:
						return arg0.getName().toLowerCase(Locale.getDefault());
					case 1:
						return arg0.getCountry().toLowerCase(Locale.getDefault());
					case 2:
						return arg0.getFirst().toLowerCase(Locale.getDefault());
					case 3:
						return arg0.getLast().toLowerCase(Locale.getDefault());
					case 4:
						return arg0.getFather().toLowerCase(Locale.getDefault());
					case 5:
						return arg0.getBrother().toLowerCase(Locale.getDefault());
					case 6:
						return arg0.getCousin().toLowerCase(Locale.getDefault());
				}
				return null;
			}
		});

	}
}
