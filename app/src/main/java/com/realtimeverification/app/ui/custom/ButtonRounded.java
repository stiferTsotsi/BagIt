//package com.realtimeverification.app.ui.custom;
//
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.drawable.GradientDrawable;
//import android.graphics.drawable.StateListDrawable;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.widget.Button;
//
///**
// * Created by lebohangTsotetsi on 2015/03/03.
// */
//public class ButtonRounded extends Button {
//	private Color mColor;
//	private int borderColor;
//
//	public ButtonRounded(Context context) {
//		super(context);
//		init(null);
//	}
//
//	public ButtonRounded(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		init(attrs);
//	}
//
//	public ButtonRounded(Context context, AttributeSet attrs, int defStyle) {
//		super(context, attrs, defStyle);
//		init(attrs);
//	}
//
//	private void init(AttributeSet attrs) {
//		if (attrs != null) {
//			TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ButtonRounded, 0, 0);
//			try {
//				//setBorderColor(ta.getInt(R.styleable.ButtonRounded_border, -1));
//
//				setBorderColor(ta.getInt(R.styleable.ButtonRounded_border, -1));
//				setColor(ta.getInt(R.styleable.ButtonRounded_color, -1));
//
//				//setBorderColor(ta.getInt(R.styleable.ButtonRounded_border, -1));
//
//			} finally {
//				ta.recycle();
//			}
//		}
//	}
//
//	// The border color
//	private void setBorderColor(int attrValue){
//		if (attrValue >= 0) {
//			//setBorderColor(Color.values()[attrValue]);
//			borderColor = (Color.values()[attrValue]).getBorder(getContext());
//		}
//	}
//
//	// The background color of the Button
//	private void setColor(int attrValue) {
//		if (attrValue >= 0) {
//			setColor(Color.values()[attrValue]);
//		}
//	}
//	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//	public void setColor(Color color) {
//		mColor = color;
//		StateListDrawable states = new StateListDrawable();
//
//		states.addState(new int[]{android.R.attr.state_pressed}, getBackground(color.getPressed(getContext())));
//		states.addState(new int[]{}, getBackground(color.getDefault(getContext())));
//
//		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//			setBackgroundDrawable(states);
//		} else {
//			setBackground(states);
//		}
//	}
//
//	GradientDrawable gd;
//	private GradientDrawable getBackground(int color) {
//		gd = new GradientDrawable();
//		gd.setColor(color);
//		//gd.setColor(Color.C9.getDefault(getContext()));
//
//		//gd.setStroke(2, Color.C6.getDefault(getContext()));     // The secondary button outline
//		gd.setStroke(2, borderColor);
//
//		//gd.setStroke(2, borderColor.getDefault(getContext()));     // The secondary button outline
//
//		//gd.setStroke(2, color);     // The secondary button outline
//
//		//gd.setCornerRadius(getContext().getResources().getDimensionPixelOffset(R.dimen.button_rounded_corner));
//		gd.setCornerRadius(getContext().getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin));
//		return gd;
//	}
//
//	public enum Color {
//		C1(R.color.colour_one, R.color.colour_one_selector),
//		C2(R.color.colour_two, R.color.colour_two_selector),
//		C3(R.color.colour_three, R.color.colour_three_selector),
//		C4(R.color.colour_four, R.color.colour_four_selector),
//		C5(R.color.colour_five, R.color.colour_five_selector),
//		C6(R.color.colour_six, R.color.colour_six_selector),
//		C7(R.color.colour_seven_selector, R.color.colour_seven_selector),
//		C8(R.color.colour_eight, R.color.colour_eight_selector),
//		C9(R.color.colour_nine, R.color.colour_nine_selector),
//		C10(R.color.colour_ten, R.color.colour_ten_selector),
//		C11(R.color.colour_eleven, R.color.colour_eleven_selector),
//		C12(R.color.colour_twelve, R.color.colour_twelve_selector),
//		CSEC(R.color.colour_secondary, R.color.colour_secondary_selector),
//		CSECBORDER(R.color.colour_secondary_border, R.color.colour_secondary_border),
//		CDEFAULT(R.color.colour_default, R.color.colour_default);
//
//		private int mDefaultId;
//		private int mPressedId;
//
//		private Color(int defaultId, int pressedId) {
//			mDefaultId = defaultId;
//			mPressedId = pressedId;
//		}
//
//		public int getDefault(Context context) {
//			return getColor(context, mDefaultId);
//		}
//
//		public int getPressed(Context context) {
//			return getColor(context, mPressedId);
//		}
//
//		private int getColor(Context context, int resId) {
//			return context.getResources().getColor(resId);
//		}
//
//		public int getBorder(Context context) {
//			return getColor(context, mDefaultId);
//		}
//	}
//}
