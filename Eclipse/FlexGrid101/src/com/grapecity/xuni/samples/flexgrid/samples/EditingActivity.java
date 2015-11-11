package com.grapecity.xuni.samples.flexgrid.samples;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.Customer;
import com.grapecity.xuni.samples.flexgrid.fragments.EditRowFragment;

public class EditingActivity extends FragmentActivity
{
	public FlexGrid mGrid;

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_edit, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editing);

		// initializing widgets
		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		// property set in XML layout
		// mGrid.setSelectionMode(GridSelectionMode.ROW);

		mGrid.setReadOnly(true);

		mGrid.setAutoGenerateColumns(false);

		mGrid.setItemsSource(Customer.getList(100));

		mGrid.getColumns().add(new GridColumn(mGrid, "First Name", "firstName"));
		mGrid.getColumns().add(new GridColumn(mGrid, "Last Name", "lastName"));
		mGrid.getColumns().add(new GridColumn(mGrid, "Last Order Date", "lastOrderDate"));
		mGrid.getColumns().add(new GridColumn(mGrid, "Order Total", "orderTotal"));
		
		mGrid.getCellDoubleTapped().addHandler(mCellDoubleTapped, mGrid);
	}
	
	private IEventHandler mCellDoubleTapped = new IEventHandler()
	{
		@Override
		public void call(Object arg0, Object arg1)
		{
			if (mGrid.getSelection().isValid())
			{
				showEditingFragment();
			}
		}
	};
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (mGrid.getSelection().isValid())
		{
			switch (item.getItemId())
			{
				case R.id.actionEdit:

					showEditingFragment();

					return true;
				default:
					return super.onOptionsItemSelected(item);
			}
		}
		else
		{
			Toast.makeText(this, R.string.editRowToast, Toast.LENGTH_SHORT).show();
		}

		return false;
	}
	
	private void showEditingFragment()
	{
		EditRowFragment dialog = new EditRowFragment();

		dialog.show(getFragmentManager(), "EDIT_ROW_FRAGMENT");
	}
}
