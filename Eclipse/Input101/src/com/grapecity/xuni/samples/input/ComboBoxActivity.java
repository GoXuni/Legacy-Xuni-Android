package com.grapecity.xuni.samples.input;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.grapecity.xuni.core.ObservableList;
import com.grapecity.xuni.input.combobox.XuniComboBox;

public class ComboBoxActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);

		TextView editableLabel = new TextView(this);
		editableLabel.setText(R.string.editable);
		editableLabel.setTextSize(18);
		layout.addView(editableLabel);

		XuniComboBox comboBox = new XuniComboBox(this);
		ObservableList<DropDownItem> list = DropDownItem.getList();
		comboBox.setItemsSource(list);
		comboBox.setDisplayMemberPath("name");
		layout.addView(comboBox);

		Space emptySpace = new Space(this);
		layout.addView(emptySpace);

		TextView nonEditableLabel = new TextView(this);
		nonEditableLabel.setText(R.string.noneditable);
		nonEditableLabel.setTextSize(18);
		layout.addView(nonEditableLabel);

		XuniComboBox nonEditableComboBox = new XuniComboBox(this);
		nonEditableComboBox.setItemsSource(list);
		nonEditableComboBox.setDisplayMemberPath("name");
		nonEditableComboBox.setEditable(false);
		layout.addView(nonEditableComboBox);

		this.setContentView(layout);
	}
}
