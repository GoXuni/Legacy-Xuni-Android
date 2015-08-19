package com.grapecity.xuni.samples.flexchart;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SampleListAdapter extends BaseAdapter
{
	private List<SampleModel> list;
	private LayoutInflater inflater;

	public SampleListAdapter(Context context)
	{
		list = new ArrayList<SampleModel>();
		this.inflater = LayoutInflater.from(context);

		// initializing SampleModel objects for each sample
		SampleModel gettingStarted = new SampleModel(context.getResources().getString(R.string.gettingStarted), context.getResources().getString(
				R.string.gettingStartedDesc), R.drawable.chart_column);
		SampleModel basicChartTypes = new SampleModel(context.getResources().getString(R.string.basicChartTypes), context.getResources().getString(
				R.string.basicChartTypesDesc), R.drawable.chart_area);
		SampleModel mixedChartTypes = new SampleModel(context.getResources().getString(R.string.mixedChartTypes), context.getResources().getString(
				R.string.mixedChartTypesDesc), R.drawable.chart_composite);
		SampleModel financialChart = new SampleModel(context.getResources().getString(R.string.financialChart), context.getResources().getString(
				R.string.financialChartDesc), R.drawable.chart_finance);
		SampleModel bubbleChart = new SampleModel(context.getResources().getString(R.string.bubbleChart), context.getResources().getString(
				R.string.bubbleChartDesc), R.drawable.chart_bubble);
		SampleModel customTooltips = new SampleModel(context.getResources().getString(R.string.customTooltips), context.getResources().getString(
				R.string.customTooltipsDesc), R.drawable.chart_tooltip);
		SampleModel dataLabels = new SampleModel(context.getResources().getString(R.string.dataLabels), context.getResources().getString(
				R.string.dataLabelsDesc), R.drawable.chart_tooltip);
		SampleModel theming = new SampleModel(context.getResources().getString(R.string.theming), context.getResources().getString(R.string.themingDesc),
				R.drawable.themes);
		SampleModel stylingSeries = new SampleModel(context.getResources().getString(R.string.stylingSeries), context.getResources().getString(
				R.string.stylingSeriesDesc), R.drawable.chart_composite);
		SampleModel customizingAxes = new SampleModel(context.getResources().getString(R.string.customizingAxes), context.getResources().getString(
				R.string.customizingAxesDesc), R.drawable.chart_axes);
		SampleModel legendAndTitles = new SampleModel(context.getResources().getString(R.string.legendAndTitles), context.getResources().getString(
				R.string.legendAndTitlesDesc), R.drawable.chart_aggregate);
		SampleModel selectionModes = new SampleModel(context.getResources().getString(R.string.selectionModes), context.getResources().getString(
				R.string.selectionModesDesc), R.drawable.chart_selection);
		SampleModel toggleSeries = new SampleModel(context.getResources().getString(R.string.toggleSeries), context.getResources().getString(
				R.string.toggleSeriesDesc), R.drawable.chart_column);
		SampleModel animationOptions = new SampleModel(context.getResources().getString(R.string.animationOptions), context.getResources().getString(
				R.string.animationOptionsDesc), R.drawable.chart_animation);
		SampleModel updateAnimation = new SampleModel(context.getResources().getString(R.string.updateAnimation), context.getResources().getString(
				R.string.updateAnimationDesc), R.drawable.chart_tooltip);
		SampleModel conditionalFormatting = new SampleModel(context.getResources().getString(R.string.conditionalFormatting), context.getResources().getString(
				R.string.conditionalFormattingDesc), R.drawable.chart_format);
		SampleModel dynamicCharts = new SampleModel(context.getResources().getString(R.string.dynamicCharts), context.getResources().getString(
				R.string.dynamicChartsDesc), R.drawable.chart_dynamic);
		SampleModel scrolling = new SampleModel(context.getResources().getString(R.string.scrolling), context.getResources().getString(
				R.string.scrollingDesc), R.drawable.chart_scatter);
		SampleModel zoomingAndScrolling = new SampleModel(context.getResources().getString(R.string.zoomingAndScrolling), context.getResources().getString(
				R.string.zoomingAndScrollingDesc), R.drawable.chart_scatter);
		SampleModel hitTest = new SampleModel(context.getResources().getString(R.string.hitTest), context.getResources().getString(R.string.hitTestDesc),
				R.drawable.chart_line);
		SampleModel multipleAxes = new SampleModel(context.getResources().getString(R.string.multipleAxes), context.getResources().getString(
				R.string.multipleAxesDesc), R.drawable.chart_composite);
		SampleModel customPlotElements = new SampleModel(context.getResources().getString(R.string.customPlotElements), context.getResources().getString(
				R.string.customPlotElementsDesc), R.drawable.custom);
		SampleModel snapshotActivity = new SampleModel(context.getResources().getString(R.string.snapshot), context.getResources().getString(
				R.string.snapshotDesc), R.drawable.chart_column);

		// adding objects to list
		list.add(gettingStarted);
		list.add(basicChartTypes);
		list.add(mixedChartTypes);
		list.add(financialChart);
		list.add(bubbleChart);
		list.add(customTooltips);
		list.add(dataLabels);
		list.add(theming);
		list.add(stylingSeries);
		list.add(customizingAxes);
		list.add(legendAndTitles);
		list.add(selectionModes);
		list.add(toggleSeries);
		list.add(animationOptions);
		list.add(updateAnimation);
		list.add(conditionalFormatting);
		list.add(dynamicCharts);
		list.add(scrolling);
		list.add(zoomingAndScrolling);
		list.add(hitTest);
		list.add(multipleAxes);
		list.add(customPlotElements);
		list.add(snapshotActivity);
	}

	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return list.get(position).getThumbnailResourceId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
		{
			convertView = this.inflater.inflate(R.layout.activity_custom_list, parent, false);
		}

		SampleModel sample = list.get(position);

		// creating custom view for each list element
		((ImageView) convertView.findViewById(R.id.sampleImage)).setImageResource(sample.getThumbnailResourceId());
		((TextView) convertView.findViewById(R.id.sampleName)).setText(sample.getName());
		((TextView) convertView.findViewById(R.id.sampleDesc)).setText(sample.getDescription());

		return convertView;
	}
}
