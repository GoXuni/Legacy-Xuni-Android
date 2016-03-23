package com.grapecity.xuni.samples.collectionview;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.grapecity.xuni.core.CollectionView;

public class BaseActivity extends Activity implements OnItemClickListener
{
	protected ListView listView;
	protected CollectionView<YoutubeItem> collectionView;

	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		YoutubeItem video = collectionView.getItems().get(position);
		String link = video.getLink();

		Uri uri = Uri.parse(link);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	private class DownloadYoububeItemImageTask extends AsyncTask<URL, Integer, Long>
	{
		private YoutubeItem youtubeItem;

		public DownloadYoububeItemImageTask(YoutubeItem youtubeItem)
		{
			this.youtubeItem = youtubeItem;
		}

		protected Long doInBackground(URL... urls)
		{
			try
			{
				URL url = new URL(youtubeItem.getThumbnail());

				// This url is a test for Chinese team since they cannot access to youbube.com.
				// URL url = new URL("https://www.baidu.com/img/baidu_jgylogo3.gif");

				Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
				youtubeItem.setThumbmailBitmap(bitmap);

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			return 1L;
		}

		protected void onProgressUpdate(Integer... progress)
		{
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(Long result)
		{
			if (listView != null)
			{
				ListAdapter adapter = listView.getAdapter();
				if (adapter != null)
				{
					((CollectionViewAdapter) adapter).notifyDataSetChanged();
				}
			}

		}
	}

	protected class DownloadYoububeFilesTask extends AsyncTask<URL, Integer, Long>
	{
		protected Long doInBackground(URL... urls)
		{
			List<YoutubeItem> sourceCollection = DataSource.getList();

			if (sourceCollection != null && sourceCollection.size() > 0)
			{
				for (YoutubeItem item : sourceCollection)
				{
					new DownloadYoububeItemImageTask(item).execute();
				}

				collectionView.setSourceCollection(sourceCollection);
			}

			return 1L;
		}

		protected void onProgressUpdate(Integer... progress)
		{
			// setProgressPercent(progress[0]);
		}

		protected void onPostExecute(Long result)
		{
			if (listView != null)
			{
				ListAdapter adapter = listView.getAdapter();
				if (adapter != null)
				{
					((CollectionViewAdapter) adapter).notifyDataSetChanged();
				}
			}
		}
	}
}
