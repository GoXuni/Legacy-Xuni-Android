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
				R.string.gettingStartedDesc), R.drawable.chart_column, GettingStartedActivity.class);
		SampleModel basicChartTypes = new SampleModel(context.getResources().getString(R.string.basicChartTypes), context.getResources().getString(
				R.string.basicChartTypesDesc), R.drawable.chart_area, BasicChartTypesActivity.class);
		SampleModel mixedChartTypes = new SampleModel(context.getResources().getString(R.string.mixedChartTypes), context.getResources().getString(
				R.string.mixedChartTypesDesc), R.drawable.chart_composite, MixedChartTypesActivity.class);
		SampleModel financialChart = new SampleModel(context.getResources().getString(R.string.financialChart), context.getResources().getString(
				R.string.financialChartDesc), R.drawable.chart_finance, FinancialChartTypesActivity.class);
		SampleModel bubbleChart = new SampleModel(context.getResources().getString(R.string.bubbleChart), context.getResources().getString(
				R.string.bubbleChartDesc), R.drawable.chart_bubble, BubbleChartActivity.class);
		SampleModel customTooltips = new SampleModel(context.getResources().getString(R.string.customTooltips), context.getResources().getString(
				R.string.customTooltipsDesc), R.drawable.chart_tooltip, CustomTooltipsActivity.class);
		SampleModel dataLabels = new SampleModel(context.getResources().getString(R.string.dataLabels), context.getResources().getString(
				R.string.dataLabelsDesc), R.drawable.chart_tooltip, DataLabelsActivity.class);
		SampleModel theming = new SampleModel(context.getResources().getString(R.string.theming), context.getResources().getString(R.string.themingDesc),
				R.drawable.themes, ThemingActivity.class);
		SampleModel stylingSeries = new SampleModel(context.getResources().getString(R.string.stylingSeries), context.getResources().getString(
				R.string.stylingSeriesDesc), R.drawable.chart_composite, StylingSeriesActivity.class);
		SampleModel legendAndTitles = new SampleModel(context.getResources().getString(R.string.legendAndTitles), context.getResources().getString(
				R.string.legendAndTitlesDesc), R.drawable.chart_aggregate, LegendAndTitlesActivity.class);
		SampleModel selectionModes = new SampleModel(context.getResources().getString(R.string.selectionModes), context.getResources().getString(
				R.string.selectionModesDesc), R.drawable.chart_selection, SelectionModesActivity.class);
		SampleModel toggleSeries = new SampleModel(context.getResources().getString(R.string.toggleSeries), context.getResources().getString(
				R.string.toggleSeriesDesc), R.drawable.chart_column, ToggleSeriesActivity.class);
		SampleModel animationOptions = new SampleModel(context.getResources().getString(R.string.animationOptions), context.getResources().getString(
				R.string.animationOptionsDesc), R.drawable.chart_animation, AnimationOptionsActivity.class);
		SampleModel updateAnimation = new SampleModel(context.getResources().getString(R.string.updateAnimation), context.getResources().getString(
				R.string.updateAnimationDesc), R.drawable.chart_tooltip, UpdateAnimationActivity.class);
		SampleModel conditionalFormatting = new SampleModel(context.getResources().getString(R.string.conditionalFormatting), context.getResources().getString(
				R.string.conditionalFormattingDesc), R.drawable.chart_format, ConditionalFormattingActivity.class);
		SampleModel dynamicCharts = new SampleModel(context.getResources().getString(R.string.dynamicCharts), context.getResources().getString(
				R.string.dynamicChartsDesc), R.drawable.chart_dynamic, DynamicChartsActivity.class);
		SampleModel scrolling = new SampleModel(context.getResources().getString(R.string.scrolling), context.getResources().getString(R.string.scrollingDesc),
				R.drawable.chart_scatter, ScrollingActivity.class);
		SampleModel zoomingAndScrolling = new SampleModel(context.getResources().getString(R.string.zoomingAndScrolling), context.getResources().getString(
				R.string.zoomingAndScrollingDesc), R.drawable.chart_scatter, ZoomingAndScrollingActivity.class);
		SampleModel hitTest = new SampleModel(context.getResources().getString(R.string.hitTest), context.getResources().getString(R.string.hitTestDesc),
				R.drawable.chart_line, HitTestActivity.class);
		SampleModel multipleAxes = new SampleModel(context.getResources().getString(R.string.multipleAxes), context.getResources().getString(
				R.string.multipleAxesDesc), R.drawable.chart_composite, MultipleAxesActivity.class);
		SampleModel customPlotElements = new SampleModel(context.getResources().getString(R.string.customPlotElements), context.getResources().getString(
				R.string.customPlotElementsDesc), R.drawable.custom, CustomPlotElementsActivity.class);
		SampleModel snapshotActivity = new SampleModel(context.getResources().getString(R.string.snapshot), context.getResources().getString(
				R.string.snapshotDesc), R.drawable.chart_column, SnapshotActivity.class);
		SampleModel customMarkerActivity = new SampleModel(context.getResources().getString(R.string.customMarker), context.getResources().getString(
				R.string.customMarkerDesc), R.drawable.chart_marker, CustomMarkerActivity.class);
		SampleModel customizingAxesLabelActivity = new SampleModel(context.getResources().getString(R.string.customizingAxesLabel), context.getResources()
				.getString(R.string.customizingAxesLabelDesc), R.drawable.chart_axes, CustomizingAxesLabelActivity.class);

		// adding objects to list
		list.add(gettingStarted);
		list.add(basicChartTypes);
		list.add(mixedChartTypes);
		list.add(financialChart);
		list.add(bubbleChart);
		list.add(customTooltips);
		list.add(dataLabels);
		list.add(customMarkerActivity);
		list.add(customizingAxesLabelActivity);
		list.add(multipleAxes);
		list.add(legendAndTitles);
		list.add(conditionalFormatting);
		list.add(customPlotElements);
		list.add(selectionModes);
		list.add(toggleSeries);
		list.add(animationOptions);
		list.add(updateAnimation);
		list.add(dynamicCharts);
		list.add(hitTest);
		list.add(scrolling);
		list.add(zoomingAndScrolling);
		list.add(theming);
		list.add(stylingSeries);
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
