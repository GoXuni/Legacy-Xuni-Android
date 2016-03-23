package com.grapecity.xuni.samples.flexpie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.grapecity.xuni.core.*;
import com.grapecity.xuni.flexpie.*;

public class SnapshotActivity extends Activity
{
	private FlexPie mFlexPie;
	private MarginF mPlotMargin;

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.actionSnapshot)
		{
			// export image and return the status
			return exportImage();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_snapshot, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_snapshot);

		// initializing widgets
		mFlexPie = (FlexPie) findViewById(R.id.flexPie);

		// creating a list of fruit objects of type BindObject for first FlexPie
		ObservableList<Object> flexpieFruits = new ObservableList<Object>();
		flexpieFruits.add(new BindObject("Oranges", 11));
		flexpieFruits.add(new BindObject("Apples", 94));
		flexpieFruits.add(new BindObject("Pears", 93));
		flexpieFruits.add(new BindObject("Bananas", 2));
		flexpieFruits.add(new BindObject("Pineapples", 53));

		// set the binding of FlexPie to variables of BindObject
		mFlexPie.setBindingName("name");
		mFlexPie.setBinding("value");

		// setting the source of data/items and default values in FlexPie
		mFlexPie.setItemsSource(flexpieFruits);
		mPlotMargin = new MarginF(10f, 10f, 10f, 10f);
		mFlexPie.setPlotMargin(mPlotMargin);

		// export image when the chart is fully rendered
		/*
		 * mFlexPie.getRendered().addHandler(new IEventHandler() {
		 *
		 * @Override public void call(Object arg0, Object arg1) { if (exportImage())
		 * Toast.makeText(getApplicationContext(), "Snapshot stored to device",
		 * Toast.LENGTH_SHORT).show(); } }, mFlexPie);
		 */
	}

	/**
	 * Exports the bitmap of the current pie chart to the device's storage
	 *
	 * @return
	 *
	 */
	public boolean exportImage()
	{
		final String APP_PATH_SD_CARD = "/xuni/samples/FlexPie/";
		Bitmap image = mFlexPie.getImage();

		String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_PATH_SD_CARD;

		try
		{
			File dir = new File(fullPath);
			if (!dir.exists())
			{
				dir.mkdirs();
			}

			// save image to a new file
			OutputStream fOut = null;
			File file = new File(fullPath, "snapshot.jpeg");
			file.createNewFile();
			fOut = new FileOutputStream(file);

			// convert image to jpeg format with no compression
			image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
			fOut.flush();
			fOut.close();

			// add index of the image to the gallery
			Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			Uri contentUri = Uri.fromFile(file);
			mediaScanIntent.setData(contentUri);
			getApplicationContext().sendBroadcast(mediaScanIntent);

			Toast.makeText(getApplicationContext(), R.string.snapshotStored, Toast.LENGTH_SHORT).show();
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
