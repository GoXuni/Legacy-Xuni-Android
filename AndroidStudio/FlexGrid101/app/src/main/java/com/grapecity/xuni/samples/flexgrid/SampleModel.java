package com.grapecity.xuni.samples.flexgrid;

public class SampleModel
{
	private String name;
	private String description;
	private int thumbnailResourceId;

	public SampleModel(String name, String description, int thumbnailResourceId)
	{
		this.name = name;
		this.description = description;
		this.thumbnailResourceId = thumbnailResourceId;
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
}
