package com.grapecity.xuni.samples.flexchart;

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

import com.grapecity.xuni.flexchart.*;

public class SnapshotActivity extends Activity
{
	private FlexChart mChart;

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

		// initializing widget
		mChart = (FlexChart) findViewById(R.id.flexchart);

		// set the binding for X-axis of FlexChart
		mChart.setBindingX("name");

		// initialize series elements and set the binding to variables of
		// ChartPoint
		ChartSeries seriesSales = new ChartSeries(mChart, "Sales", "sales");
		ChartSeries seriesExpenses = new ChartSeries(mChart, "Expenses", "expenses");
		ChartSeries seriesDownloads = new ChartSeries(mChart, "Downloads", "downloads");

		// add series to list
		mChart.getSeries().add(seriesSales);
		mChart.getSeries().add(seriesExpenses);
		mChart.getSeries().add(seriesDownloads);

		// setting the source of data/items in FlexPie
		mChart.setItemsSource(ChartPoint.getList());

		// export image when the chart is fully rendered
		/*
		 * mChart.getRendered().addHandler(new IEventHandler() {
		 *
		 * @Override public void call(Object arg0, Object arg1) { if (exportImage())
		 * Toast.makeText(getApplicationContext(), "Snapshot stored to device",
		 * Toast.LENGTH_SHORT).show(); } }, mChart);
		 */
	}

	/**
	 * Exports the bitmap of the current chart to the device's storage
	 *
	 * @return
	 *
	 */
	public boolean exportImage()
	{
		final String APP_PATH_SD_CARD = "/xuni/samples/FlexChart/";
		Bitmap image = mChart.getImage();

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

			Toast.makeText(getApplicationContext(), "Snapshot stored to device", Toast.LENGTH_SHORT).show();
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
