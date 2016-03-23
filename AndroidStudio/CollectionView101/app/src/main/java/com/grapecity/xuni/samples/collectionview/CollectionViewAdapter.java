package com.grapecity.xuni.samples.collectionview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grapecity.xuni.core.CollectionView;

public class CollectionViewAdapter extends BaseAdapter
{
	protected CollectionView<YoutubeItem> collectionView;
	protected LayoutInflater mInflater;

	public CollectionViewAdapter(Context context, CollectionView<YoutubeItem> collectionView)
	{
		this.collectionView = collectionView;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		return collectionView.getItems().size();
	}

	@Override
	public Object getItem(int position)
	{
		return collectionView.getItems().get(position);
	}

	// Not used.
	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = this.mInflater.inflate(R.layout.collection_list_inner, parent, false);
		}

		YoutubeItem youtubeVideo = collectionView.getItems().get(position);
		((ImageView) convertView.findViewById(R.id.youtubeImage)).setImageBitmap(youtubeVideo.getThumbmailBitmap());
		((TextView) convertView.findViewById(R.id.yoububeDescription)).setText(youtubeVideo.getTitle());

		return convertView;
	}
}
