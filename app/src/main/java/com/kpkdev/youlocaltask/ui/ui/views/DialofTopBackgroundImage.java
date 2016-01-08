package com.kpkdev.youlocaltask.ui.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.kpkdev.youlocaltask.R;

/**
 * Created by krasimirkaramazov on 1/8/16.
 */
public class DialofTopBackgroundImage extends View {

    private final Drawable logo;

    public DialofTopBackgroundImage(Context context) {
        super(context);
        logo = context.getResources().getDrawable(R.drawable.img_user_profile_top_background);
        setBackgroundDrawable(logo);
    }

    public DialofTopBackgroundImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        logo = context.getResources().getDrawable(R.drawable.img_user_profile_top_background);
        setBackgroundDrawable(logo);
    }

    public DialofTopBackgroundImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        logo = context.getResources().getDrawable(R.drawable.img_user_profile_top_background);
        setBackgroundDrawable(logo);
    }

    @Override protected void onMeasure(int widthMeasureSpec,
                                       int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * logo.getIntrinsicHeight() / logo.getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }
}
