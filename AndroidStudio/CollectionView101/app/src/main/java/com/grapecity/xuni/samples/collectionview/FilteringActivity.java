package com.grapecity.xuni.samples.collectionview;

import java.util.Collections;
import java.util.Locale;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ListView;
import android.widget.TextView;

import com.grapecity.xuni.core.CollectionView;
import com.grapecity.xuni.core.IPredicate;

public class FilteringActivity extends BaseActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// setTheme(android.R.style.Theme_Holo);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtering);

		collectionView = new CollectionView<YoutubeItem>(Collections.<YoutubeItem> emptyList());

		listView = (ListView) findViewById(R.id.collection_list);
		CollectionViewAdapter adapter = new CollectionViewAdapter(this, collectionView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

		new DownloadYoububeFilesTask().execute();

		// Set up your filter text
		TextView filterText = (TextView) findViewById(R.id.filterText);
		filterText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{

			}

			@Override
			public void afterTextChanged(Editable s)
			{
				TextView filterText = (TextView) findViewById(R.id.filterText);
				final CharSequence text = filterText.getText();

				if (text != null && text.length() > 0)
				{
					collectionView.setFilter(new IPredicate<YoutubeItem>()
					{
						@Override
						public boolean execute(YoutubeItem item)
						{
							String title = item.getTitle().toLowerCase();
							String filterString = text.toString().toLowerCase();
							if (title.contains(filterString))
							{
								return true;
							}
							else
							{
								return false;
							}
						}
					});

				}
				else
				{
					collectionView.setFilter(null);
				}

				CollectionViewAdapter adapter = (CollectionViewAdapter) listView.getAdapter();
				adapter.notifyDataSetChanged();
			}

		});

	}
}
