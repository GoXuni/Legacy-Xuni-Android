package com.grapecity.xuni.samples.flexgrid.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class YoutubeItem
{
	private String title;
	private String description;
	private String thumbnailUrl;

	private Bitmap bitmap;
	private boolean alreadyLoading = false;

	public YoutubeItem(String title, String description, String thumbnailUrl)
	{
		super();
		this.title = title;
		this.description = description;
		this.thumbnailUrl = thumbnailUrl;

	}

	public void asyncLoadBitmap()
	{
		if (!alreadyLoading)
		{
			alreadyLoading = true;

			// kick off async task to load the bitmap
			new HttpAsyncTask().execute(this);
		}
	}

	private class HttpAsyncTask extends AsyncTask<YoutubeItem, Void, Bitmap>
	{
		YoutubeItem item;

		@Override
		protected Bitmap doInBackground(YoutubeItem... items)
		{
			item = items[0];

			return getBitmapFromURL(item.thumbnailUrl);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(Bitmap bitmap)
		{
			item.bitmap = bitmap;
		}
	}

	public static Bitmap getBitmapFromURL(String src)
	{
		try
		{
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getThumbnailUrl()
	{
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl)
	{
		this.thumbnailUrl = thumbnailUrl;
	}

	public Bitmap getBitmap()
	{
		return bitmap;
	}
}
