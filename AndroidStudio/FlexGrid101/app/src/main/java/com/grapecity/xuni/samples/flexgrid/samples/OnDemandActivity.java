package com.grapecity.xuni.samples.flexgrid.samples;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.FlexGridCanvasRenderEngine;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridColumn;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.flexgrid.GridRow;
import com.grapecity.xuni.flexgrid.ItemFormatter;
import com.grapecity.xuni.samples.flexgrid.R;
import com.grapecity.xuni.samples.flexgrid.YoutubeCollectionView;
import com.grapecity.xuni.samples.flexgrid.data.YoutubeItem;

public class OnDemandActivity extends Activity
{
	private FlexGrid mGrid;
	private TextView mNoInternetTextView;
	private ProgressBar mProgressIndicator;

	private EditText mYoutubeSearchEditText;
	private Spinner mYoutubeOrderBySpinner;

	private YoutubeCollectionView mCollectionView;

	private String[] youtobeOrderByValuesArray = new String[]
	{ "relevance", "date", "viewCount", "rating", "title" };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_on_demand);

		mGrid = (FlexGrid) findViewById(R.id.flexgrid);
		mNoInternetTextView = (TextView) findViewById(R.id.noInternetTextView);
		mProgressIndicator = (ProgressBar) findViewById(R.id.progressIndicator);
		mYoutubeSearchEditText = (EditText) findViewById(R.id.youtubeSearchEditText);
		mYoutubeOrderBySpinner = (Spinner) findViewById(R.id.youtubeOrderBySpinner);

		mYoutubeSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
		{
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				if (actionId == EditorInfo.IME_ACTION_DONE)
				{
					if (mCollectionView != null)
					{
						mCollectionView.setQuery(mYoutubeSearchEditText.getText().toString());
					}
				}
				
				return false;
			}
		});

		// initializing adapter to string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.youtubeOrderByValues, android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		mYoutubeOrderBySpinner.setAdapter(adapter1);
		mYoutubeOrderBySpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if (mCollectionView != null)
				{
					mCollectionView.setOrderBy(youtobeOrderByValuesArray[mYoutubeOrderBySpinner.getSelectedItemPosition()]);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		mGrid.setAutoGenerateColumns(false);

		mGrid.getRows().setDefaultSize(75);

		GridColumn thumbnailColumn = new GridColumn(mGrid, "", "thumbnailUrl");
		thumbnailColumn.setItemFormatter(mImageFormatter);

		GridColumn titleColumn = new GridColumn(mGrid, "Title", "title");
		titleColumn.setWordWrap(true);

		GridColumn descriptionColumn = new GridColumn(mGrid, "Description", "description");
		descriptionColumn.setWidth("*");
		descriptionColumn.setWordWrap(true);

		mGrid.getColumns().add(thumbnailColumn);
		mGrid.getColumns().add(titleColumn);
		mGrid.getColumns().add(descriptionColumn);

		if (isConnected())
		{
			new HttpAsyncTask().execute();
		}
		else
		{
			noInternet();
		}
	}

	/**
	 * Custom item formatter for the thumbnail column to draw the bitmap directly to the FlexGrid
	 * canvas.
	 */
	private ItemFormatter mImageFormatter = new ItemFormatter()
	{
		GridRow gridRow;
		YoutubeItem item;
		Bitmap bitmap;
		Rect dstRect = new Rect();

		@Override
		public String formatItem(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
		{
			gridRow = gridPanel.getRows().get(cellRange.row);

			item = (YoutubeItem) gridRow.getDataItem();

			bitmap = item.getBitmap();

			if (bitmap != null)
			{
				dstRect.left = bounds.left;
				dstRect.top = bounds.top;
				dstRect.right = bounds.right;
				dstRect.bottom = bounds.bottom;

				// adjust the rectangle according to the current pan x/y offset w/o reallocating a
				// new bounds during onDraw
				renderEngine.adjustRectWithPanOffsetDontAllocate(dstRect);

				renderEngine.canvas.drawBitmap(bitmap, null, dstRect, null);
			}
			else
			{
				// item is being shown in the viewport, need to make sure the bitmap is loaded
				item.asyncLoadBitmap();
			}

			return null;
		}
	};

	private void noInternet()
	{
		mProgressIndicator.setVisibility(View.GONE);
		mNoInternetTextView.setVisibility(View.VISIBLE);
	}

	private class HttpAsyncTask extends AsyncTask<String, Void, List<YoutubeItem>>
	{
		@Override
		protected List<YoutubeItem> doInBackground(String... urls)
		{
			mCollectionView = new YoutubeCollectionView(25);
			return mCollectionView.searchVideos("android development", "relevance", null, 25);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(List<YoutubeItem> items)
		{
			if (items == null || items.size() <= 0)
			{
				noInternet();
			}
			else
			{
				mCollectionView.addAll(items);

				mGrid.setCollectionView(mCollectionView);

				mGrid.setVisibility(View.VISIBLE);
				mProgressIndicator.setVisibility(View.GONE);
			}
		}
	}

	public boolean isConnected()
	{
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}
}
