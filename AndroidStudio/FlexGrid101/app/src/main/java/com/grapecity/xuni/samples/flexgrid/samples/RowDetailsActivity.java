package com.grapecity.xuni.samples.flexgrid.samples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;

import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.flexgrid.DetailCellCreatedEventArgs;
import com.grapecity.xuni.flexgrid.DetailVisibilityMode;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.FlexGridDetailProvider;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

public class RowDetailsActivity extends Activity
{
	private FlexGrid mGrid;
	private Spinner mDetailVisibilityModeSpinner;
	private FlexGridDetailProvider mDetailsProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_row_details);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);

		mGrid.setAutoGenerateColumns(false);

		mGrid.setItemsSource(Customer.getList(100));
		
		// initializing widgets
		mDetailVisibilityModeSpinner = (Spinner) findViewById(R.id.detailVisibilityModeSpinner);

		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.detailVisibilityModeSpinnerValues, android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		mDetailVisibilityModeSpinner.setAdapter(adapter1);
		mDetailVisibilityModeSpinner.setOnItemSelectedListener(mDetailVisibilityModeItemSelectedListener);
		
		// initialize new columns
		GridColumn idColumn = new GridColumn(mGrid, "Id", "id");
		
		GridColumn firstNameColumn = new GridColumn(mGrid, "First Name", "firstName");
		firstNameColumn.setWidth("*");
		
		GridColumn lastNameColumn = new GridColumn(mGrid, "Last Name", "lastName");
		lastNameColumn.setWidth("*");

		// add new columns
		mGrid.getColumns().add(idColumn);
		mGrid.getColumns().add(firstNameColumn);
		mGrid.getColumns().add(lastNameColumn);
		
		mDetailsProvider = new FlexGridDetailProvider(mGrid);

		final Context context = mGrid.getContext();
		
		mDetailsProvider.getDetailCellCreated().addHandler(new IEventHandler()
		{
			@SuppressWarnings("static-access")
			@Override
			public void call(Object arg0, Object arg1)
			{
				DetailCellCreatedEventArgs args = (DetailCellCreatedEventArgs) arg1;

				View convertView = args.getContent();

				if (convertView == null)
				{
					convertView = new FrameLayout(getBaseContext());

					convertView.setPadding(10, 10, 10, 10);

					convertView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

					LinearLayout lin = new LinearLayout(getBaseContext());

					lin.setOrientation(LinearLayout.VERTICAL);

					TextView country = new TextView(context);
					TextView city = new TextView(context);
					TextView address = new TextView(context);
					TextView postalcode = new TextView(context);

					Customer customer = (Customer) args.row.getDataItem();
					
					country.setText("Country: " + customer.getCounties().get(customer.getCountryId()).getCountryName());
					city.setText("City: " + customer.getCity());
					address.setText("Address: " + customer.getAddress());
					postalcode.setText("PostalCode: " + customer.getPostalCode());

					lin.addView(country);
					lin.addView(city);
					lin.addView(address);
					lin.addView(postalcode);

					((FrameLayout) convertView).addView(lin);
				}

				args.setContent(convertView);
			}
		}, mGrid);
	}
	
	private OnItemSelectedListener mDetailVisibilityModeItemSelectedListener = new OnItemSelectedListener()
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
		{
			switch (position)
			{
				case 0:
					mDetailsProvider.setDetailVisibilityMode(DetailVisibilityMode.EXPAND_SINGLE);
					break;
				case 1:
					mDetailsProvider.setDetailVisibilityMode(DetailVisibilityMode.EXPAND_MULTI);
					break;
				case 2:
					mDetailsProvider.setDetailVisibilityMode(DetailVisibilityMode.SELECTION);
					break;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent)
		{

		}
	};
}
