package com.grapecity.xuni.samples.flexgrid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class CustomEditText extends EditText
{
	private OnEditTextImeBackListener mOnImeBack;

	public CustomEditText(Context context)
	{
		super(context);
	}

	public CustomEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public CustomEditText(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			if (mOnImeBack != null)
				mOnImeBack.onImeBack(this, this.getText().toString());
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	public void setOnEditTextImeBackListener(OnEditTextImeBackListener listener)
	{
		mOnImeBack = listener;
	}
}

interface OnEditTextImeBackListener
{
	public abstract void onImeBack(CustomEditText ctrl, String text);
}
