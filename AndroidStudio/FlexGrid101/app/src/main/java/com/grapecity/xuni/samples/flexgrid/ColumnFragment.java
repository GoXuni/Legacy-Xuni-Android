package com.grapecity.xuni.samples.flexgrid;

import java.util.List;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class ColumnFragment extends DialogFragment
{

	OnDataPass mDataPasser;
	private ListView mColumnList;
	private String[] mColumnNames;
	private int[] mColumnWidths;
	private Button mButtonApply;
	private Button mButtonCancel;

	@Override
	public void onAttach(Activity a)
	{
		super.onAttach(a);
		mDataPasser = (OnDataPass) a;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View rootView = inflater.inflate(R.layout.fragment_edit_column, container, false);
		getDialog().setTitle("Column Manager");

		mButtonApply = (Button) rootView.findViewById(R.id.buttonApply);
		mButtonCancel = (Button) rootView.findViewById(R.id.buttonCancel);

		mColumnNames = getArguments().getStringArray("names");
		mColumnWidths = getArguments().getIntArray("widths");

		// initializing listView and applying custom adapter
		mColumnList = (ListView) rootView.findViewById(R.id.editColumnsListview);
		final ColumnListAdapter adapter = new ColumnListAdapter(this.getActivity().getBaseContext(), mColumnNames, mColumnWidths);
		mColumnList.setAdapter(adapter);

		mButtonApply.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// passing data to the activity
				mDataPasser.saveData(adapter.getmList());
				dismiss();
			}
		});

		mButtonCancel.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// dismissing the fragment
				dismiss();
			}
		});

		return rootView;
	}

	public interface OnDataPass
	{
		public void saveData(List<ColumnModel> list);
	}
}