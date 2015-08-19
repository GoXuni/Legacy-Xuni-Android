package com.grapecity.xuni.samples.gauge;

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
		SampleModel gettingStarted = new SampleModel(context.getResources().getString(R.string.getting_started), context.getResources().getString(
				R.string.getting_started_desc), R.drawable.gauge_basic);
		SampleModel displayingValues = new SampleModel(context.getResources().getString(R.string.displaying_values), context.getResources().getString(
				R.string.displaying_values_desc), R.drawable.gauge_radial);
		SampleModel usingRanges = new SampleModel(context.getResources().getString(R.string.using_ranges), context.getResources().getString(
				R.string.using_ranges_desc), R.drawable.gauge_ranges);
		SampleModel automaticScaling = new SampleModel(context.getResources().getString(R.string.automatic_scaling), context.getResources().getString(
				R.string.automatic_scaling_desc), R.drawable.gauge_scaling);
		SampleModel direction = new SampleModel(context.getResources().getString(R.string.direction),
				context.getResources().getString(R.string.direction_desc), R.drawable.gauge_linear);
		SampleModel bulletGraph = new SampleModel(context.getResources().getString(R.string.bulletgraph), context.getResources().getString(
				R.string.bulletgraph_desc), R.drawable.gauge_bullet);
		SampleModel customAnimation = new SampleModel(context.getResources().getString(R.string.custom_animation), context.getResources().getString(
				R.string.custom_animation_desc), R.drawable.gauge_basic);

		// adding objects to list
		mList.add(gettingStarted);
		mList.add(displayingValues);
		mList.add(usingRanges);
		mList.add(automaticScaling);
		mList.add(direction);
		mList.add(bulletGraph);
		mList.add(customAnimation);
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
