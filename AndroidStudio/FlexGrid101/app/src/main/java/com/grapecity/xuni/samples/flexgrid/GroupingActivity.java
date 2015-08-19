package com.grapecity.xuni.samples.flexgrid;

import android.os.Bundle;

import com.grapecity.xuni.core.*;

public class GroupingActivity extends BaseChartPointActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grouping);

		// apply grouping on column country
		mGrid.getCollectionView().getGroupDescriptions().add(new PropertyGroupDescription("country"));
		// property set in XML layout
		// mGrid.setShowGroups(true);
		mGrid.collapseGroupsToLevel(0);

		// displaying aggregate for weight
		mGrid.getColumns().getColumn("weight").setAggregate(Aggregate.SUM);
	}
}
