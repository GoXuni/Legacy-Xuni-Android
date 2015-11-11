package com.grapecity.xuni.samples.flexgrid.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ColumnModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final String SHARED_PREF_KEY = "C1_FlexGrid101_ColumnLayout";

	private static final String COLUMN_NAME_KEY = "columnName";
	private static final String VISIBLE_KEY = "visible";

	private String columnName;
	private boolean visible;

	public ColumnModel(String columnName, boolean visible)
	{
		this.columnName = columnName;
		this.visible = visible;
	}

	/**
	 * Save a list of ColumnModel objects to the SharedPreferences.
	 * 
	 * @param context
	 * @param columns
	 * @throws JSONException
	 */
	public static void saveToPreferences(Context context, List<ColumnModel> columns) throws JSONException
	{
		JSONArray array = new JSONArray();

		for (ColumnModel model : columns)
		{
			JSONObject jsonColumnModel = new JSONObject();

			jsonColumnModel.put(COLUMN_NAME_KEY, model.getColumnName());
			jsonColumnModel.put(VISIBLE_KEY, model.isVisible());

			array.put(jsonColumnModel);
		}

		SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);

		SharedPreferences.Editor editor = sharedPreference.edit();

		editor.putString(SHARED_PREF_KEY, array.toString());

		editor.commit();
	}

	/**
	 * Get a list of ColumnModel objects from the SharedPreferences.
	 * 
	 * @param context
	 * @return
	 * @throws JSONException
	 */
	public static List<ColumnModel> getFromPreferences(Context context) throws JSONException
	{
		SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(context);

		if (sharedPreference.contains(SHARED_PREF_KEY))
		{
			List<ColumnModel> returnList = new ArrayList<ColumnModel>();

			String jsonString = sharedPreference.getString(SHARED_PREF_KEY, null);

			JSONArray array = new JSONArray(jsonString);

			for (int i = 0; i < array.length(); i++)
			{
				JSONObject jsonColumnModel = (JSONObject) array.get(i);

				returnList.add(new ColumnModel(jsonColumnModel.getString(COLUMN_NAME_KEY), jsonColumnModel.getBoolean(VISIBLE_KEY)));
			}

			return returnList;
		}

		return null;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String name)
	{
		this.columnName = name;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

}
