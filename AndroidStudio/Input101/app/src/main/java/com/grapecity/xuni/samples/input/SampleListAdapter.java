package com.grapecity.xuni.samples.input;

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
		SampleModel autocomplete = new SampleModel(context.getResources().getString(R.string.autocomplete), context.getResources().getString(
				R.string.autocomplete_desc), R.drawable.input_autocomplete);
		SampleModel comboBox = new SampleModel(context.getResources().getString(R.string.combobox), context.getResources().getString(
				R.string.combobox_desc), R.drawable.input_combobox);
		SampleModel dropdown = new SampleModel(context.getResources().getString(R.string.dropdown), context.getResources().getString(
				R.string.dropdown_desc), R.drawable.input_dropdown);
		SampleModel basicMask = new SampleModel(context.getResources().getString(R.string.basic_mask), context.getResources().getString(
				R.string.basic_mask_desc), R.drawable.input_mask);
		
		// adding objects to list
		mList.add(autocomplete);
		mList.add(comboBox);
		mList.add(dropdown);
		mList.add(basicMask);
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
