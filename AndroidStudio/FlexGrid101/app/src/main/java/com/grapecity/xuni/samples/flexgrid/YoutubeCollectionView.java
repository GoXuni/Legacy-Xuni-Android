package com.grapecity.xuni.samples.flexgrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

import com.grapecity.xuni.core.CursorCollectionView;
import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.samples.flexgrid.data.YoutubeItem;

public class YoutubeCollectionView extends CursorCollectionView<YoutubeItem>
{
	private int pageSize = 10;
	private String pageToken;
	private String query = "";
	private String orderBy = "relevance";

	public YoutubeCollectionView(int pageSize)
	{
		super(new ObservableList<YoutubeItem>());

		this.pageSize = pageSize;
	}

	@Override
	public List<YoutubeItem> getPageAsync(Integer count)
	{
		return searchVideos(query, orderBy, pageToken, pageSize);
	}

	public List<YoutubeItem> searchVideos(String query, String orderBy, String pageToken, int maxResults)
	{
		String youtubeUrl = String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q=%s&order=%s&maxResults=%s%s&key=%s",
				Uri.encode(query), orderBy, maxResults, pageToken == null ? "" : "&pageToken=" + pageToken, "AIzaSyDFz8V9U0ccKXQ5oSrcRSoHqpaursqOudo");

		String json = GET(youtubeUrl);

		List<YoutubeItem> returnList = new ArrayList<YoutubeItem>();

		JSONObject result;

		try
		{
			result = new JSONObject(json);

			this.pageToken = result.getString("nextPageToken");

			JSONArray items = result.getJSONArray("items");

			for (int i = 0; i < items.length(); i++)
			{
				JSONObject youtubeJsonItem = (JSONObject) items.get(i);

				JSONObject youtubeSnippet = (JSONObject) youtubeJsonItem.get("snippet");

				String title = youtubeSnippet.getString("title");
				String description = youtubeSnippet.getString("description");

				JSONObject youtubeSnippetThumbnails = (JSONObject) youtubeSnippet.get("thumbnails");

				JSONObject youtubeSnippetThumbnailURL = (JSONObject) youtubeSnippetThumbnails.get("medium");

				String thumbnailUrl = youtubeSnippetThumbnailURL.getString("url");

				returnList.add(new YoutubeItem(title, description, thumbnailUrl));
			}

			return returnList;
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static String GET(String url)
	{
		InputStream inputStream = null;
		String result = "";
		try
		{
			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		}
		catch (Exception e)
		{
			Log.d("InputStream", "MSG: " + e.getLocalizedMessage());
		}

		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	public void setQuery(String query)
	{
		if (!this.query.equals(query))
		{
			this.query = query;
			
			resetCollection();
		}
	}

	public void setOrderBy(String orderBy)
	{
		if (!this.orderBy.equals(orderBy))
		{
			this.orderBy = orderBy;
			
			resetCollection();
		}
	}
	
	private void resetCollection()
	{
		pageToken = null;
		
		beginUpdate();
		
		clear();
		
		endUpdate();
		
		loadMoreItemsAsync(null);
	}
}
