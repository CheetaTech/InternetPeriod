package com.mobiledatatimerwidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AutoEditText extends EditText
{
    LinearLayout.LayoutParams tabletParams;
    public AutoEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        autoSize();
    }

    public AutoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        autoSize();
    }

    public AutoEditText(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        autoSize();
    }

    public void autoSize()
    {
        //this.setBackgroundColor(Color.rgb(46, 46, 46));
        if(!isInEditMode())
        {
            this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/heavy_data.ttf"));
        }
        this.setTextColor(Color.WHITE);

        if(Config.IS_TABLET)
        {
            this.setBackgroundColor(Color.rgb(46, 46, 46));
            this.setTextSize(Config.SCREEN_WIDTH / 38);

            if(Config.IS_LANDSCAPE)
            {
                tabletParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                tabletParams.weight = 1;
                tabletParams.rightMargin = Config.SCREEN_WIDTH /3;
                this.setLayoutParams(tabletParams);
            }
            else
            {
                tabletParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                tabletParams.weight = 1;
                tabletParams.rightMargin = Config.SCREEN_WIDTH /4;
                this.setLayoutParams(tabletParams);
            }
        }
        else
        {
            this.setBackgroundColor(Color.rgb(46, 46, 46));
            tabletParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            tabletParams.weight = 1;
            tabletParams.rightMargin = Config.SCREEN_WIDTH /9;
            this.setLayoutParams(tabletParams);
        }


    }
}