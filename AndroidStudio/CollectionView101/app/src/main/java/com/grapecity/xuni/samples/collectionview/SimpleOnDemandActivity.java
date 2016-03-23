package com.grapecity.xuni.samples.collectionview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.grapecity.xuni.core.CollectionView;

public class SimpleOnDemandActivity extends BaseActivity
{
	private boolean isInLoading = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_list);

		List<YoutubeItem> list = DataSource.getRandomStringList(0, 20);

		System.out.println("list.size()=" + list.size());
		collectionView = new CollectionView<YoutubeItem>(list);

		listView = (ListView) findViewById(R.id.collection_list);
		final SimpleOnDemandListViewAdapter adapter = new SimpleOnDemandListViewAdapter(this, collectionView);
		listView.setAdapter(adapter);

		listView.setOnScrollListener(new OnScrollListener()
		{
			public void onScrollStateChanged(AbsListView view, int scrollState)
			{
			}

			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
			{

				if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0)
				{
					if (isInLoading == false)
					{
						isInLoading = true;

						ArrayList<YoutubeItem> items = collectionView.getItems();
						List<YoutubeItem> addList = DataSource.getRandomStringList(items.size(), 10);

						collectionView.addAll(addList);
						collectionView.refresh();

						adapter.notifyDataSetChanged();
						isInLoading = false;
					}
				}
			}
		});

	}

}
