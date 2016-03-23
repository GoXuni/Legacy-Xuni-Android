package com.grapecity.xuni.samples.collectionview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grapecity.xuni.core.CollectionView;
import com.grapecity.xuni.core.CollectionViewGroup;

public class GroupedCollectionViewAdapter extends CollectionViewAdapter
{
	private List<Object> list = new ArrayList<Object>();
	private static String TITLE_TAG = "titleTag";
	private static String ITEM_TAG = "itemTag";

	public GroupedCollectionViewAdapter(Context context, CollectionView<YoutubeItem> collectionView)
	{
		super(context, collectionView);
	}

	@Override
	public int getCount()
	{
		list.clear();
		ArrayList<CollectionViewGroup<YoutubeItem>> groups = collectionView.getGroups();
		for (int i = 0; i < groups.size(); i++)
		{
			CollectionViewGroup<YoutubeItem> group = groups.get(i);
			list.add(group.getName());

			List<YoutubeItem> items = group.getItems();
			for (int j = 0; j < items.size(); j++)
			{
				YoutubeItem item = items.get(j);
				list.add(item);
			}
		}

		return list.size();
	}

	@Override
	public Object getItem(int position)
	{

		return list.get(position);
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
		Object object = list.get(position);

		if (object instanceof YoutubeItem)
		{
			if (convertView == null || convertView.getTag() != ITEM_TAG)
			{
				convertView = this.mInflater.inflate(R.layout.collection_list_inner, parent, false);
				convertView.setTag(ITEM_TAG);
			}

			YoutubeItem youtubeVideo = (YoutubeItem) object;

			ImageView imageView = (ImageView) convertView.findViewById(R.id.youtubeImage);
			imageView.setImageBitmap(youtubeVideo.getThumbmailBitmap());

			((TextView) convertView.findViewById(R.id.yoububeDescription)).setText(youtubeVideo.getTitle());

		}
		else
		{
			if (convertView == null || convertView.getTag() != TITLE_TAG)
			{
				convertView = this.mInflater.inflate(R.layout.collection_list_inner_group_title, parent, false);
				convertView.setTag(TITLE_TAG);
			}

			TextView titleView = (TextView) convertView.findViewById(R.id.yoububeGroupTitle);
			titleView.setText(object.toString());
		}

		return convertView;
	}
}
