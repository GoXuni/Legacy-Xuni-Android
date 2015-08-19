package com.grapecity.xuni.samples.gauge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.grapecity.xuni.gauge.*;

public class BulletGraphActivity extends Activity
{
	private XuniBulletGraph mBulletGraph;
	private TextView mBadText;
	private TextView mGoodText;
	private TextView mTargetText;
	private int mValue = 13;
	private int mBad = 50;
	private int mGood = 70;
	private int mTarget = 90;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bullet_graph);

		// initializing widgets
		mBulletGraph = (XuniBulletGraph) findViewById(R.id.bulletGraph1);
		mBadText = (TextView) findViewById(R.id.badText);
		mGoodText = (TextView) findViewById(R.id.goodText);
		mTargetText = (TextView) findViewById(R.id.targetText);

		// setting default values
		mBulletGraph.setValue(mValue);
		mBulletGraph.setBad(mBad);
		mBulletGraph.setGood(mGood);
		mBulletGraph.setMax(100);
		mBulletGraph.setTarget(mTarget);
		mBulletGraph.setShowTextType(GaugeShowText.NONE);
		mBulletGraph.setStep(1);
		// properties set in XML layout
		// mBulletGraph.setGaugeWidth(.5f);

		mBadText.setText(Integer.toString(mBad));
		mGoodText.setText(Integer.toString(mGood));
		mTargetText.setText(Integer.toString(mTarget));
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.buttonBadMinus:
				mBad -= (mBad > mBulletGraph.getMin()) ? 5 : 0;
				mBulletGraph.setBad(mBad);
				mBadText.setText(Integer.toString(mBad));
				break;
			case R.id.buttonBadPlus:
				mBad += (mBad < mBulletGraph.getMax()) ? 5 : 0;
				mBulletGraph.setBad(mBad);
				mBadText.setText(Integer.toString(mBad));
				break;
			case R.id.buttonGoodMinus:
				mGood -= (mGood > mBulletGraph.getMin()) ? 5 : 0;
				mBulletGraph.setGood(mGood);
				mGoodText.setText(Integer.toString(mGood));
				break;
			case R.id.buttonGoodPlus:
				mGood += (mGood < mBulletGraph.getMax()) ? 5 : 0;
				mBulletGraph.setGood(mGood);
				mGoodText.setText(Integer.toString(mGood));
				break;
			case R.id.buttonTargetMinus:
				mTarget -= (mTarget > mBulletGraph.getMin()) ? 5 : 0;
				mBulletGraph.setTarget(mTarget);
				mTargetText.setText(Integer.toString(mTarget));
				break;
			case R.id.buttonTargetPlus:
				mTarget += (mTarget < mBulletGraph.getMax()) ? 5 : 0;
				mBulletGraph.setTarget(mTarget);
				mTargetText.setText(Integer.toString(mTarget));
				break;
		}

	}
}
