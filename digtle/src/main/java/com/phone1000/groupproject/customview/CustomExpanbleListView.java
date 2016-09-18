package com.phone1000.groupproject.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by ${USER_NAME} on 2016/9/18.
 */
public class CustomExpanbleListView extends ExpandableListView {
    public CustomExpanbleListView(Context context) {
        super(context);
    }

    public CustomExpanbleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExpanbleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
