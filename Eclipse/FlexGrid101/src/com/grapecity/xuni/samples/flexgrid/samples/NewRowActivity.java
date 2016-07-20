package com.grapecity.xuni.samples.flexgrid.samples;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.core.CollectionView;
import com.grapecity.xuni.core.IFunction;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.Country;

public class NewRowActivity extends Activity
{
	private FlexGrid mGrid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_getting_started);
		mGrid = (FlexGrid) findViewById(R.id.flexgrid);
		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country(1, "U.S"));
		countries.add(new Country(2, "China"));
		CollectionView<Country> cv = new CollectionView<Country>(countries);
		cv.setNewItemCreator(new NewCountryGenerator());
		mGrid.setCollectionView(cv);
	}

	class NewCountryGenerator implements IFunction
	{
		@Override
		public Object execute(Object... args)
		{
			return new Country(0, "");
		}
	}
}
