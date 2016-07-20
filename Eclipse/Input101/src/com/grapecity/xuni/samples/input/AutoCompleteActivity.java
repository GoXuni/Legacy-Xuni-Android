package com.grapecity.xuni.samples.input;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import com.grapecity.xuni.core.ICollectionView;
import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.core.IPredicate;
import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.input.autocomplete.FilteringEventArgs;
import com.grapecity.xuni.input.autocomplete.MatchType;
import com.grapecity.xuni.input.autocomplete.XuniAutoCompleteTextView;

/**
 * For auto complete control samples.
 * */
public class AutoCompleteActivity extends Activity
{

	ObservableList<DropDownItem> dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocomplete);

		// Highlight sample
		XuniAutoCompleteTextView highLightAutoComplete = (XuniAutoCompleteTextView) this.findViewById(R.id.autocomplete_highlight);
		highLightAutoComplete.setItemsSource(DropDownItem.country);
		highLightAutoComplete.setMatchType(MatchType.CONTAINS);
		highLightAutoComplete.setThreshold(1);

		// Delay sample
		XuniAutoCompleteTextView delayAutoComplete = (XuniAutoCompleteTextView) this.findViewById(R.id.autocomplete_delay);
		delayAutoComplete.setItemsSource(DropDownItem.country);
		delayAutoComplete.setMatchType(MatchType.CONTAINS);
		delayAutoComplete.setThreshold(1);
		delayAutoComplete.setDelay(1500); // Delay 1500 milliseconds.

		// Custom sample
		Map<Integer, String> viewToDataMap = new HashMap<Integer, String>();
		viewToDataMap.put(R.id.imageView1, "flag");
		viewToDataMap.put(R.id.textView1, "name");
		XuniAutoCompleteTextView customAutoComplete = (XuniAutoCompleteTextView) this.findViewById(R.id.autocomplete_custom);
		customAutoComplete.setDropDownItemLayoutId(R.layout.custom_item); // Custom drop down item.
		customAutoComplete.setDropDownItemTextViewId(R.id.textView1);
		customAutoComplete.setViewDataBinderMap(viewToDataMap);
		ObservableList<DropDownItem> list = DropDownItem.getList();
		customAutoComplete.setItemsSource(list);
		customAutoComplete.setDisplayMemberPath("name");
		customAutoComplete.setMatchType(MatchType.CONTAINS);
		customAutoComplete.setThreshold(1);

		// custom filter sample
		final XuniAutoCompleteTextView filterAutoComplete = (XuniAutoCompleteTextView) this.findViewById(R.id.autocomplete_filter);
		filterAutoComplete.setItemsSource(DropDownItem.country);
		filterAutoComplete.setThreshold(1);

		filterAutoComplete.getFilteringEvent().addHandler(new IEventHandler()
		{
			@Override
			public void call(Object sender, Object args)
			{
				ICollectionView collectionView = filterAutoComplete.getCollectionView();

				collectionView.setFilter(new IPredicate()
				{
					@Override
					public boolean execute(Object item)
					{
						String str = (String) item;
						if (str.startsWith("B"))
						{
							return true;
						}
						return false;
					}

				});

				FilteringEventArgs filteringEventArgs = (FilteringEventArgs) args;
				filteringEventArgs.setHandled(true);
			}

		}, this);

	}
}
