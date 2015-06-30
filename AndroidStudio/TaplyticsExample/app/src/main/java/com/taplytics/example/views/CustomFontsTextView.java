package com.taplytics.example.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.taplytics.example.R;

public class CustomFontsTextView extends TextView {

	public CustomFontsTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	public CustomFontsTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);

	}

	public CustomFontsTextView(Context context) {
		super(context);
		init(null);
	}

	private void init(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFontsTextView);
			String fontName = a.getString(R.styleable.CustomFontsTextView_fontName);
			if (fontName != null) {
				Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
				setTypeface(myTypeface);
			}
			a.recycle();
		}
	}

}