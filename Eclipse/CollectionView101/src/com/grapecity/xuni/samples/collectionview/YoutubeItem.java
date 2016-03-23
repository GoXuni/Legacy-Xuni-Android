package com.grapecity.xuni.samples.collectionview;

import android.graphics.Bitmap;

public class YoutubeItem
{
	private String title;
	private String description;
	private String thumbnail;
	private Bitmap thumbmailBitmap;
	private String link;
	private String channelTitle;

	private static final String BASE_URL = "https://www.youtube.com/watch?v=";

	public YoutubeItem()
	{
	}

	public YoutubeItem(String title, String thumbnail, String videoId, String channelTitle)
	{
		this.title = title;
		this.thumbnail = thumbnail;
		this.link = BASE_URL + videoId;
		this.channelTitle = channelTitle;
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

	public String getThumbnail()
	{
		return thumbnail;
	}

	public void setThumbnail(String thumbnail)
	{
		this.thumbnail = thumbnail;
	}

	public Bitmap getThumbmailBitmap()
	{
		return thumbmailBitmap;
	}

	public void setThumbmailBitmap(Bitmap thumbmailBitmap)
	{
		this.thumbmailBitmap = thumbmailBitmap;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getChannelTitle()
	{
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle)
	{
		this.channelTitle = channelTitle;
	}

}
