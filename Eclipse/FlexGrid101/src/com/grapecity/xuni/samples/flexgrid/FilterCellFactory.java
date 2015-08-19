package com.grapecity.xuni.samples.flexgrid;

import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.grapecity.xuni.flexgrid.*;

public class FilterCellFactory extends GridCellFactory
{
	private ChartPoint mChartPoint;
	private TextView mTextView;
	private int mCellWidth;
	private int mCellHeight;
	private int mPaddingLeft;
	private int mPaddingTop;
	private Spannable mSpanText;
	private String mFilterString;
	private int mHighlightLeft;
	private int mHighlightRight;

	public FilterCellFactory(FlexGrid flexGrid, Context context)
	{
		super(flexGrid);

		// initializing new textview to display
		mTextView = new TextView(context);
		flexGrid.addView(mTextView);
		mTextView.setVisibility(View.INVISIBLE);
		mTextView.setTextColor(flexGrid.getFontColor());

		mHighlightLeft = 0;
		mHighlightRight = 0;

		mPaddingLeft = Math.round(FlexGridCanvasRenderEngine.getDimensionSize(flexGrid.getCellPaddingLeft()));
		mPaddingTop = Math.round(FlexGridCanvasRenderEngine.getDimensionSize(flexGrid.getCellPaddingTop()));
	}

	@Override
	public void createCellContent(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
	{
		// checking only for cell elements
		if (gridPanel.getCellType().equals(GridCellType.CELL) && mFilterString != null)
		{
			mChartPoint = (ChartPoint) gridPanel.getRows().get(cellRange.row).getDataItem();

			this.mCellWidth = bounds.width();
			this.mCellHeight = bounds.height();
			mTextView.setLayoutParams(new LayoutParams(mCellWidth - (mPaddingLeft * 2), mCellHeight - (mPaddingTop * 2)));

			String displayString = getFilterVariable(mChartPoint, cellRange.col);
			mSpanText = Spannable.Factory.getInstance().newSpannable(displayString);

			// setting boundaries for highlight
			mHighlightLeft = displayString.toLowerCase(Locale.getDefault()).indexOf(mFilterString.toLowerCase(Locale.getDefault()));
			mHighlightRight = mHighlightLeft + mFilterString.length();

			// highlighting the entered text
			if (mHighlightLeft >= 0)
				mSpanText.setSpan(new BackgroundColorSpan(Color.YELLOW), mHighlightLeft, mHighlightRight, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			mTextView.setText(mSpanText);

			renderEngine.canvas.save();
			renderEngine.canvas.translate(bounds.left + renderEngine.panX + mPaddingLeft, bounds.top + renderEngine.panY + mPaddingTop);

			mTextView.draw(renderEngine.canvas);

			renderEngine.canvas.restore();
		}
		else
			super.createCellContent(gridPanel, renderEngine, cellRange, bounds);
	}

	private String getFilterVariable(ChartPoint arg0, int i)
	{
		// call required getter method of ChartPoint
		switch (i)
		{
			case 9:
				return Integer.toString(arg0.getId());
			case 0:
				return arg0.getName();
			case 10:
				return Integer.toString(arg0.getCountryId());
			case 2:
				return arg0.getCountry();
			case 11:
				return "";
			case 6:
				return arg0.getFirst();
			case 3:
				return arg0.getLast();
			case 7:
				return arg0.getHiredDate().toString();
			case 8:
				return arg0.getHiredTime().toString();
			case 12:
				return Float.toString(arg0.getWeight());
			case 5:
				return arg0.getFather();
			case 1:
				return arg0.getBrother();
			case 4:
				return arg0.getCousin();
		}
		return null;
	}

	public void setFilterString(String filterString)
	{
		this.mFilterString = filterString;
	}
}
