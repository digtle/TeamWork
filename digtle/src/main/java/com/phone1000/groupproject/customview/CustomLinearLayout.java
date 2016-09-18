package com.phone1000.groupproject.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangjw on 2016/8/11.
 * ViewGroup的功能：
 * 1、测量子控件
 * 2、布局子控件
 *
 * 注意：
 * ViewGroup中的所有子控件的绘制都是由其自身onDraw方法完成，跟ViewGroup中的onDraw方法没有关系
 * ViewGroup中的onDraw方法默认情况下是不会执行的，只有给其设置背景时才会执行onDraw方法。
 *
 * 记住：任何控件的绘制都是有其自身完成。onDraw方法只能绘制自身，除此之外不能绘制任何其他内容。
 */
public class CustomLinearLayout extends ViewGroup {
    private static final String TAG = "androidxx";

    public CustomLinearLayout(Context context) {
        this(context,null);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 计算ViewGroup的高度和宽度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            /**
             * 参数1、2：子控件的高度和宽度
             * 父容器不要影响子控件的自身测量结果
             * MeasureSpec.UNSPECIFIED:表示不指定大小，由子控件自身测量决定
             *
             * measure方法会触发子控件的onMeasure执行
             */
            view.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
        }


        //系统给予的高度和宽度
        int sysWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sysHeight = MeasureSpec.getSize(heightMeasureSpec);

        //获得用户设置模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //判断设置是否为wrap_content
        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {

            //--------------计算ViewGroup的高宽-------------------
            //宽度是子控件宽度之和
            //高度就是子控件的高度
            int width = 0;
            int height = 0;
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                int measuredHeight = view.getMeasuredHeight();
                int measuredWidth = view.getMeasuredWidth();
                height = measuredHeight;
                width += measuredWidth;
            }
            //设置当前ViewGroup的高宽
            setMeasuredDimension(width,height);
        } else {
            setMeasuredDimension(sysWidth,sysHeight);
        }




    }

    /**
     * ViewGroup用来布局子View的位置的一个方法
     * @param changed 当ViewGroup中的元素动态发生变化的时候，返回true；否则false。
     * @param l left：VIewGroup的左边距
     * @param t top ViewGroup的上边距
     * @param r right ViewGroup的右边距  右边距屏幕左边的距离
     * @param b bottom VIewGroup的下边距  下边距屏幕上边的距离
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int childCount = getChildCount();
        int totalWidth = 0;
        for (int i = 0; i < childCount; i++) {
            //获得ViewGroup中的Button
            View view = getChildAt(i);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            Log.d(TAG, "onLayout: " + measuredWidth);
            totalWidth += measuredWidth;
            //设置View在布局中的位置
            /**
             * 参数1：left：view的左边距
             * 参数2：top：view的上边距
             * 参数3：view右边距屏幕左边的距离
             * 参数4：view下边距屏幕上边的距离
             */
            view.layout(totalWidth-measuredWidth,0,totalWidth,measuredHeight);
        }

    }

    /**
     * 只有在设置了背景后，才会执行
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
    }
}
