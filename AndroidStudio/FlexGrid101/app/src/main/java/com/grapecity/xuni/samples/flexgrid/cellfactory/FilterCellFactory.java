package com.grapecity.xuni.samples.flexgrid.cellfactory;

import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.FlexGridCanvasRenderEngine;
import com.grapecity.xuni.flexgrid.GridCellFactory;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridCellType;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.samples.flexgrid.data.Customer;

/**
 * Custom GridCellFactory that will highlight text filted by.
 * 
 * @author GrapeCity
 * 
 */
public class FilterCellFactory extends GridCellFactory
{
	private Customer mChartPoint;
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
			mChartPoint = (Customer) gridPanel.getRows().get(cellRange.row).getDataItem();

			this.mCellWidth = bounds.width();
			this.mCellHeight = bounds.height();
			mTextView.setLayoutParams(new LayoutParams(mCellWidth - (mPaddingLeft * 2), mCellHeight - (mPaddingTop * 2)));

			String displayString = getFilterVariable(mChartPoint, gridPanel.getColumns().get(cellRange.col).getBinding());
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

	private String getFilterVariable(Customer customer, String propertyName)
	{
		if ("id".equals(propertyName))
		{
			return String.valueOf(customer.isActive());
		}
		else if ("active".equals(propertyName))
		{
			return String.valueOf(customer.isActive());
		}
		else if ("address".equals(propertyName))
		{
			return customer.getAddress();
		}
		else if ("city".equals(propertyName))
		{
			return customer.getCity();
		}
		else if ("countryId".equals(propertyName))
		{
			return "" + customer.getCountryId();
		}
		else if ("email".equals(propertyName))
		{
			return customer.getEmail();
		}
		else if ("firstName".equals(propertyName))
		{
			return customer.getFirstName();
		}
		else if ("lastName".equals(propertyName))
		{
			return customer.getLastName();
		}
		
		return null;
	}

	public void setFilterString(String filterString)
	{
		this.mFilterString = filterString;
	}
}
