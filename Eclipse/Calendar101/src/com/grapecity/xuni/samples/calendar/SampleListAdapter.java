package com.grapecity.xuni.samples.calendar;

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
				R.string.getting_started_desc), R.drawable.calendar);
		SampleModel verticalOrientation = new SampleModel(context.getResources().getString(R.string.vertical_orientation), context.getResources().getString(
				R.string.vertical_orientation_desc), R.drawable.calendar_vertical);
		SampleModel customDayContent = new SampleModel(context.getResources().getString(R.string.custom_day_content), context.getResources().getString(
				R.string.custom_day_content_desc), R.drawable.calendar2);
		SampleModel customHeader = new SampleModel(context.getResources().getString(R.string.custom_header), context.getResources().getString(
				R.string.custom_header_desc), R.drawable.calendar);
		SampleModel customAppearance = new SampleModel(context.getResources().getString(R.string.custom_appearance), context.getResources().getString(
				R.string.custom_appearance_desc), R.drawable.calendar);
		SampleModel popupEditor = new SampleModel(context.getResources().getString(R.string.popup_editor), context.getResources().getString(
				R.string.popup_editor_desc), R.drawable.calendar_datepicker);
		SampleModel customerSelection = new SampleModel(context.getResources().getString(R.string.custom_selection), context.getResources().getString(
				R.string.custom_selection_desc), R.drawable.calendar2);
		SampleModel customHeaderText = new SampleModel(context.getResources().getString(R.string.custom_header_text), context.getResources().getString(
				R.string.custom_header_text_desc), R.drawable.calendar);

		// adding objects to list
		mList.add(gettingStarted);
		mList.add(verticalOrientation);
		mList.add(customDayContent);
		mList.add(customHeader);
		mList.add(customAppearance);
		mList.add(popupEditor);
		mList.add(customerSelection);
		//mList.add(customHeaderText);
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
