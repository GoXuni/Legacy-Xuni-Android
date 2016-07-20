package com.grapecity.xuni.samples.flexgrid.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.data.SampleModel;
import com.grapecity.xuni.samples.flexgrid.samples.CellFreezingActivity;
import com.grapecity.xuni.samples.flexgrid.samples.ColumnDefinitionsActivity;
import com.grapecity.xuni.samples.flexgrid.samples.ColumnLayoutActivity;
import com.grapecity.xuni.samples.flexgrid.samples.ConditionalFormattingActivity;
import com.grapecity.xuni.samples.flexgrid.samples.CustomCellsActivity;
import com.grapecity.xuni.samples.flexgrid.samples.CustomMergingActivity;
import com.grapecity.xuni.samples.flexgrid.samples.EditConfirmationActivity;
import com.grapecity.xuni.samples.flexgrid.samples.EditingActivity;
import com.grapecity.xuni.samples.flexgrid.samples.FilterActivity;
import com.grapecity.xuni.samples.flexgrid.samples.FullFilterActivity;
import com.grapecity.xuni.samples.flexgrid.samples.GettingStartedActivity;
import com.grapecity.xuni.samples.flexgrid.samples.GroupingActivity;
import com.grapecity.xuni.samples.flexgrid.samples.NewRowActivity;
import com.grapecity.xuni.samples.flexgrid.samples.OnDemandActivity;
import com.grapecity.xuni.samples.flexgrid.samples.RowDetailsActivity;
import com.grapecity.xuni.samples.flexgrid.samples.SelectionModesActivity;
import com.grapecity.xuni.samples.flexgrid.samples.StarSizingActivity;
import com.grapecity.xuni.samples.flexgrid.samples.UnboundActivity;

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
				R.string.gettingStartedDesc), R.drawable.flexgrid, GettingStartedActivity.class);
		
		SampleModel columnDefinitions = new SampleModel(context.getResources().getString(R.string.columnDefinitions), context.getResources().getString(
				R.string.columnDefinitionsDesc), R.drawable.flexgrid_columns, ColumnDefinitionsActivity.class);
		  
		SampleModel selectionModes = new SampleModel(context.getResources().getString(R.string.selectionModes), context.getResources().getString(
				R.string.selectionModesDesc), R.drawable.flexgrid_selection, SelectionModesActivity.class);
		
		SampleModel editConfirmation = new SampleModel(context.getResources().getString(R.string.editConfirm), context.getResources().getString(R.string.editConfirmDesc),
				R.drawable.input_form, EditConfirmationActivity.class);
		
		SampleModel editing = new SampleModel(context.getResources().getString(R.string.editing), context.getResources().getString(R.string.editingDesc),
				R.drawable.input_form, EditingActivity.class);
		
		SampleModel customCells = new SampleModel(context.getResources().getString(R.string.customCells), context.getResources().getString(
				R.string.customCellsDesc), R.drawable.flexgrid_custom, CustomCellsActivity.class);
		
		SampleModel grouping = new SampleModel(context.getResources().getString(R.string.grouping), context.getResources().getString(R.string.groupingDesc),
				R.drawable.flexgrid_grouping, GroupingActivity.class);
		
		SampleModel rowDetails = new SampleModel(context.getResources().getString(R.string.rowDetails), context.getResources().getString(
				R.string.rowDetailsDesc), R.drawable.flexgrid, RowDetailsActivity.class);
		
		SampleModel filter = new SampleModel(context.getResources().getString(R.string.filter), context.getResources().getString(R.string.filterDesc),
				R.drawable.flexgrid_filter, FilterActivity.class);
		
		SampleModel fullFilter = new SampleModel(context.getResources().getString(R.string.fullFilter), context.getResources().getString(
				R.string.fullFilterDesc), R.drawable.filter, FullFilterActivity.class);
		
		SampleModel columnLayout = new SampleModel(context.getResources().getString(R.string.columnLayout), context.getResources().getString(
				R.string.columnLayoutDesc), R.drawable.flexgrid_columns, ColumnLayoutActivity.class);
		
		SampleModel conditionalFormatting = new SampleModel(context.getResources().getString(R.string.conditionalFormatting), context.getResources().getString(
				R.string.conditionalFormattingDesc), R.drawable.flexgrid, ConditionalFormattingActivity.class);
		
		SampleModel starSizing = new SampleModel(context.getResources().getString(R.string.starSizing), context.getResources().getString(
				R.string.starSizingDesc), R.drawable.flexgrid, StarSizingActivity.class);
		
		SampleModel cellFreezing = new SampleModel(context.getResources().getString(R.string.cellFreezing), context.getResources().getString(
				R.string.cellFreezingDesc), R.drawable.flexgrid_freezing, CellFreezingActivity.class);
		
		SampleModel cellMerging = new SampleModel(context.getResources().getString(R.string.customMerging), context.getResources().getString(
				R.string.customMergingDesc), R.drawable.flexgrid_merging, CustomMergingActivity.class);

		SampleModel unbound = new SampleModel(context.getResources().getString(R.string.unbound), context.getResources().getString(
				R.string.unboundDesc), R.drawable.flexgrid_merging, UnboundActivity.class);
		SampleModel onDemand = new SampleModel(context.getResources().getString(R.string.onDemand), context.getResources().getString(
				R.string.onDemandDesc), R.drawable.flexgrid_loading, OnDemandActivity.class);
		
		SampleModel newRow = new SampleModel(context.getResources().getString(R.string.newRow), context.getResources().getString(
				R.string.newRowDesc), R.drawable.flexgrid, NewRowActivity.class);

		// adding objects to list
		mList.add(gettingStarted);
		mList.add(columnDefinitions);
		mList.add(selectionModes);
		mList.add(editing);
		mList.add(editConfirmation);
		mList.add(customCells);
		mList.add(grouping);
		mList.add(rowDetails);
		mList.add(filter);
		mList.add(fullFilter);
		mList.add(columnLayout);
		mList.add(conditionalFormatting);
		mList.add(starSizing);
		mList.add(cellFreezing);
		mList.add(cellMerging);
		mList.add(unbound);
		mList.add(onDemand);
		//mList.add(newRow);
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
		return mList.get(position).thumbnailResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = this.mInflater.inflate(R.layout.view_list_item, parent, false);
		}

		SampleModel sample = mList.get(position);

		// creating custom view for each list element
		((ImageView) convertView.findViewById(R.id.sampleImage)).setImageResource(sample.thumbnailResourceId);
		((TextView) convertView.findViewById(R.id.sampleName)).setText(sample.name);
		((TextView) convertView.findViewById(R.id.sampleDesc)).setText(sample.description);

		return convertView;
	}
}
