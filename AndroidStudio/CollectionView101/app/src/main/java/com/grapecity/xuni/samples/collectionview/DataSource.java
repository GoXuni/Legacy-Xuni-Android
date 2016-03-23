package com.grapecity.xuni.samples.collectionview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class DataSource
{
	public static List<YoutubeItem> getList()
	{
		return searchVideos("android development", "relevance", null, 20);
	}

	public static List<YoutubeItem> getRandomStringList(int startIndex, int size)
	{
		List<YoutubeItem> list = new ArrayList<YoutubeItem>();
		int end = startIndex + size;
		for (int i = startIndex; i < end; i++)
		{
			SimpleDateFormat format = new SimpleDateFormat();
			format.applyPattern("MM/dd/yyyy hh:mm:ss a");
			String dataString = format.format(new Date());

			YoutubeItem video = new YoutubeItem();
			String title = "My Data Item # " + i;
			title += "\nCreated: " + dataString;

			video.setTitle(title);
			list.add(video);
		}

		return list;
	}

	private static List<YoutubeItem> searchVideos(String query, String orderBy, String pageToken, int maxResults)
	{
		String youtubeUrl = String.format("https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q=%s&order=%s&maxResults=%s%s&key=%s",
				Uri.encode(query), orderBy, maxResults, pageToken == null ? "" : "&pageToken=" + pageToken, "AIzaSyDFz8V9U0ccKXQ5oSrcRSoHqpaursqOudo");

		// This url is a test for Chinese team since they cannot access to youbube.
		// youtubeUrl = "http://redsky.3vfree.net/you.html";

		String json = get(youtubeUrl);
		List<YoutubeItem> returnList = new ArrayList<YoutubeItem>();

		JSONObject result;
		try
		{
			result = new JSONObject(json);
			// this.pageToken = result.getString("nextPageToken");

			JSONArray items = result.getJSONArray("items");

			for (int i = 0; i < items.length(); i++)
			{
				JSONObject youtubeJsonItem = (JSONObject) items.get(i);

				JSONObject youtubeSnippet = (JSONObject) youtubeJsonItem.get("snippet");

				JSONObject youtubeId = (JSONObject) youtubeJsonItem.get("id");

				String videoId = youtubeId.getString("videoId");

				String title = youtubeSnippet.getString("title");
				// String description = youtubeSnippet.getString("description");
				String channelTitle = youtubeSnippet.getString("channelTitle");

				JSONObject youtubeSnippetThumbnails = (JSONObject) youtubeSnippet.get("thumbnails");

				JSONObject youtubeSnippetThumbnailURL = (JSONObject) youtubeSnippetThumbnails.get("medium");

				String thumbnailUrl = youtubeSnippetThumbnailURL.getString("url");

				returnList.add(new YoutubeItem(title, thumbnailUrl, videoId, channelTitle));
			}

			return returnList;
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	private static String get(String url)
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
}
