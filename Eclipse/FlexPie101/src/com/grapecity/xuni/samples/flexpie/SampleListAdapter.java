package com.grapecity.xuni.samples.flexpie;

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
				R.string.getting_started_desc), R.drawable.pie);
		SampleModel basicFeatures = new SampleModel(context.getResources().getString(R.string.basic_features), context.getResources().getString(
				R.string.basic_features_desc), R.drawable.pie_doughnut);
		SampleModel legendAndTitles = new SampleModel(context.getResources().getString(R.string.legend_and_titles), context.getResources().getString(
				R.string.legend_and_titles_desc), R.drawable.pie_title);
		SampleModel selection = new SampleModel(context.getResources().getString(R.string.selection),
				context.getResources().getString(R.string.selection_desc), R.drawable.pie_selection);
		SampleModel theming = new SampleModel(context.getResources().getString(R.string.theming), context.getResources().getString(R.string.theming_desc_desc),
				R.drawable.themes);
		SampleModel snapshot = new SampleModel(context.getResources().getString(R.string.snapshot), context.getResources().getString(R.string.snapshot_desc),
				R.drawable.pie);
		SampleModel updateAnimation = new SampleModel(context.getResources().getString(R.string.update_animation), context.getResources().getString(
				R.string.update_animation_desc), R.drawable.pie_selection);
		SampleModel dataLabels = new SampleModel(context.getResources().getString(R.string.data_labels), context.getResources().getString(
				R.string.data_labels_desc), R.drawable.pie_selection);
		SampleModel customTooltip = new SampleModel(context.getResources().getString(R.string.custom_tooltip), context.getResources().getString(
				R.string.custom_tooltip_desc), R.drawable.pie);

		// adding objects to list
		mList.add(gettingStarted);
		mList.add(basicFeatures);
		mList.add(legendAndTitles);
		mList.add(selection);
		mList.add(theming);
		mList.add(snapshot);
		mList.add(updateAnimation);
		mList.add(dataLabels);
		mList.add(customTooltip);
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
