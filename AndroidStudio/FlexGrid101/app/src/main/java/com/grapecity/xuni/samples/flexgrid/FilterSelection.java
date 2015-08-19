package com.grapecity.xuni.samples.flexgrid;

public enum FilterSelection
{
	NONE(0), CONTAINS(1), EQUALS(2), STARTSWITH(3), ENDSWITH(4);

	int id;

	FilterSelection(int id)
	{
		this.id = id;
	}
}
