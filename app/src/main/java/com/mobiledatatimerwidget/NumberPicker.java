package com.mobiledatatimerwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class NumberPicker extends View {
	String TAG = "numberpicker";

	public final int STYLE_TOP = 0;
	public final int STYLE_MID = 1;

	private final int NONE = 0;
	private final int INCREASE = 1;
	private final int DECREASE = 3;

	private static String ns = "http://oldsch00l.com/android/numberpicker";

	private int mDigits = 3; // number of digits shown
	private int mBoxSize = 50; // cell size
	private int mTextSize = 34; // text/number size
	private int mMargin = 6; // margin between boxes
	private long mRepeatDelay = 150;

	private Paint mTextPaint;
	private int mAscent;
	private int mScaledBoxSize;
	private int mScaledMargin;
	private int mStyle;

	private int mValue;
	private StringBuilder mSBValue;
	private int mStateIndex;
	private int mState;

	final float mScale = getResources().getDisplayMetrics().density;

	//last increase/decrease time
	private Handler repeatUpdateHandler = new Handler();

	//drawables
	NinePatchDrawable mDrwNumber = (NinePatchDrawable)getResources().getDrawable(R.drawable.number_input_normal);
	NinePatchDrawable mDrwBtnPlus = (NinePatchDrawable)getResources().getDrawable(R.drawable.btn_plus_normal);
	NinePatchDrawable mDrwBtnPlusPressed = (NinePatchDrawable)getResources().getDrawable(R.drawable.btn_plus_pressed);
	NinePatchDrawable mDrwBtnMinus = (NinePatchDrawable)getResources().getDrawable(R.drawable.btn_minus_normal);
	NinePatchDrawable mDrwBtnMinusPressed = (NinePatchDrawable)getResources().getDrawable(R.drawable.btn_minus_pressed);

	public NumberPicker(Context context) {
		super(context);
		init();
	}

	public NumberPicker(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		mDigits = attributeSet.getAttributeIntValue(ns, "digits", 3);
		mBoxSize = attributeSet.getAttributeIntValue(ns, "size", 50);
		mTextSize = attributeSet.getAttributeIntValue(ns, "textSize", 34);
		mValue = attributeSet.getAttributeIntValue(ns, "value", 0);
		mRepeatDelay = attributeSet.getAttributeIntValue(ns, "repeatDelay", 150);
		mStyle = attributeSet.getAttributeListValue(ns, "style", new String[] { "top", "mid" }, STYLE_TOP);
		init();
	}

	public void init() {
		mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setTextSize(mTextSize * mScale);
		mTextPaint.setColor(0xFF000000);
		setPadding(0, 0, 0, 0);
		setValue(mValue);
		mStateIndex = -1;
		mState = 0;
		mScaledBoxSize = (int)(mBoxSize * mScale);
		mScaledMargin = (int)(mMargin * mScale);
	}

	protected void updatePadding() {
		while(mSBValue.length() < mDigits){
			mSBValue.insert(0, "0");
		}
	}

	public int getValue() {
		return mValue;
	}

	public void setValue(int value) {
		if( value >= Math.pow(10, mDigits) ) value = (int)Math.pow(10, mDigits) - 1;
		if( value >= 0 ) {
			mValue = value;
			mSBValue = new StringBuilder(mValue + "");
			updatePadding();
		}
	}

	public void increment(int pos) {
		int value = mValue + (int)Math.pow( 10, pos);
		setValue(value);
	}

	public void decrement(int pos){
		int value = mValue - (int)Math.pow( 10, pos);
		setValue(value);
	}

	/**
	 * @see View#measure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	/**
	 * Determines the width of this view
	 *
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The width of the view, honoring constraints from measureSpec
	 */
	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text
			result = getPaddingLeft() + getPaddingRight() + (mScaledMargin * 2) + (mScaledBoxSize * mDigits);
			if (specMode == MeasureSpec.AT_MOST) {
				// Respect AT_MOST value if that was what is called for by
				// measureSpec
				result = Math.min(result, specSize);
			}
		}

		return mScaledBoxSize * mDigits + getPaddingLeft() + getPaddingRight() + mScaledMargin * 2;
	}

	/**
	 * Determines the height of this view
	 *
	 * @param measureSpec
	 *            A measureSpec packed into an int
	 * @return The height of the view, honoring constraints from measureSpec
	 */
	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		mAscent = (int) mTextPaint.ascent();
		if (specMode == MeasureSpec.EXACTLY) {
			// We were told how big to be
			result = specSize;
		} else {
			// Measure the text (beware: ascent is a negative number)
			result = (int) (-mAscent + mTextPaint.descent()) + getPaddingTop()
					+ getPaddingBottom() + (mScaledMargin * 2);
			if (specMode == MeasureSpec.AT_MOST) {
				// Respect AT_MOST value if that was what is called for by
				// measureSpec
				result = Math.min(result, specSize);
			}
		}
		return (mScaledBoxSize + getPaddingBottom() + getPaddingTop() + 1) * 3;
	}

	/**
	 * Render the text
	 *
	 * @see View#onDraw(Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		switch(mStyle)
		{
		case STYLE_MID:
			drawIncreaseButtons(canvas);
			drawNumbers(canvas, mScaledBoxSize + 1);
			drawDecreaseButtons(canvas, mScaledBoxSize * 2 + 2);
		break;
		default:
			drawNumbers(canvas);
			drawIncreaseButtons(canvas, mScaledBoxSize + 1);
			drawDecreaseButtons(canvas, mScaledBoxSize * 2 + 2);
		}
	}

	protected void drawIncreaseButtons(Canvas canvas) {
		drawIncreaseButtons(canvas, 0);
	}

	protected void drawIncreaseButtons(Canvas canvas, int hOffset) {
		int top = hOffset;
		int bottom = hOffset + mScaledBoxSize;

		for(int i = 0; i < mDigits; i++) {
			int left = i * mScaledBoxSize;
			if(mState == 1 && mStateIndex == i) {
				mDrwBtnPlusPressed.setBounds(left, top, left + mScaledBoxSize, bottom);
				mDrwBtnPlusPressed.draw(canvas);
			} else {
				mDrwBtnPlus.setBounds(left, top, left + mScaledBoxSize, bottom);
				mDrwBtnPlus.draw(canvas);
			}
		}
	}

	protected void drawNumbers(Canvas canvas) {
		drawNumbers(canvas, 0);
	}

	protected void drawNumbers(Canvas canvas, int hOffset) {
		Rect textBounds = new Rect();

		int top = hOffset;
		int bottom = hOffset + mScaledBoxSize;

		//mDrwNumber.setBounds(-2, top, mBoxSize * mDigits + 2, bottom);
		//mDrwNumber.draw(canvas);

		for(int i = 0; i < mDigits; i++) {
			String digit = mSBValue.charAt(i) + "";
			mTextPaint.getTextBounds(digit, 0, 1, textBounds);
			int left = i * mScaledBoxSize;
			mDrwNumber.setBounds(left - (int)(2 * mScale), top, left + mScaledBoxSize + (int)(2 * mScale), bottom);
			mDrwNumber.draw(canvas);
			canvas.drawText(
					digit,
					left + (mScaledBoxSize / 2) - (textBounds.width() / 2),
					top + (mScaledBoxSize / 2) + (textBounds.height() / 2),
					mTextPaint);
		}
	}

	protected void drawDecreaseButtons(Canvas canvas, int hOffset) {
		int top = hOffset;
		int bottom = hOffset + mScaledBoxSize - getPaddingBottom();

		for(int i = 0; i < mDigits; i++) {
			int left = i * mScaledBoxSize;
			if(mState == 3 && mStateIndex == i) {
				mDrwBtnMinusPressed.setBounds(left, top, left + mScaledBoxSize, bottom);
				mDrwBtnMinusPressed.draw(canvas);
			} else {
				mDrwBtnMinus.setBounds(left, top, left + mScaledBoxSize, bottom);
				mDrwBtnMinus.draw(canvas);
			}
		}
	}

	protected Rect getIncreaseBounds() {
		if(mStyle == STYLE_MID)
			return new Rect( 0, 0, mScaledBoxSize * mDigits, mScaledBoxSize);
		else
			return new Rect( 0, mScaledBoxSize, mScaledBoxSize * mDigits, mScaledBoxSize * 2);
	}

	protected Rect getDecreaseBounds() {
		return new Rect( 0, mScaledBoxSize * 2, mScaledBoxSize * mDigits, mScaledBoxSize * 3);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN: {
			Rect incBounds = getIncreaseBounds();
			Rect decBounds = getDecreaseBounds();

			mStateIndex = (int)(event.getX() / mScaledBoxSize);
			int index = mDigits - mStateIndex - 1; //invert

			if(event.getY() > incBounds.top && event.getY() < incBounds.bottom) {
				mState = INCREASE;
				repeatUpdateHandler.post( new RepetetiveUpdater(mState, index) );
			} else if(event.getY() > decBounds.top && event.getY() < decBounds.bottom) {
				mState = DECREASE;
				repeatUpdateHandler.post( new RepetetiveUpdater(mState, index) );
			}
		}
		break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL: {
			mState = NONE;
			mStateIndex = -1;
		}
		break;
		}
		invalidate();
		return true;
	}

	/**
	 * As long we are at the INCREASE or DECREASE state, do this and renew the task.
	 *
	 * @author Peinthor Rene
	 *
	 */
	class RepetetiveUpdater implements Runnable {
		private int mPos = 0;
		public RepetetiveUpdater(int state, int pos)
		{
			mPos = pos;
		}
		public void run() {
			switch(mState)
			{
			case INCREASE: {
				increment(mPos);
				invalidate();
				repeatUpdateHandler.postDelayed( new RepetetiveUpdater(mState, mPos), mRepeatDelay );
			}
			break;
			case DECREASE: {
				decrement(mPos);
				invalidate();
				repeatUpdateHandler.postDelayed( new RepetetiveUpdater(mState, mPos), mRepeatDelay );
			}
			break;
			}
		}
	}

}
