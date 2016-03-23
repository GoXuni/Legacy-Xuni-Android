package com.grapecity.xuni.samples.collectionview;

import java.util.Collections;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.grapecity.xuni.core.CollectionView;
import com.grapecity.xuni.core.PropertyGroupDescription;

public class GroupingActivity extends BaseActivity
{
	private CollectionViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection_list);

		collectionView = new CollectionView<YoutubeItem>(Collections.<YoutubeItem> emptyList());

		PropertyGroupDescription gd = new PropertyGroupDescription("channelTitle");
		collectionView.getGroupDescriptions().add(gd);

		listView = (ListView) findViewById(R.id.collection_list);
		adapter = new GroupedCollectionViewAdapter(this, collectionView);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);

		new DownloadYoububeFilesTask().execute();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Object object = adapter.getItem(position);
		if (object instanceof YoutubeItem){
			YoutubeItem video = (YoutubeItem) object;
			String link = video.getLink();

			Uri uri = Uri.parse(link);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
	}
}
