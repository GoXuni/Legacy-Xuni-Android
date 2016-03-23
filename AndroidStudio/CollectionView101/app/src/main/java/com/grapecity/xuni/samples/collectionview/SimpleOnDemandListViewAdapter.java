package com.grapecity.xuni.samples.collectionview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grapecity.xuni.core.CollectionView;

public class SimpleOnDemandListViewAdapter extends CollectionViewAdapter
{
	private Context context;

	public SimpleOnDemandListViewAdapter(Context context, CollectionView<YoutubeItem> collectionView)
	{
		super(context, collectionView);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = new TextView(context);
		}

		YoutubeItem youtubeVideo = collectionView.getItems().get(position);

		((TextView) convertView).setText(youtubeVideo.getTitle());

		return convertView;
	}
}
