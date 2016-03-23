package com.grapecity.xuni.samples.collectionview;

import java.util.Collections;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.grapecity.xuni.core.CollectionView;
import com.grapecity.xuni.core.SortDescription;

public class SortingActivity extends BaseActivity
{
	private ImageView actionImageView;
	private boolean isAscending = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_list);

		collectionView = new CollectionView<YoutubeItem>(Collections.<YoutubeItem> emptyList());
		SortDescription sd = new SortDescription("title", true);
		collectionView.getSortDescriptions().add(sd);

		listView = (ListView) findViewById(R.id.collection_list);
		CollectionViewAdapter adapter = new CollectionViewAdapter(this, collectionView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		new DownloadYoububeFilesTask().execute();

		// Set up your ActionBar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);

		final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.action_bar, null);
		LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		actionBar.setCustomView(actionBarLayout, layout);
		actionImageView = (ImageView) this.findViewById(R.id.actionView);
		actionImageView.setImageResource(R.drawable.ic_sort_descending);
		actionImageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				isAscending = !isAscending;
				if (isAscending)
				{
					actionImageView.setImageResource(R.drawable.ic_sort_descending);
					collectionView.getSortDescriptions().get(0).setAscending(isAscending);

				}
				else
				{
					actionImageView.setImageResource(R.drawable.ic_sort_ascending);
					collectionView.getSortDescriptions().get(0).setAscending(isAscending);
				}

				collectionView.refresh();

				CollectionViewAdapter adapter = (CollectionViewAdapter) listView.getAdapter();
				adapter.notifyDataSetChanged();
			}
		});

	}

}
