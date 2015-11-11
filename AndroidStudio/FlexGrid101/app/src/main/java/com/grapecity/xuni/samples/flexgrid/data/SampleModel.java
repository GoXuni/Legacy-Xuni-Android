package com.grapecity.xuni.samples.flexgrid.data;

import android.app.Activity;

public class SampleModel
{
	public final String name;
	public final String description;
	public final int thumbnailResourceId;
	public final Class<? extends Activity> activityClass;

	public SampleModel(String name, String description, int thumbnailResourceId, Class<? extends Activity> activityClass)
	{
		this.name = name;
		this.description = description;
		this.thumbnailResourceId = thumbnailResourceId;
		this.activityClass = activityClass;
	}
}
