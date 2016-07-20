package com.grapecity.xuni.samples.input;

import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.samples.input.R;

/**
 * Drop down item java bean.
 * */
public class DropDownItem
{
	public String name;
	public String value;
	public int flag;
	static String[] country =
	{ "Australia", "Bangladesh", "Brazil", "Canada", "China", "France", "Germany", "India", "Japan", "Nepal", "Pakistan", "Srilanka" };

	DropDownItem(String name, String value)
	{
		this.name = name;
		this.value = value;
	}

	DropDownItem(String name, String value, int flag)
	{
		this.name = name;
		this.value = value;
		this.flag = flag;
	}

	/**
	 * a method to create a list of objects of type DropDownItem;
	 * 
	 * @param size
	 *            - size of element.
	 * */
	static ObservableList<DropDownItem> getList()
	{
		ObservableList<DropDownItem> list = new ObservableList<DropDownItem>();

		int[] flagId =
		{ R.drawable.australia, R.drawable.bangladesh, R.drawable.brazil, R.drawable.canada, R.drawable.china, R.drawable.france, R.drawable.germany, R.drawable.india,
				R.drawable.japan, R.drawable.nepal, R.drawable.pakistan, R.drawable.srilanka };

		for (int i = 0; i < country.length; i++)
		{
			list.add(new DropDownItem(country[i], i + "", flagId[i]));
		}

		return list;
	}

}
