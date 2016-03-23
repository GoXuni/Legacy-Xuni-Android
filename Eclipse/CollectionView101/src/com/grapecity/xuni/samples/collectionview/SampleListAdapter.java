package com.grapecity.xuni.samples.collectionview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SampleListAdapter extends BaseAdapter
{
	private List<SampleModel> mList;
	private LayoutInflater mInflater;

	public SampleListAdapter(Context context)
	{
		mList = new ArrayList<SampleModel>();
		this.mInflater = LayoutInflater.from(context);

		// initializing SampleModel objects for each sample
		SampleModel sorting = new SampleModel(context.getResources().getString(R.string.sorting), context.getResources().getString(R.string.sorting_desc),
				R.drawable.sort);
		SampleModel filtering = new SampleModel(context.getResources().getString(R.string.filtering),
				context.getResources().getString(R.string.filtering_desc), R.drawable.filter);
		SampleModel grouping = new SampleModel(context.getResources().getString(R.string.grouping), context.getResources().getString(R.string.grouping_desc),
				R.drawable.flexgrid_grouping);
		SampleModel simpleOnDemand = new SampleModel(context.getResources().getString(R.string.simple_on_demand), context.getResources().getString(
				R.string.simple_on_demand_desc), R.drawable.flexgrid_loading);

		// adding objects to list
		mList.add(sorting);
		mList.add(filtering);
		mList.add(grouping);
		mList.add(simpleOnDemand);
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
		return mList.get(position).getThumbnailResourceId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = this.mInflater.inflate(R.layout.activity_custom_list, parent, false);
		}

		SampleModel sample = mList.get(position);

		// creating custom view for each list element
		((ImageView) convertView.findViewById(R.id.sampleImage)).setImageResource(sample.getThumbnailResourceId());
		((TextView) convertView.findViewById(R.id.sampleName)).setText(sample.getName());
		((TextView) convertView.findViewById(R.id.sampleDesc)).setText(sample.getDescription());

		return convertView;
	}
}
