package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;

public class SampleModel
{
	private String name;
	private String description;
	private int thumbnailResourceId;
	private Class<? extends Activity> activityClass;

	public SampleModel(String name, String description, int thumbnailResourceId, Class<? extends Activity> activityClass)
	{
		this.name = name;
		this.description = description;
		this.thumbnailResourceId = thumbnailResourceId;
		this.activityClass = activityClass;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getThumbnailResourceId()
	{
		return thumbnailResourceId;
	}

	public void setThumbnailResourceId(int thumbnailResourceId)
	{
		this.thumbnailResourceId = thumbnailResourceId;
	}

	public Class<? extends Activity> getActivityClass()
	{
		return activityClass;
	}

	public void setActivityClass(Class<? extends Activity> activityClass)
	{
		this.activityClass = activityClass;
	}

}
