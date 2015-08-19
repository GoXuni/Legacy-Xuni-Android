package com.grapecity.xuni.samples.flexgrid;

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
		SampleModel gettingStarted = new SampleModel(context.getResources().getString(R.string.gettingStarted), context.getResources().getString(
				R.string.gettingStartedDesc), R.drawable.flexgrid);
		SampleModel columnDefinitions = new SampleModel(context.getResources().getString(R.string.columnDefinitions), context.getResources().getString(
				R.string.columnDefinitionsDesc), R.drawable.flexgrid_columns);
		SampleModel selectionModes = new SampleModel(context.getResources().getString(R.string.selectionModes), context.getResources().getString(
				R.string.selectionModesDesc), R.drawable.flexgrid_selection);
		SampleModel editing = new SampleModel(context.getResources().getString(R.string.editing), context.getResources().getString(R.string.editingDesc),
				R.drawable.input_form);
		SampleModel editConfirmation = new SampleModel(context.getResources().getString(R.string.editConfirm), context.getResources().getString(R.string.editConfirmDesc),
				R.drawable.input_form);
		SampleModel customCells = new SampleModel(context.getResources().getString(R.string.customCells), context.getResources().getString(
				R.string.customCellsDesc), R.drawable.flexgrid_custom);
		SampleModel grouping = new SampleModel(context.getResources().getString(R.string.grouping), context.getResources().getString(R.string.groupingDesc),
				R.drawable.flexgrid_grouping);
		SampleModel filter = new SampleModel(context.getResources().getString(R.string.filter), context.getResources().getString(R.string.filterDesc),
				R.drawable.flexgrid_filter);
		SampleModel fullFilter = new SampleModel(context.getResources().getString(R.string.fullFilter), context.getResources().getString(
				R.string.fullFilterDesc), R.drawable.filter);
		SampleModel columnLayout = new SampleModel(context.getResources().getString(R.string.columnLayout), context.getResources().getString(
				R.string.columnLayoutDesc), R.drawable.flexgrid_columns);
		SampleModel conditionalFormatting = new SampleModel(context.getResources().getString(R.string.conditionalFormatting), context.getResources().getString(
				R.string.conditionalFormattingDesc), R.drawable.flexgrid);

		// adding objects to list
		mList.add(gettingStarted);
		mList.add(columnDefinitions);
		mList.add(selectionModes);
		mList.add(editing);
		mList.add(editConfirmation);
		mList.add(customCells);
		mList.add(grouping);
		mList.add(filter);
		mList.add(fullFilter);
		mList.add(columnLayout);
		mList.add(conditionalFormatting);
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
